package go.entity;

/**
 * @Author: Yukai
 * Description: master T
 * create time: 22/07/2021 18:55
 **/
public class TaskMeta {
    private String name;
    private String index;
    private String sql;
    private long size;
    private boolean createIndex;
    private boolean spi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isCreateIndex() {
        return createIndex;
    }

    public void setCreateIndex(boolean createIndex) {
        this.createIndex = createIndex;
    }

    public boolean isSpi() {
        return spi;
    }

    public void setSpi(boolean spi) {
        this.spi = spi;
    }
}
