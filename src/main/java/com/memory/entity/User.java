package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author INS6+
 * @date 2019/7/23 19:35
 */

@Entity
public class User {
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
    private int userFollow;
    private int userFans;
    private int userLike;
    private int userGroup;
    private double userIntegral;
    private int userArticles;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "user_union_id")
    public String getUserUnionId() {
        return userUnionId;
    }

    public void setUserUnionId(String userUnionId) {
        this.userUnionId = userUnionId;
    }

    @Basic
    @Column(name = "user_open_id")
    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    @Basic
    @Column(name = "user_tel")
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_logo")
    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    @Basic
    @Column(name = "user_sex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_birthday")
    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Basic
    @Column(name = "user_province")
    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    @Basic
    @Column(name = "user_city")
    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    @Basic
    @Column(name = "user_area")
    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    @Basic
    @Column(name = "user_address")
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "user_create_time")
    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @Basic
    @Column(name = "user_forbidden")
    public int getUserForbidden() {
        return userForbidden;
    }

    public void setUserForbidden(int userForbidden) {
        this.userForbidden = userForbidden;
    }

    @Basic
    @Column(name = "user_nologin")
    public int getUserNologin() {
        return userNologin;
    }

    public void setUserNologin(int userNologin) {
        this.userNologin = userNologin;
    }

    @Basic
    @Column(name = "user_cancel")
    public int getUserCancel() {
        return userCancel;
    }

    public void setUserCancel(int userCancel) {
        this.userCancel = userCancel;
    }

    @Basic
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "user_follow")
    public int getUserFollow() {
        return userFollow;
    }

    public void setUserFollow(int userFollow) {
        this.userFollow = userFollow;
    }

    @Basic
    @Column(name = "user_fans")
    public int getUserFans() {
        return userFans;
    }

    public void setUserFans(int userFans) {
        this.userFans = userFans;
    }

    @Basic
    @Column(name = "user_like")
    public int getUserLike() {
        return userLike;
    }

    public void setUserLike(int userLike) {
        this.userLike = userLike;
    }

    @Basic
    @Column(name = "user_group")
    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    @Basic
    @Column(name = "user_integral")
    public double getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(double userIntegral) {
        this.userIntegral = userIntegral;
    }

    @Basic
    @Column(name = "user_articles")
    public int getUserArticles() {
        return userArticles;
    }

    public void setUserArticles(int userArticles) {
        this.userArticles = userArticles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userForbidden == user.userForbidden &&
                userNologin == user.userNologin &&
                userCancel == user.userCancel &&
                userFollow == user.userFollow &&
                userFans == user.userFans &&
                userLike == user.userLike &&
                userGroup == user.userGroup &&
                Double.compare(user.userIntegral, userIntegral) == 0 &&
                userArticles == user.userArticles &&
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
        return Objects.hash(id, password, userUnionId, userOpenId, userTel, userName, userLogo, userSex, userBirthday, userProvince, userCity, userArea, userAddress, userCreateTime, userForbidden, userNologin, userCancel, parentId, userFollow, userFans, userLike, userGroup, userIntegral, userArticles);
    }
}
