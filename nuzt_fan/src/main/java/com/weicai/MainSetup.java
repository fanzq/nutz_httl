package com.weicai;

import com.weicai.demo.pojo.Book;
import com.weicai.demo.pojo.User;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * Created by fan on 2015/7/2.
 */
public class MainSetup implements Setup {

    public void init(NutConfig conf) {
        Ioc ioc = conf.getIoc();
        Dao dao = ioc.get(Dao.class);
        Dao dao1= Mvcs.ctx().getDefaultIoc().get(Dao.class, "dao");
        Daos.createTablesInPackage(dao, "com.weicai.demo.pojo", false);
        System.out.println(dao+"----FFFFFFFFFFFFFFF");
        // 初始化默认根用户
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            dao.fastInsert(user);
            User user1 = new User();
            user1.setName("wendal");
            dao.fastInsert(user1);
        }

     //   dao.create(Book.class, false);
//生成表
        Daos.createTablesInPackage(dao, "com.weicai.demo.pojo", false);
        if (dao.count(Book.class) == 0) {
            for (int i = 0; i < 100; i++) {
                Book book = new Book();
                book.setAuthor("xxx"+i);
                book.setPrice(i);
                book.setPublisher("pp"+i);
                book.setTitle("title"+i);
                dao.fastInsert(book);
            }
        }


        // 获取NutQuartzCronJobFactory从而触发计划任务的初始化与启动
        ioc.get(NutQuartzCronJobFactory.class);
    }

    public void destroy(NutConfig conf) {
    }

}