package etyka.exampub.services;

import etyka.exampub.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    void save(Order order);
}
