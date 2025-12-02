package com.fortune.calculator.core.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Accessors(chain = true)
public class CalculatorConfig {
    /**
     * 城市
     */
    private Set<String> cites;
    /**
     * 初始资金
     */
    private BigDecimal initCapital;
    /**
     * 总时长
     */
    private Integer totalTime;
}
