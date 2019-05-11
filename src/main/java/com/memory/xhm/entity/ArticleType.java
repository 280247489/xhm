package com.memory.xhm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 10:20
 * @Description:
 */
@Entity
@Table(name = "article_type", schema = "xhm_db", catalog = "")
public class ArticleType {
    private int id;
    private String typeName;
    private Date typeCreateTime;
    private int typeSort;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name", nullable = false, length = 255)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "type_create_time", nullable = false)
    public Date getTypeCreateTime() {
        return typeCreateTime;
    }

    public void setTypeCreateTime(Date typeCreateTime) {
        this.typeCreateTime = typeCreateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleType that = (ArticleType) o;
        return id == that.id &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(typeCreateTime, that.typeCreateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, typeCreateTime);
    }

    @Basic
    @Column(name = "type_sort", nullable = false)
    public int getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(int typeSort) {
        this.typeSort = typeSort;
    }
}
