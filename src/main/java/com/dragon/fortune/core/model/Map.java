package com.dragon.fortune.core.model;

import java.util.List;

public class Map {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否城市
     */
    private boolean isCity;
    /**
     * 目的地
     */
    private List<Location> locations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCity() {
        return isCity;
    }

    public void setCity(boolean city) {
        isCity = city;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
