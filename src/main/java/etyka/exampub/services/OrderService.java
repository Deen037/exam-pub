package etyka.exampub.services;

import etyka.exampub.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void save(Order order);

    List<Order> findAllByProductName(String name);
}
