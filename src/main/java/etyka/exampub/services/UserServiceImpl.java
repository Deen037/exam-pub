package etyka.exampub.services;

import etyka.exampub.models.User;
import etyka.exampub.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
