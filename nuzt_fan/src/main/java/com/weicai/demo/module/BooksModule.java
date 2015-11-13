package com.weicai.demo.module;

import com.weicai.demo.pojo.Book;
import org.nutz.dao.Cnd;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.service.IdEntityService;
import org.nutz.trans.Atom;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@IocBean(fields = { "dao" })
//@IocBean(args = { "refer:dao" })
public class BooksModule extends IdEntityService<Book> {
	@At("/diplay")
	@Ok("httl:test.books")
	public Map<String, Object>  diplayF() throws SQLException {
		Map<String, Object> map = new HashMap();
		List<Book> books = this.dao().query(Book.class, null, null);
		map.put("books", books);
		return map;
	}
	@At("/diplay2")
	@Ok("httl:test.books2")
//	@Ok("json")
	public Map<String, Object>  diplay2() throws SQLException {
		Map<String, Object> map = new HashMap();
		map.put("fff", 78787);
		map.put("obj2", "obj2222");
		return map;
	}
	@At("/diplay5")
	@Ok("json")
	public void  diplay5() throws SQLException {
//		List<Book> books =this.dao().query(Book.class, Cnd.wrap("id=18"));
		List<Book> books =this.dao().query(Book.class, Cnd.where("id", "in", "18,19,20"));
		if(!books.isEmpty())
			System.out.println(books.get(0));
//更新部分字段   匿名内部类
		FieldFilter.create(Book.class, "^id|title").run(new Atom(){
			public void run(){
				// TODO 你的 DAO 操作代码
			}
		});

		//原写法
		FieldFilter.create(Book.class, "^id|name$").run(new Atom(){
			public void run(){
			//	this.dao().update(pet); // 这里的pet必须是final
			}
		});
// 新的写法
		Book b=this.dao().fetch(Book.class,2);
		b.setPrice(12);
		Daos.ext(this.dao(), FieldFilter.create(Book.class, "^id|name$")).update(b);
// ext方法会返回一个Dao实例,是对原有dao对象的封装
		
		
	}

}
