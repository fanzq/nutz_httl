var ioc = {
    // 读取配置文件
    conf: {
        type: "org.nutz.ioc.impl.PropertiesProxy",
        //fields : { paths : [ "db.properties" ] }
        fields: {
            paths: ["custom/"]
        }
    },
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            create: "init",
            depose: 'close'
        },
        fields: {
            driverClassName: {java: "$conf.get('db-driver')"},
            url: {java: "$conf.get('db-url')"},
            username: {java: "$conf.get('db-user')"},
            password: {java: "$conf.get('db-passwd')"},
            testWhileIdle: true,
            validationQuery: "select 1",
            maxActive: {java: "$conf.get('db-maxActive')"},
            filters: "mergeStat",//sql执行超过2s记录
            connectionProperties: "druid.stat.slowSqlMillis=2000"
        },
        events: {
            depose: "close"
        }
    },

    fileSqlManager: {
        type: "org.nutz.dao.impl.FileSqlManager",
        args: ["sql"]
    },
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [{refer: "dataSource"},
            {
                refer: "fileSqlManager"
            }]
    },

};