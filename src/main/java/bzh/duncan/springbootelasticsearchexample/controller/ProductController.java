package bzh.duncan.springbootelasticsearchexample.controller;

import bzh.duncan.springbootelasticsearchexample.entity.Product;
import bzh.duncan.springbootelasticsearchexample.service.ElasticSearchService;
import bzh.duncan.springbootelasticsearchexample.service.ProductService;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(id, updatedProduct);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException {
        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        return searchResponse.hits().hits().toString();
    }

    @GetMapping("/matchAllProducts")
    public List<Product> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchAllProductsServices();
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();

        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

    @GetMapping("/matchWithNameField/{name}")
    public ResponseEntity<List<Product>> matchWithNameField(@PathVariable String name) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchWithNameField(name);
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();

        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return ResponseEntity.ok(listOfProducts);
    }

}