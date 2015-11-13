package com.weicai.demo.service;

/**
 * Created by fan on 2015/9/17.
 */
import java.util.Date;


import com.weicai.demo.pojo.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.random.R;
import org.nutz.service.IdNameEntityService;

@IocBean(fields="dao")
public class UserService extends IdNameEntityService<User> {

    public User add(String name, String password) {
        User user = new User();
        user.setName(name.trim());
        user.setSalt(R.UU16());
        user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
        return dao().insert(user);
    }

    public int fetch(String username, String password) {
        User user = fetch(username);
        if (user == null) {
            return -1;
        }
        String _pass = new Sha256Hash(password, user.getSalt()).toHex();
        if(_pass.equalsIgnoreCase(user.getPassword())) {
            return user.getId();
        }
        return -1;
    }

    public void updatePassword(int userId, String password) {
        User user = fetch(userId);
        if (user == null) {
            return;
        }
        user.setSalt(R.UU16());
        user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
        //update user set age = age + 1 where name = "wendal";
           dao().update("t_user", Chain.makeSpecial("age", "+1"), Cnd.where("name", "=", "wendal"));
           dao().update("t_user",Chain.make("age","22").add("salt","fanzq"),Cnd.where("name", "=", "wendal"));
           dao().update(user, "^(password|salt|updateTime)$");
    }
}