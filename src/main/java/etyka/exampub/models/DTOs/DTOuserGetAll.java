package etyka.exampub.models.DTOs;

import etyka.exampub.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class DTOuserGetAll {

    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;

    public DTOuserGetAll(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.isActive = user.isActive();
        this.isAdult = user.isAdult();
        this.pocket = user.getPocket();
    }

    public static List<DTOuserGetAll> from(List<User> users) {
        return users.stream().map(DTOuserGetAll::new).collect(Collectors.toList());
    }
}
