package com.memory.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 10:20
 * @Description:
 */
@Entity
@Table(name = "user_log", schema = "xhm_db", catalog = "")
public class UserLog {
    private String id;
    private String userId;
    private String logIp;
    private String logType;
    private String logMobile;
    private Timestamp logTime;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 255)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "log_ip", nullable = false, length = 255)
    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    @Basic
    @Column(name = "log_type", nullable = false, length = 255)
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "log_mobile", nullable = false, length = 255)
    public String getLogMobile() {
        return logMobile;
    }

    public void setLogMobile(String logMobile) {
        this.logMobile = logMobile;
    }

    @Basic
    @Column(name = "log_time", nullable = false)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLog userLog = (UserLog) o;
        return Objects.equals(id, userLog.id) &&
                Objects.equals(userId, userLog.userId) &&
                Objects.equals(logIp, userLog.logIp) &&
                Objects.equals(logType, userLog.logType) &&
                Objects.equals(logMobile, userLog.logMobile) &&
                Objects.equals(logTime, userLog.logTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, logIp, logType, logMobile, logTime);
    }
}
