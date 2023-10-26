package etyka.exampub.conrollers;

import etyka.exampub.models.DTOs.DTOorderPostBuy;
import etyka.exampub.models.Order;
import etyka.exampub.models.Product;
import etyka.exampub.services.OrderService;
import etyka.exampub.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody Order order) {
        Product drink = productService.findByName(order.getProductName());

        if(drink.isForAdult() && !order.getUser().isAdult()) {
            return ResponseEntity.badRequest().body("You are not adult");
        }

        if(order.getUser().getPocket() < order.getPrice()){
            return ResponseEntity.badRequest().body("You don't have enough money");
        }

        order.getUser().setPocket(order.getUser().getPocket() - order.getPrice());

        order.getUser().getOrders().add(order);

        orderService.saveOrder(order);

        DTOorderPostBuy response = new DTOorderPostBuy(order.getUser().getId(), drink.getId(), order.getPrice());

        return ResponseEntity.ok(response);

    }
}
