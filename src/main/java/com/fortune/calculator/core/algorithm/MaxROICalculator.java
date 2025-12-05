package com.fortune.calculator.core.algorithm;

import com.fortune.calculator.core.config.CalculatorConfig;
import com.fortune.calculator.core.model.City;
import com.fortune.calculator.core.model.Location;
import com.fortune.calculator.core.model.Timer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据最高回报率计算路线
 */
public class MaxROICalculator implements Calculator {
    private final CalculatorConfig config;

    @Override
    public List<Timer> calculate(String city) {
        Map<String, City> cites = config.getCites();
        if (!cites.containsKey(city)) {
            return Collections.emptyList();
        }
        Integer shipTime = config.getShipTime();
        Integer totalTime = config.getTotalTime();
        Timer timer = new Timer(city, shipTime, totalTime);
        List<Location> locations = cites.get(city).getLocations();
        while (true) {
            Location location = maxRoiLocation(locations);
            int durationTime = location.getTime() + shipTime;
            Integer result = timer.compareTo(durationTime);
            if (result <= 0) {
                // 模拟路线，计算总收益最高路线
                Timer newTimer = new Timer(timer.getUsedTime(), timer.getRemainingTime());
                location = simulateRoute(locations, newTimer);
                durationTime = location.getTime() + shipTime;
            }
            timer.setTimer(location, durationTime);
            if (timer.isEnd() || !cites.containsKey(location.getName())) {
                break;
            }
            // 下一个地点
            locations = cites.get(location.getName()).getLocations();
        }
        return timer.getRoutes();
    }

    private Location simulateRoute(List<Location> locations, Timer timer) {
        Integer shipTime = config.getShipTime();
        Map<String, City> cites = config.getCites();
        Map<Integer, BigDecimal> rois = new HashMap<>(locations.size());
        for (int index = 0; index < locations.size(); index++) {
            int durationTime = locations.get(index).getTime() + shipTime;
            rois.put(index, locations.get(index).getRoi());
            timer.setTimer(durationTime);
            if (timer.isEnd() || !cites.containsKey(locations.get(index).getName())) {
                continue;
            }
            List<Location> list = cites.get(locations.get(index).getName()).getLocations();
            while (true) {
                Location location = maxRoiLocation(list);
                rois.put(index, rois.get(index).add(location.getRoi()));
                timer.setTimer(location.getTime() + shipTime);
                if (timer.isEnd() || !cites.containsKey(location.getName())) {
                    break;
                }
                list = cites.get(location.getName()).getLocations();
            }
        }
        Integer index = maxRoiLocationIndex(rois);
        return locations.get(index);
    }

    private Location maxRoiLocation(List<Location> locations) {
        int index = 0;
        BigDecimal roi = BigDecimal.ZERO;
        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            if (location.getRoi().compareTo(roi) > 0) {
                index = i;
                roi = location.getRoi();
            }
        }
        return locations.get(index);
    }

    private Integer maxRoiLocationIndex(Map<Integer, BigDecimal> rois) {
        int index = 0;
        BigDecimal roi = BigDecimal.ZERO;
        for (Map.Entry<Integer, BigDecimal> entry : rois.entrySet()) {
            if (entry.getValue().compareTo(roi) > 0) {
                index = entry.getKey();
                roi = entry.getValue();
            }
        }
        return index;
    }

    public MaxROICalculator(CalculatorConfig config) {
        if (config == null) {
            throw new NullPointerException("config can't be null");
        }
        if (config.getShipTime() == null) {
            throw new IllegalArgumentException("shipTime can't be null");
        }
        if (config.getTotalTime() == null) {
            throw new IllegalArgumentException("totalTime can't be null");
        }
        if (config.getCites() == null || config.getCites().isEmpty()) {
            throw new IllegalArgumentException("cites can't be null or empty");
        }
        this.config = config;
    }
}
