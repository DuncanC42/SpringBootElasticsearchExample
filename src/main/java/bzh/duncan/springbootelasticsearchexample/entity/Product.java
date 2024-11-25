package bzh.duncan.springbootelasticsearchexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "products")

public class Product {
    @Id
    @Field(type = FieldType.Keyword)  // Ajoutez cette annotation
    private String id;  // Changez Long en String car Elasticsearch utilise des IDs en String

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Integer)
    private int quantity;

    @Field(type = FieldType.Double)
    private double price;
}
