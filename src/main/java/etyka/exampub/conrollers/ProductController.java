package etyka.exampub.conrollers;

import etyka.exampub.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/drink-menu")
    public ResponseEntity<?> getAllDrinks() {
        return ResponseEntity.ok(productService.findAllByType("drink"));
    }
}
