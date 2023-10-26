package etyka.exampub.conrollers;

import etyka.exampub.models.DTOs.DTOuserGetAll;
import etyka.exampub.models.User;
import etyka.exampub.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAll();
        List<DTOuserGetAll> usersDTO = DTOuserGetAll.from(users);
        return ResponseEntity.ok(usersDTO);
    }
}
