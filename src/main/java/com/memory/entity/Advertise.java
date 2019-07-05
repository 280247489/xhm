package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Advertise
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/28 18:16
 */
@Entity
public class Advertise {
    private String id;
    private String advertiseName;
    private String advertiseLogo;
    private String advertiseContent;
    private int advertiseIsOnline;
    private Date advertiseCreateTime;
    private String advertiseCreateId;
    private Date advertiseUpdateTime;
    private String advertiseUpdateId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "advertise_name")
    public String getAdvertiseName() {
        return advertiseName;
    }

    public void setAdvertiseName(String advertiseName) {
        this.advertiseName = advertiseName;
    }

    @Basic
    @Column(name = "advertise_logo")
    public String getAdvertiseLogo() {
        return advertiseLogo;
    }

    public void setAdvertiseLogo(String advertiseLogo) {
        this.advertiseLogo = advertiseLogo;
    }

    @Basic
    @Column(name = "advertise_content")
    public String getAdvertiseContent() {
        return advertiseContent;
    }

    public void setAdvertiseContent(String advertiseContent) {
        this.advertiseContent = advertiseContent;
    }

    @Basic
    @Column(name = "advertise_is_online")
    public int getAdvertiseIsOnline() {
        return advertiseIsOnline;
    }

    public void setAdvertiseIsOnline(int advertiseIsOnline) {
        this.advertiseIsOnline = advertiseIsOnline;
    }

    @Basic
    @Column(name = "advertise_create_time")
    public Date getAdvertiseCreateTime() {
        return advertiseCreateTime;
    }

    public void setAdvertiseCreateTime(Date advertiseCreateTime) {
        this.advertiseCreateTime = advertiseCreateTime;
    }

    @Basic
    @Column(name = "advertise_create_id")
    public String getAdvertiseCreateId() {
        return advertiseCreateId;
    }

    public void setAdvertiseCreateId(String advertiseCreateId) {
        this.advertiseCreateId = advertiseCreateId;
    }

    @Basic
    @Column(name = "advertise_update_time")
    public Date getAdvertiseUpdateTime() {
        return advertiseUpdateTime;
    }

    public void setAdvertiseUpdateTime(Date advertiseUpdateTime) {
        this.advertiseUpdateTime = advertiseUpdateTime;
    }

    @Basic
    @Column(name = "advertise_update_id")
    public String getAdvertiseUpdateId() {
        return advertiseUpdateId;
    }

    public void setAdvertiseUpdateId(String advertiseUpdateId) {
        this.advertiseUpdateId = advertiseUpdateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertise advertise = (Advertise) o;
        return advertiseIsOnline == advertise.advertiseIsOnline &&
                Objects.equals(id, advertise.id) &&
                Objects.equals(advertiseName, advertise.advertiseName) &&
                Objects.equals(advertiseLogo, advertise.advertiseLogo) &&
                Objects.equals(advertiseContent, advertise.advertiseContent) &&
                Objects.equals(advertiseCreateTime, advertise.advertiseCreateTime) &&
                Objects.equals(advertiseCreateId, advertise.advertiseCreateId) &&
                Objects.equals(advertiseUpdateTime, advertise.advertiseUpdateTime) &&
                Objects.equals(advertiseUpdateId, advertise.advertiseUpdateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advertiseName, advertiseLogo, advertiseContent, advertiseIsOnline, advertiseCreateTime, advertiseCreateId, advertiseUpdateTime, advertiseUpdateId);
    }
}
