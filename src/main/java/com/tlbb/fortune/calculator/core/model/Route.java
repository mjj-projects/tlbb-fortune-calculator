package com.tlbb.fortune.calculator.core.model;

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
     * 时间
     */
    private Integer time;
}
