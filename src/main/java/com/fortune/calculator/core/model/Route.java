package com.fortune.calculator.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Route {
    /**
     * 城市
     */
    private String city;
    /**
     * 使用时间
     */
    private Long usedTime;
    /**
     * 剩余时间
     */
    private Long remainingTime;
}
