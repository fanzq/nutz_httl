package com.weicai.demo.pojo;

import org.nutz.dao.entity.annotation.*;
import sun.plugin.util.UserProfile;

import java.util.List;

/**
 * Created by fan on 2015/7/2.
 */
@Table("t_user")
public class User {
    @Id
    protected int id;
    @Name
    @ColDefine(width=50)
    protected String name;
    @Column("passwd")
    @ColDefine(width=20)
    protected String password;
    @Column
    @ColDefine(width=20)
    protected String salt;
    @Column
    @ColDefine(type= ColType.INT, width=2)//
    protected Integer age;
    @Column
    private boolean locked;
    @ManyMany(from="u_id", relation="t_user_role", target=Role.class, to="role_id")
    protected List<Role> roles;
    @ManyMany(from="u_id", relation="t_user_permission", target=Permission.class, to="permission_id")
    protected List<Permission> permissions;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
