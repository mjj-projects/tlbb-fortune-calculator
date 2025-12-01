package com.tlbb.fortune.calculator.core.model;

import lombok.Data;

import java.util.List;

/**
 * 地图
 */
@Data
public class From {
    /**
     * 城市
     */
    private String city;
    /**
     * 是否城市
     */
    private boolean isCity;
    /**
     * 目的地
     */
    private List<To> tos;
}
