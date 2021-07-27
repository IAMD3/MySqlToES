package go;

import go.elasticsearch.ESClient;
import go.entity.TaskMeta;
import go.mysql.XConnector;
import go.spi.XProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Author: Yukai
 * Description: master T
 * create time: 22/07/2021 18:43
 **/
public class XApp {


    public static void main(String args[]) throws IOException {
        System.err.println("Yukai is so cool xxD!");
        XApp.start();
    }


    public static void start() throws IOException {
        ClassPathXmlApplicationContext appC = new ClassPathXmlApplicationContext("config/bootstrap.xml");
        TaskMeta task = appC.getBean(TaskMeta.class);
        XConnector connector = appC.getBean(XConnector.class);
        ESClient esClient = appC.getBean(ESClient.class);

        String sql = task.getSql() + " limit " + 0 + "," + task.getSize();
        List<Map<String, Object>> rst = connector.query(sql);

        if (task.isSpi()) process(rst);

        if (task.isCreateIndex()) esClient.createIndex(task.getIndex());

        esClient.bulkIndex(task.getIndex(), rst);
    }

    private static void process(List<Map<String, Object>> rsts) {
        ServiceLoader<XProcessor> slProcessors
                = ServiceLoader.load(XProcessor.class, XApp.class.getClassLoader());
        List<XProcessor> xProcessors = StreamSupport
                .stream(slProcessors.spliterator(), false)
                .collect(Collectors.toList());

        for (Map<String, Object> rst : rsts) {
            xProcessors.stream().forEach(p->p.process(rst));
        }

    }


}
