package etyka.exampub.conrollers;

import etyka.exampub.models.DTOs.DTOorderGetAll;
import etyka.exampub.models.DTOs.DTOorderGetByProduct;
import etyka.exampub.models.DTOs.DTOorderGetByUser;
import etyka.exampub.models.DTOs.DTOorderPostBuy;
import etyka.exampub.models.Order;
import etyka.exampub.models.Product;
import etyka.exampub.models.User;
import etyka.exampub.services.OrderService;
import etyka.exampub.services.ProductService;
import etyka.exampub.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody Order order) {

        if (!String.valueOf(order.getUser().getRole()).equals("CUSTOMER")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("You are not CUSTOMER and you are not allowed to buy drinks");
        }

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
    public ResponseEntity<?> getProductsSummaries(@RequestHeader String role) {
        if (!role.equals("BARTENDER")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Only BARTENDER is allowed to see this page");
        }
        List<DTOorderGetAll> response = new ArrayList<>();
        Set<Product> drinks = new HashSet<>(productService.findAllByType("drink"));

        for (Product product : drinks) {
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
    public ResponseEntity<?> getAllOrdersOrderedByProduct(@RequestHeader String role) {

        if (!role.equals("BARTENDER")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Only BARTENDER is allowed to see this page");
        }

        Map<String,List<DTOorderGetByProduct>> response = new HashMap<>();
        Set<Product> drinks = new HashSet<>(productService.findAllByType("drink"));

        for (Product product : drinks) {
            List<Order> orders = orderService.findAllByProductName(product.getName());
            List<DTOorderGetByProduct> productOrderList = new ArrayList<>();

            for (Order order : orders) {
                DTOorderGetByProduct drink = new DTOorderGetByProduct();
                drink.setUserId(order.getUser().getId());
                drink.setAmount(order.getAmount());
                drink.setPrice(order.getPrice());

                productOrderList.add(drink);
            }
            response.put(product.getName(),productOrderList);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/summary/user")
    public ResponseEntity<?> getAllOrdersOrderedByUser(@RequestHeader String role) {

        if (!role.equals("BARTENDER")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Only BARTENDER is allowed to see this page");
        }

        Map<String,List<DTOorderGetByUser>> response = new HashMap<>();
        Set<User> users = new HashSet<>(userService.findAll());

        for (User user : users) {
            List<Order> orders = user.getOrders();
            List<DTOorderGetByUser> userOrderList = new ArrayList<>();

            for (Order order : orders) {
                DTOorderGetByUser userOrder = new DTOorderGetByUser();
                userOrder.setUserId(user.getId());
                userOrder.setProduct(productService.findByName(order.getProductName()));
                userOrder.setPrice(order.getPrice());

                userOrderList.add(userOrder);

            }
            response.put(user.getName(),userOrderList);
        }
        return ResponseEntity.ok(response);
    }
}
