package etyka.exampub.models.DTOs;

import etyka.exampub.models.Product;
import lombok.Data;

@Data
public class DTOorderGetByUser {

    private Long userId;
    private Product product;
    private double Price;

}
