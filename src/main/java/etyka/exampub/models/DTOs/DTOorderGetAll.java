package etyka.exampub.models.DTOs;

import etyka.exampub.models.Product;
import lombok.Data;

@Data
public class DTOorderGetAll {

    private Product product;
    private int amount;
    private double unitPrice;
    private double summaryPrice;
}
