package com.jf.shop.login.shopTeminal.responseEntity;
public class StoreNumber {

    private Integer id;
    //不同颜色对应的库存数量
    private Integer number;
    private Integer goodsId;
    //规格：包含型号和颜色
    private String specification;
    //不同颜色会有不同的价格
    private Double prices;
    public StoreNumber() {
        super();
    }
    public StoreNumber(Integer id, Integer number, Integer goodsId, String specification, Double prices) {
        super();
        this.id = id;
        this.number = number;
        this.goodsId = goodsId;
        this.specification = specification;
        this.prices = prices;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecification() {
        return this.specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getPrices() {
        return this.prices;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }

}
