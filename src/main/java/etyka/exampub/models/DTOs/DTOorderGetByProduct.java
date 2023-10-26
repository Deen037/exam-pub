package etyka.exampub.models.DTOs;

import lombok.Data;

@Data
public class DTOorderGetByProduct {

    private Long userId;
    private int amount;
    private double price;

}
