package etyka.exampub.models.DTOs;

import lombok.Data;

@Data
public class DTOorderGetAll {

    private String product;
    private int amount;
    private double unitPrice;
    private double summaryPrice;


}
