package com.jf.shop.login.shopTeminal.responseEntity;

import lombok.Data;

@Data
public class SkimGoods {

    private Integer id;
    //商店id
    private String storeId;
    //商品名字
    private String goodsName;
    private java.util.Date updateTime;
    //商品创建时间
    private java.util.Date createTime;
    //商品描述
    private String description;
//    private java.util.List<StoreNumber> storeNumber;



    public Object[] toArray() {
        Object[] array = new Object[7];
        array[0] = id;
        array[1] = storeId;
        array[2] = goodsName;
        array[3] = updateTime;
        array[4] = createTime;
        array[5] = description;
        return array;
    }

    @Override
    public String toString() {
        return "SkimGoods{" +
                "id=" + id +
                ", storeId='" + storeId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                '}';
    }
}
