package com.weicai.demo.pojo;

/**
 * Created by fan on 2015/9/17.
 */
import java.util.List;

import org.nutz.dao.entity.annotation.*;

/**
 * 角色表
 */
@Table("t_role")
public class Role{

    @Id
    private Integer id;
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 50)
    protected String name;
    @Column("al")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    protected String alias;
    @Column("dt")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;
    @ManyMany(from="role_id", relation="t_role_permission", target=Permission.class, to="permission_id")
    protected List<Permission> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
