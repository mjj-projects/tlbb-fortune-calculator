package com.fortune.calculator.core.algorithm;

import com.fortune.calculator.core.model.From;
import com.fortune.calculator.core.model.Route;
import com.fortune.calculator.core.model.To;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private final Map<String, From> maps;

    @Override
    public List<Route> calculate() {
        if (maps.containsKey(city)) {
            // 记时器
            long timer = 0;
            // 起点
            From from = maps.get(city);
            // 记时（首次购买）
            timer += shipTime;
            // 收益路线
            List<Route> routes = new ArrayList<>();
            routes.add(new Route()
                    .setCity(city)
                    .setUsedTime(timer / 1000)
                    .setRemainingTime((totalTime - timer) / 1000));
            // 根据成本回报率计算最大收益路线
            while (true) {
                List<To> tos = from.getTos();
                int index = 0;
                BigDecimal roi = BigDecimal.ZERO;
                for (int i = 0; i < tos.size(); i++) {
                    To to = tos.get(i);
                    if (to.getRoi().compareTo(roi) > 0) {
                        index = i;
                        roi = to.getRoi();
                    }
                }
                // 最大收益路线
                To to = tos.get(index);
                // 使用时间（路程时间+买/卖时间）
                long usedTime = to.getTime() * 1000 + shipTime;
                // 记时
                timer += usedTime;
                // 记录最大收益路线
                routes.add(new Route()
                        .setCity(to.getCity())
                        .setUsedTime(usedTime / 1000)
                        .setRemainingTime((totalTime - timer) / 1000));
                // 时间不足或没有下一条路线，则结束
                if (totalTime <= timer || !maps.containsKey(to.getCity())) {
                    break;
                }
                // 指定下一个起点
                from = maps.get(to.getCity());
            }
            return routes;
        }
        return Collections.emptyList();
    }

    public ROICalculator(String city, Long shipTime, Long totalTime, Map<String, From> maps) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city cannot be null or empty");
        }
        if (shipTime == null) {
            throw new IllegalArgumentException("shipTime cannot be null");
        }
        if (totalTime == null) {
            throw new IllegalArgumentException("totalTime cannot be null");
        }
        if (maps == null || maps.isEmpty()) {
            throw new IllegalArgumentException("maps cannot be null or empty");
        }
        this.city = city;
        this.shipTime = shipTime;
        this.totalTime = totalTime;
        this.maps = maps;
    }
}
