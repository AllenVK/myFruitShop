package com.fruit.po;

import java.io.Serializable;
import java.util.List;

/**
 * 一级类目和它的二级类目列表
 */
public class CategoryDto implements Serializable {

    private ItemCategory father;

    private List<ItemCategory> childrens;

    public ItemCategory getFather() {
        return father;
    }

    public void setFather(ItemCategory father) {
        this.father = father;
    }

    public List<ItemCategory> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<ItemCategory> childrens) {
        this.childrens = childrens;
    }
}
