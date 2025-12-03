package com.fortune.calculator.core.algorithm;

import com.fortune.calculator.core.model.Location;
import com.fortune.calculator.core.model.Region;
import com.fortune.calculator.core.model.Timer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 成本回报率计算器
 */
public class ROICalculator implements CalculatorAlgorithm {
    private final String city;
    private final Long shipTime;
    private final Long totalTime;
    private final Map<String, Region> maps;

    @Override
    public List<Timer> calculate() {
        if (maps.containsKey(this.city)) {
            // 开启记时器
            Timer timer = new Timer(city, shipTime, totalTime);
            // 路线起点
            Region region = maps.get(this.city);
            // 路线集合
            List<Location> locations = region.getLocations();
            // 根据成本回报率计算收益最高路线
            while (true) {
                // 最高收益地点
                Location location = location(locations);
                // 前往地点耗时（路程+买卖）
                long durationTime = location.getTime() + shipTime;
                /*
                 * 剩余时间与耗时比较
                 * 大于：下向执行，前往收益最高地点
                 * 等于：计算原路线收益，再计算新路线收益，如果新路线比原路线收益高使用新路线，否则使用原路线
                 * 小于：不计算原路线收益，只计算新路线收益，如果新路线比原路线收益高使用新路线，否则使用原路线
                 */
                int value = timer.compareTo(durationTime);
                if (value == 0) {
                    Location nLocation = location(location.getName(), locations);
                    BigDecimal oRoi = sumRoi(location, timer);
                    BigDecimal nRoi = sumRoi(nLocation, timer);
                    if (nRoi.compareTo(oRoi) > 0) {
                        durationTime = nLocation.getTime() + shipTime;
                        location = nLocation;
                    }
                }
                if (value == -1) {
                    Location nLocation = location(location.getName(), locations);
                    BigDecimal nRoi = sumRoi(nLocation, timer);
                    if (nRoi.compareTo(location.getRoi()) > 0) {
                        durationTime = nLocation.getTime() + shipTime;
                        location = nLocation;
                    }
                }
                // 更新记时器
                timer.setTimer(location.getName(), durationTime);
                // 时间结束或未找到路线，任务结束
                if (timer.isEnd() || !maps.containsKey(location.getName())) {
                    break;
                }
                // 下一个地点
                locations = maps.get(location.getName()).getLocations();
            }
            // 路线
            return timer.getTimers();
        }
        return Collections.emptyList();
    }

    private BigDecimal sumRoi(Location location, Timer timer) {
        BigDecimal roi = location.getRoi();
        if (!maps.containsKey(location.getName())) {
            return roi;
        }
        Timer nTimer = new  Timer(timer.getUsedTime(), timer.getRemainingTime());
        long durationTime = location.getTime() + shipTime;
        nTimer.setTimer(durationTime);
        if (nTimer.isEnd()) {
            return roi;
        }
        List<Location> locations = maps.get(location.getName()).getLocations();
        // 模拟路线计算收益
        while (true) {
            Location nLocation = location(locations);
            durationTime = nLocation.getTime() + shipTime;
            nTimer.setTimer(durationTime);
            roi = roi.add(nLocation.getRoi());
            if (nTimer.isEnd() || !maps.containsKey(location.getName())) {
                return roi;
            }
            locations = maps.get(location.getName()).getLocations();
        }
    }

    private Location location(List<Location> locations) {
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

    private Location location(String name, List<Location> locations) {
        int index = 0;
        BigDecimal roi = BigDecimal.ZERO;
        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            if (name.equals(location.getName())) {
                continue;
            }
            if (location.getRoi().compareTo(roi) > 0) {
                index = i;
                roi = location.getRoi();
            }
        }
        return locations.get(index);
    }

    public ROICalculator(String city, Long shipTime, Long totalTime, Map<String, Region> map) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city cannot be null or blank");
        }
        if (shipTime == null) {
            throw new IllegalArgumentException("shipTime cannot be null");
        }
        if (totalTime == null) {
            throw new IllegalArgumentException("totalTime cannot be null");
        }
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("map cannot be null or empty");
        }
        this.city = city;
        this.shipTime = shipTime;
        this.totalTime = totalTime;
        this.maps = map;
    }
}
