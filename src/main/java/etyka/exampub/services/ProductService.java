package etyka.exampub.services;

import etyka.exampub.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> findAllByType(String type);
}
