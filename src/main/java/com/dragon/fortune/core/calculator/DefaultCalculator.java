package com.dragon.fortune.core.calculator;

import com.dragon.fortune.core.model.Map;

import java.util.List;

public class DefaultCalculator implements Calculator {

    private final List<Map> maps;

    @Override
    public void calculator() {
        for (Map map : maps) {

        }
    }

    public DefaultCalculator(List<Map> maps) {
        this.maps = maps;
    }
}
