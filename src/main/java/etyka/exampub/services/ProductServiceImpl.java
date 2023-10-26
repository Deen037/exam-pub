package etyka.exampub.services;

import etyka.exampub.models.Product;
import etyka.exampub.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllByType(String type) {
        return productRepository.findAllByType(type);
    }
}
