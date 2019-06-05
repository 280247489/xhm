package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/6/5 0005 21:12
 * @Description:
 */
@Entity
public class User {
    public User() {
    }

    public User(String id, String password, String userUnionId, String userOpenId, String userTel, String userName, String userLogo, String userSex, String userBirthday, String userProvince, String userCity, String userArea, String userAddress, Date userCreateTime, int userForbidden, int userNologin, int userCancel, String parentId) {
        this.id = id;
        this.password = password;
        this.userUnionId = userUnionId;
        this.userOpenId = userOpenId;
        this.userTel = userTel;
        this.userName = userName;
        this.userLogo = userLogo;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userProvince = userProvince;
        this.userCity = userCity;
        this.userArea = userArea;
        this.userAddress = userAddress;
        this.userCreateTime = userCreateTime;
        this.userForbidden = userForbidden;
        this.userNologin = userNologin;
        this.userCancel = userCancel;
        this.parentId = parentId;
    }

    private String id;
    private String password;
    private String userUnionId;
    private String userOpenId;
    private String userTel;
    private String userName;
    private String userLogo;
    private String userSex;
    private String userBirthday;
    private String userProvince;
    private String userCity;
    private String userArea;
    private String userAddress;
    private Date userCreateTime;
    private int userForbidden;
    private int userNologin;
    private int userCancel;
    private String parentId;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "user_union_id", nullable = false, length = 255)
    public String getUserUnionId() {
        return userUnionId;
    }

    public void setUserUnionId(String userUnionId) {
        this.userUnionId = userUnionId;
    }

    @Basic
    @Column(name = "user_open_id", nullable = false, length = 255)
    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    @Basic
    @Column(name = "user_tel", nullable = false, length = 255)
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_logo", nullable = false, length = 255)
    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    @Basic
    @Column(name = "user_sex", nullable = false, length = 255)
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_birthday", nullable = false, length = 255)
    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Basic
    @Column(name = "user_province", nullable = false, length = 255)
    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    @Basic
    @Column(name = "user_city", nullable = false, length = 255)
    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    @Basic
    @Column(name = "user_area", nullable = false, length = 255)
    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    @Basic
    @Column(name = "user_address", nullable = false, length = 255)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "user_create_time", nullable = false)
    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @Basic
    @Column(name = "user_forbidden", nullable = false)
    public int getUserForbidden() {
        return userForbidden;
    }

    public void setUserForbidden(int userForbidden) {
        this.userForbidden = userForbidden;
    }

    @Basic
    @Column(name = "user_nologin", nullable = false)
    public int getUserNologin() {
        return userNologin;
    }

    public void setUserNologin(int userNologin) {
        this.userNologin = userNologin;
    }

    @Basic
    @Column(name = "user_cancel", nullable = false)
    public int getUserCancel() {
        return userCancel;
    }

    public void setUserCancel(int userCancel) {
        this.userCancel = userCancel;
    }

    @Basic
    @Column(name = "parent_id", nullable = false, length = 255)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userForbidden == user.userForbidden &&
                userNologin == user.userNologin &&
                userCancel == user.userCancel &&
                Objects.equals(id, user.id) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userUnionId, user.userUnionId) &&
                Objects.equals(userOpenId, user.userOpenId) &&
                Objects.equals(userTel, user.userTel) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userLogo, user.userLogo) &&
                Objects.equals(userSex, user.userSex) &&
                Objects.equals(userBirthday, user.userBirthday) &&
                Objects.equals(userProvince, user.userProvince) &&
                Objects.equals(userCity, user.userCity) &&
                Objects.equals(userArea, user.userArea) &&
                Objects.equals(userAddress, user.userAddress) &&
                Objects.equals(userCreateTime, user.userCreateTime) &&
                Objects.equals(parentId, user.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, userUnionId, userOpenId, userTel, userName, userLogo, userSex, userBirthday, userProvince, userCity, userArea, userAddress, userCreateTime, userForbidden, userNologin, userCancel, parentId);
    }
}
