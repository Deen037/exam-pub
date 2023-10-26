package etyka.exampub.conrollers;

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
       return ResponseEntity.ok(drink);
    }
}
