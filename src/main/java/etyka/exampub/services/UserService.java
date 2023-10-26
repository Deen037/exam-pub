package etyka.exampub.services;

import etyka.exampub.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();

    Optional<User> findUserById(Long id);
}
