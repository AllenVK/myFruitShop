package com.fruit.po;

import java.io.Serializable;

/**
 * 购物车
 */
public class Car implements Serializable {

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
     * 用户id
     */
    private Integer userId;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 商品单价
     */
    private double price;

    /**
     * 商品总价
     */
    private String total;

    public Car() {
    }

    public Car(Integer id, Item item,Integer itemId, Integer userId, Integer num, double price, String total) {
        this.id = id;
        this.item = item;
        this.itemId = itemId;
        this.userId = userId;
        this.num = num;
        this.price = price;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItemId(Item item) {
        this.item = item;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                "item=" + item +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", num=" + num +
                ", price=" + price +
                ", total='" + total + '\'' +
                '}';
    }
}
