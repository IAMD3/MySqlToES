#简单实用的MySql数据-> Elasticsearch同步工程

>支持:
>*  索引自动创建
>*  根据表shcema自动生成ES mapping(ES实现)
>*  数据传输


通过修改
config/sub/db.xml
config/sub/elasticsearch.xml
config/sub/task.xml
文件中{}的配置信息 即可实现简单的数据同步

> 扩展: SPI支持 SPIDemoProcessor