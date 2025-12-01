package com.tlbb.fortune.calculator.core.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class To {
    /**
     * 城市
     */
    private String city;
    /**
     * 跑图数量
     */
    private Integer number;
    /**
     * 商品
     */
    private String product;
    /**
     * 是否推荐
     */
    private boolean isRecommend;
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
}
