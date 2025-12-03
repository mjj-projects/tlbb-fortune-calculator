package com.fortune.calculator.core.model;

import lombok.Data;

import java.util.List;

/**
 * 地区
 */
@Data
public class Region {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否城市
     */
    private boolean isCity;
    /**
     * 地点集合
     */
    private List<Location> locations;
}
