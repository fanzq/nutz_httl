package com.weicai.demo.module;

import com.alibaba.druid.util.StringUtils;
import com.weicai.demo.pojo.User;
import com.weicai.demo.service.EmailService;
import com.weicai.demo.service.MyCaptchaService;
import com.weicai.demo.util.Toolkit;
import org.apache.commons.mail.HtmlEmail;
import org.nutz.dao.*;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.patchca.service.Captcha;
import org.patchca.utils.encoder.EncoderHelper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by fan on 2015/7/2.
 * <p>
 * <p>
 * 通过 @IocBy 为整个应用声明了 Ioc 容器，那么如何使用呢。 实际上，你的每一个模块都可以来自容器，只要 你在模块上声明 @InjectName。 当然，在主模块声明这个注解是没有意义的。
 *
 * @InjectName("petM") public class PetModule {
 * ...
 * 如果你声明了这个注解， Nutz.Mvc 构造你的这个模块的时候，会通过 Ioc 容器获取，而不直接调用默认构造函数了。
 * 如果你的 '@InjectName' 并没有值，那么默认会将你的模块类名首字母小写作为模块的注入名
 * 比如上例，你直接声明 '@InjectName' 同 '@InjectName("petModule")' 是等效的
 */


@IocBean
@At("/user")
@Ok("json:{locked:'password|salt',ignoreNull:true}") // 忽略password和salt属性,忽略空属性的json输出
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = {"me", "/"}))
//含义是,如果当前Session没有带me这个attr,就跳转到/页面,即首页.
//
//        同时,为login方法设置为空的过滤器,不然就没法登陆了
//

public class UserModule {
    @Inject
    protected Dao dao;
    @Inject
    private EmailService emailService;

    @At
    @Ok("json")
    public int count() {
        return dao.count(User.class);
    }

    @At("/ind")
    @Ok("jsp:page.login")
    public void indexL() {
        NutMap re = new NutMap();
    }

    @At
    @Filters()
    public boolean login(@Param("..") User user, HttpSession session, @Attr("me") int me, @Param("..") Pager pager) {
        //其中的@Attr是取Session/Request中的me属性.
        User u = dao.fetch(User.class, 1);
        boolean b = u.equals(user);
        if (b) {
            session.setAttribute("me", user.getId());
            return true;
        } else {
            return false;
        }
    }

    @At
    @Ok(">>:/")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @At
    public Object query(@Param("name") String name, @Param("..") Pager pager) {
//update user set age = age + 1 where name = "wendal";
        //   dao.update("user", Chain.makeSpecial("age", "+1"), Cnd.where("name", "=", "wendal"));
        //WHERE (name='wendal' OR name='zozoh') AND age<40
        Cnd cnd2 = Cnd.where(Cnd.exps("name", "=", "wendal").or("name", "=", "zozoh")).and("age", "<", 40);
        Cnd cnd = Strings.isBlank(name) ? null : Cnd.where("name", "like", "%" + name + "%");
        QueryResult qr = new QueryResult();
        qr.setList(dao.query(User.class, cnd, pager));
        pager.setRecordCount(dao.count(User.class, cnd));
        qr.setPager(pager);
        com.mysql.jdbc.StringUtils.isNullOrEmpty("");
        return qr; //默认分页是第1页,每页20条
    }
//另外, 密码和salt也不可以发送到浏览器去.
//    将UserModule的@Ok注解改成
//    @Ok("json:{locked:'password|salt',ignoreNull:true}")

    @At("/sendMail")
    @Filters
    public boolean tets() {
        // 测试发送邮件
        String html = "<div><p>如果无法点击,请拷贝一下链接到浏览器中打开<p/><b>验证链接</b></div>";
        return emailService.send("1043929879@qq.com", "这是个测试！", html, null);
    }

    @At("/diplay2")
    @Ok("httl:test.books2")
//	@Ok("json")
    @Filters
    public Map<String, Object> diplay2() throws SQLException {
        Map<String, Object> map = new HashMap();
        map.put("fff", 78787);
        map.put("obj2", "obj2222");
        return map;
    }

    // 验证码
    @At("/cap")
    @Filters
    @Ok("raw:png")
    public BufferedImage cap(HttpSession session, HttpServletResponse response) throws IOException {
        MyCaptchaService cs = MyCaptchaService.getInstance();
        Captcha captcha = cs.getCaptcha();
        String text = captcha.getChallenge();
        session.setAttribute("loginCap", text);
        return captcha.getImage();
    }

    //验证验证码
    @At("/val")
    @Filters
    public Object valcap(@Attr("loginCap") String s_loginCap, String u_cap, HttpSession session) throws Exception {
        if (Objects.equals(s_loginCap, u_cap)) {
            session.removeAttribute("loginCap");
            return true;
        } else {
            return false;
        }

    }

    //测试 dao 更新部分字段
    @At("/update")
    @Filters
    public int updateSome() {
        System.out.println(2389);
        System.out.println(123);
        User u = dao.fetch(User.class, 1);
        return dao.update("t_user", org.nutz.dao.Chain.make("age", "22").add("salt", "fanzq"), Cnd.where("name", "=", "wendal"));
    }


}