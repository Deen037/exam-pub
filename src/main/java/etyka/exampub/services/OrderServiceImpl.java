package etyka.exampub.services;

import etyka.exampub.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
}
