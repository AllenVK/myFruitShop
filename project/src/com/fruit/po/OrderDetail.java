package com.fruit.po;

import java.io.Serializable;

/**
 * 订单详情 Dao 层（mapper层）
 * 商品详情表
 * 订单表与商品表的关联表实体类
 */
public class OrderDetail implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer itemId;

    /**
     * order_detail表查询 item
     * 要想实现关联查询，这里需要引入 item 表中的实体
     */
    private Item item;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单状态
     * 0 未退货
     * 1 已退货
     */
    private Integer status;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 小计
     */
    private String total;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, Integer itemId, Item item, Integer orderId, Integer status, Integer num, String total) {
        this.id = id;
        this.itemId = itemId;
        this.item = item;
        this.orderId = orderId;
        this.status = status;
        this.num = num;
        this.total = total;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", item=" + item +
                ", orderId=" + orderId +
                ", status=" + status +
                ", num=" + num +
                ", total='" + total + '\'' +
                '}';
    }
}
