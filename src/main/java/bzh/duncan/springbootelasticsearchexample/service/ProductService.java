package bzh.duncan.springbootelasticsearchexample.service;

import bzh.duncan.springbootelasticsearchexample.entity.Product;
import bzh.duncan.springbootelasticsearchexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID().toString()); // Génère un ID unique si non fourni
        }
        return productRepository.save(product);
    }

    public Product updateProduct(String productToUpdateId, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(productToUpdateId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setQuantity(updatedProduct.getQuantity());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}