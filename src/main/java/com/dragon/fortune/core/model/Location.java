package com.dragon.fortune.core.model;

import java.math.BigDecimal;

public class Location {
    /**
     * 是否推荐
     */
    private boolean is;
    /**
     * 目的地
     */
    private String to;
    /**
     * 跑图数量
     */
    private Integer number;
    /**
     * 商品
     */
    private String product;
    /**
     * 买入价
     */
    private BigDecimal buyPrice;
    /**
     * 卖出价
     */
    private BigDecimal sellPrice;
    /**
     * 成本回报率
     */
    private BigDecimal roi;
    /**
     * 时间
     */
    private Integer time;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getRoi() {
        return roi;
    }

    public void setRoi(BigDecimal roi) {
        this.roi = roi;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
