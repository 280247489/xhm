package com.memory.entity.model;

import java.util.Date;

/**
 * @ClassName CmsUserGroup
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/9/2 16:15
 */
public class CmsUserGroup {
    private String id;
    private String userName;
    private String userLogo;
    private Date userCreateTime;
    private String parentIdOne;
    private String parentIdTwo;

    public CmsUserGroup() {
    }

    public CmsUserGroup(String id, String userName, String userLogo, Date userCreateTime, String parentIdOne, String parentIdTwo) {
        this.id = id;
        this.userName = userName;
        this.userLogo = userLogo;
        this.userCreateTime = userCreateTime;
        this.parentIdOne = parentIdOne;
        this.parentIdTwo = parentIdTwo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getParentIdOne() {
        return parentIdOne;
    }

    public void setParentIdOne(String parentIdOne) {
        this.parentIdOne = parentIdOne;
    }

    public String getParentIdTwo() {
        return parentIdTwo;
    }

    public void setParentIdTwo(String parentIdTwo) {
        this.parentIdTwo = parentIdTwo;
    }
}
