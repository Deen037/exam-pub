package etyka.exampub.services;

import etyka.exampub.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();
}
