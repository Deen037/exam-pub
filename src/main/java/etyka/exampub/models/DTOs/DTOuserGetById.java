package etyka.exampub.models.DTOs;

import etyka.exampub.models.Order;
import etyka.exampub.models.User;
import lombok.Data;

import java.util.List;

@Data
public class DTOuserGetById {

    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
    private List<Order> orders;

    public DTOuserGetById(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.isActive = user.isActive();
        this.isAdult = user.isAdult();
        this.pocket = user.getPocket();
        this.orders = user.getOrders();
    }
}
