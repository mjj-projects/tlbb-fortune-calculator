package com.fortune.calculator.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Route {
    /**
     * 名称
     */
    private String name;
    /**
     * 回报率
     */
    private BigDecimal roi;
    /**
     * 已用时间
     */
    private Integer usedTime;
    /**
     * 剩余时间
     */
    private Integer remainingTime;
}
