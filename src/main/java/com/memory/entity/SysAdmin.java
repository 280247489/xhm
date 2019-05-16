package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 10:20
 * @Description:
 */
@Entity
@Table(name = "sys_admin", schema = "xhm_db", catalog = "")
public class SysAdmin {
    private String id;
    private String loginname;
    private String password;
    private String logo;
    private String name;
    private String sex;
    private Date birthday;
    private String tel;
    private String email;
    private String address;
    private Date createTime;
    private int nologin;

    public SysAdmin(String id, String loginname, String password, String logo, String name, String sex, Date birthday, String tel, String email, String address, Date createTime, int nologin) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.logo = logo;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.createTime = createTime;
        this.nologin = nologin;
    }

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "loginname", nullable = false, length = 255)
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "logo", nullable = false, length = 255)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex", nullable = false, length = 255)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "tel", nullable = false, length = 255)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "nologin", nullable = false)
    public int getNologin() {
        return nologin;
    }

    public void setNologin(int nologin) {
        this.nologin = nologin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysAdmin sysAdmin = (SysAdmin) o;
        return nologin == sysAdmin.nologin &&
                Objects.equals(id, sysAdmin.id) &&
                Objects.equals(loginname, sysAdmin.loginname) &&
                Objects.equals(password, sysAdmin.password) &&
                Objects.equals(logo, sysAdmin.logo) &&
                Objects.equals(name, sysAdmin.name) &&
                Objects.equals(sex, sysAdmin.sex) &&
                Objects.equals(birthday, sysAdmin.birthday) &&
                Objects.equals(tel, sysAdmin.tel) &&
                Objects.equals(email, sysAdmin.email) &&
                Objects.equals(address, sysAdmin.address) &&
                Objects.equals(createTime, sysAdmin.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginname, password, logo, name, sex, birthday, tel, email, address, createTime, nologin);
    }
}
