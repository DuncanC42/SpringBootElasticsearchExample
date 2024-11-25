package bzh.duncan.springbootelasticsearchexample.service;

import bzh.duncan.springbootelasticsearchexample.entity.Product;
import bzh.duncan.springbootelasticsearchexample.util.ElasticSearchUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient client;

    public SearchResponse<Map> matchAllServices() throws IOException {
        Supplier<Query> querySupplier = ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse = client.search(sup -> sup.query(querySupplier.get()), Map.class);
        System.out.println("Elasticsearch query is " + querySupplier.get().toString());
        return searchResponse;
    }
    public SearchResponse<Product> matchAllProductsServices() throws IOException {
        Supplier<Query> querySupplier = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = client.search(sup ->
                    sup
                        .index("products")
                        .query(querySupplier.get()), Product.class);
        System.out.println("Elasticsearch query is " + querySupplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> matchWithNameField(String nameValue) throws IOException {
        Supplier<Query> querySupplier = ElasticSearchUtil.supplierWithNameField(nameValue);
        SearchResponse<Product> searchResponse = client.search(sup -> sup.query(querySupplier.get()), Product.class);
        System.out.println("Elasticsearch query is " + querySupplier.get().toString());
        return searchResponse;
    }

}
