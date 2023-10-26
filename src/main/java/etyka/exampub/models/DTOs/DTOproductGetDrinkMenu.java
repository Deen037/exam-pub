package etyka.exampub.models.DTOs;

import etyka.exampub.models.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DTOproductGetDrinkMenu {
    private Long id;
    private String name;
    private double price;
    private boolean isForAdult;

    public DTOproductGetDrinkMenu(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.isForAdult = product.isForAdult();
    }

    public static List<DTOproductGetDrinkMenu> from(List<Product> products) {
        return products.stream().map(DTOproductGetDrinkMenu::new).collect(Collectors.toList());
    }
}
