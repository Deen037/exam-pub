package etyka.exampub.conrollers;

import etyka.exampub.models.DTOs.DTOlogin;
import etyka.exampub.models.User;
import etyka.exampub.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DTOlogin credentials){
        Optional<User> user = userService.findByUsername(credentials.getUsername());
        if(user.isPresent()){
            if(user.get().getPassword().equals(credentials.getPassword())){
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
    }
}
