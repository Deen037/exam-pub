package etyka.exampub.services;

import etyka.exampub.models.Order;
import etyka.exampub.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAllByProductName(String name) {
        return orderRepository.findAllByProductName(name);
    }
}
