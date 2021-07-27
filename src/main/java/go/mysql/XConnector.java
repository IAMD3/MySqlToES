package go.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: Yukai
 * Description: master T
 * create time: 22/07/2021 19:09
 **/

public class XConnector {
    private  JdbcTemplate template;

    public  List<Map<String, Object>> query(String sql) {
        return template.queryForList(sql);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

}
