package etyka.exampub.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DTOorderPostBuy {

    private Long userId;
    private Long productId;
    private double price;
}
