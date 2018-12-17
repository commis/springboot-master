package com.commis.dao.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user", schema = "springboot", catalog = "")
public class UserBean {

    private int id;
    private String cnname;
    private String username;
    private String password;
    private String telephone;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cnname")
    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return username + telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserBean userBean = (UserBean) o;
        return id == userBean.id &&
            Objects.equals(cnname, userBean.cnname) &&
            Objects.equals(username, userBean.username) &&
            Objects.equals(password, userBean.password) &&
            Objects.equals(telephone, userBean.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnname, username, password, telephone);
    }
}
