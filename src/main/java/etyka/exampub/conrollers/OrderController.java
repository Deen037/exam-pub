package etyka.exampub.conrollers;

import etyka.exampub.models.DTOs.DTOorderGetAll;
import etyka.exampub.models.DTOs.DTOorderPostBuy;
import etyka.exampub.models.Order;
import etyka.exampub.models.Product;
import etyka.exampub.services.OrderService;
import etyka.exampub.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody Order order) {
        Product drink = productService.findByName(order.getProductName());

        if (drink.isForAdult() && !order.getUser().isAdult()) {
            return ResponseEntity.badRequest().body("You are not adult");
        }

        if (order.getUser().getPocket() < order.getPrice()) {
            return ResponseEntity.badRequest().body("You don't have enough money");
        }

        order.getUser().setPocket(order.getUser().getPocket() - order.getPrice());

        order.getUser().getOrders().add(order);

        orderService.save(order);

        DTOorderPostBuy response = new DTOorderPostBuy(order.getUser().getId(), drink.getId(), order.getPrice());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/summary/all")
    public ResponseEntity<?> getProductsSummaries() {
        List<DTOorderGetAll> response = new ArrayList<>();
        Set<Product> products = new HashSet<>(productService.findAll());

        for (Product product : products) {
            List<Order> orders = orderService.findAllByProductName(product.getName());
            int totalAmount = 0;
            double totalPrice = 0;

            for (Order order : orders) {
                totalAmount += order.getAmount();
                totalPrice += order.getPrice();
            }

            DTOorderGetAll productTotal = new DTOorderGetAll();
            productTotal.setProduct(product);
            productTotal.setAmount(totalAmount);
            productTotal.setUnitPrice(product.getPrice());
            productTotal.setSummaryPrice(totalPrice);

            response.add(productTotal);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/summary/product")
    public ResponseEntity<?> getAllOrdersOrderedByProduct(){
        return null;
    }
}
