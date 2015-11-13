package com.weicai.demo.module;

import com.weicai.demo.pojo.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

/**
 * Created by fan on 2015/9/15.
 */
public class DemoModule {
// 从session中取值 @Attr(scope=Scope.SESSION, value="me")int userId
    //直接取 @Attr("me")int me)




//-----1
//  service层extends IdEntityService<T>     dao 操作事不用再指定查询的对象
//    如果 POJO 即声明了 @Id 又声明了 @Name，那么适合采用 IdNameEntityService
//    如果 POJO 仅声明了 @Id，那么适合采用 IdEntityService
//    如果 POJO 仅声明了 @Name，那么适合采用 NameEntityService
//    如果 POJO 即没声明了 @Id 又没声明了 @Name，那么适合采用 EntityService
//    Pet pet = dao().fetch(Pet.class,"XiaoBai"); 原
//    Pet pet = dao().fetch("XiaoBai");   后

//--------2
//module层Ioc注入
//@Inject
//private ArticleService articleService;

//    --------------3
//dao查询
//----3.1过滤字段
//    Daos.ext(dao, FieldFilter.create(Pet.class, "^id|name$")).update(pet);只更新 Pet 的 id 和 name 字段：
//    dao().update(user, "^(password|salt|updateTime)$");
//    ---3.2拼装复杂sql
//    // 创建一个 Criteria 接口实例
//    Criteria cri = Cnd.cri();
//
//// 组装条件
//    if(...){
//        cri.where().andIn("id", 3,4,5).andIn("name", "Peter", "Wendal", "Juqkai");
//    }else if(...){
//        cri.where().andLT("id", 9);
//    }
//    if(...){
//        cri.where().andLike("name", "%A%");
//    }
//    cri.getOrderBy().asc("name").desc("id");
//    // 执行查询
//    List<MyObj> list = dao.query(MyObj.class, cri, null);

//    ---------3.3分页查询
/*@At
public Object query(@Param("name")String name, @Param("..")Pager pager) {
//update user set age = age + 1 where name = "wendal";
    //   dao.update("user", Chain.makeSpecial("age", "+1"), Cnd.where("name", "=", "wendal"));
    //WHERE (name='wendal' OR name='zozoh') AND age<40
    Cnd cnd2 = Cnd.where(Cnd.exps("name", "=", "wendal").or("name","=","zozoh")).and("age","<",40);
    Cnd cnd = Strings.isBlank(name)? null : Cnd.where("name", "like", "%"+name+"%");
    QueryResult qr = new QueryResult();
    qr.setList(dao.query(User.class, cnd, pager));
    pager.setRecordCount(dao.count(User.class, cnd));
    qr.setPager(pager);
    com.mysql.jdbc.StringUtils.isNullOrEmpty("");
    return qr; //默认分页是第1页,每页20条
}*/
}
