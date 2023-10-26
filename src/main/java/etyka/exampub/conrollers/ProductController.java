package etyka.exampub.conrollers;

import etyka.exampub.models.DTOs.DTOproductGetDrinkMenu;
import etyka.exampub.models.Product;
import etyka.exampub.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/drink-menu")
    public ResponseEntity<?> getAllDrinks() {
        List<Product> drinks = productService.findAllByType("drink");
        List<DTOproductGetDrinkMenu> drinksDTO = DTOproductGetDrinkMenu.from(drinks);
        return ResponseEntity.ok(drinksDTO);
    }
}
