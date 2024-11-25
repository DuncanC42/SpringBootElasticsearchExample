package bzh.duncan.springbootelasticsearchexample.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier() {
        Supplier<Query> supplier = () -> Query.of(query -> query.matchAll(matchAllQuery()));
        return supplier;
    }


    public static MatchAllQuery matchAllQuery() {
        MatchAllQuery.Builder matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static Supplier<Query> supplierWithNameField(String nameValue) {
        Supplier<Query> supplier = () -> Query.of(query -> query.match(matchQueryWithNameField(nameValue)));
        return supplier;
    }


    public static MatchQuery matchQueryWithNameField(String name) {
        MatchQuery.Builder matchQuery = new MatchQuery.Builder();
        return matchQuery.field("name").query(name).build();
    }

}
