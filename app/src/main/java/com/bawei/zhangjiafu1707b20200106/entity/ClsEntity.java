package com.bawei.zhangjiafu1707b20200106.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
@Entity
public class ClsEntity {
    @Id(autoincrement = true)
    Long id;
    String json;
    @Generated(hash = 484994004)
    public ClsEntity(Long id, String json) {
        this.id = id;
        this.json = json;
    }
    @Generated(hash = 1715876768)
    public ClsEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }
}
