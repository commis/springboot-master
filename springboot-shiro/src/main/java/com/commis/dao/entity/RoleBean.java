package com.commis.dao.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_tole", schema = "springboot", catalog = "")
public class RoleBean {

    private int id;
    private String name;
    private int roleLevel;
    private String description;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "role_level")
    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleBean toleBean = (RoleBean) o;
        return id == toleBean.id &&
            roleLevel == toleBean.roleLevel &&
            Objects.equals(name, toleBean.name) &&
            Objects.equals(description, toleBean.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roleLevel, description);
    }
}
