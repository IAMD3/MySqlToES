package go.elasticsearch;

import go.entity.ESMeta;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yukai
 * Description: master T
 * create time: 22/07/2021 19:00
 **/
public class ESClient {

    private RestHighLevelClient client;

    private ESMeta esMeta;

    public void bulkIndex(String index, List<Map<String, Object>> list) throws IOException {
        BulkRequest req = new BulkRequest();

        list.stream().forEach(map -> {
            IndexRequest indexRequest = new IndexRequest();
            indexRequest.source(map);
            indexRequest.index(index);
            indexRequest.type("_doc");
            req.add(indexRequest);
        });
        client.bulk(req, RequestOptions.DEFAULT);
    }


    public void createIndex(String indexName) throws IOException {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest();
        createIndexRequest.index(indexName);
        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    public void init() {
        String user = esMeta.getUser();
        String pwd = esMeta.getPwd();
        HttpHost host = new HttpHost(esMeta.getAddress(), 9200, "http");

        RestClientBuilder restClientBuilder = RestClient.builder(host);

        Header[] defaultHeaders = new Header[]{
                new BasicHeader("Accept", "*/*"),
                new BasicHeader("Charset", "UTF-8")
                // new BasicHeader("E_TOKEN", token)
        };
        restClientBuilder.setDefaultHeaders(defaultHeaders);
        restClientBuilder.setRequestConfigCallback(builder ->
                builder.setConnectTimeout(9999999).setSocketTimeout(9999999));
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, pwd));
        restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        client = new RestHighLevelClient(restClientBuilder);
    }


    public ESMeta getEsMeta() {
        return esMeta;
    }

    public void setEsMeta(ESMeta esMeta) {
        this.esMeta = esMeta;
    }
}
