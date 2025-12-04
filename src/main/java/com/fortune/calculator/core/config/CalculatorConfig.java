package com.fortune.calculator.core.config;

import com.fortune.calculator.core.model.City;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Accessors(chain = true)
public class CalculatorConfig {
    /**
     * 买卖时间
     */
    private Integer shipTime;
    /**
     * 总时长
     */
    private Integer totalTime;
    /**
     * 初始资金
     */
    private BigDecimal initCapital;
    /**
     * 城市
     */
    private Map<String, City> cites;
}
