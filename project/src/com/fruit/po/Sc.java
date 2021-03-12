package com.fruit.po;

import java.io.Serializable;

public class Sc implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer itemId;

    /**
     * 商品对象
     */
    private Item item;

    /**
     * 收藏者id
     */
    private Integer userId;

    public Sc(Integer id, Integer itemId, Item item, Integer userId) {
        this.id = id;
        this.itemId = itemId;
        this.item = item;
        this.userId = userId;
    }

    public Sc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", item=" + item +
                ", userId=" + userId +
                '}';
    }
}
