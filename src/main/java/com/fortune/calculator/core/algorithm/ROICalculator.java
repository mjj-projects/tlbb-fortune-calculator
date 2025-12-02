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
    private final Map<String, From> maps;

    @Override
    public List<Route> calculate() {
        if (maps.containsKey(city)) {
            // 地图数量
            int size = maps.size();
            // 起点
            From from = maps.get(city);
            // 收益路线
            List<Route> routes = new ArrayList<>();
            routes.add(new Route()
                    .setCity(city)
                    .setTime(0));
            // 根据成本回报率计算最大收益路线
            while (size > 1) {
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
                // 记录最大收益路线
                routes.add(new Route()
                        .setCity(to.getCity())
                        .setTime(to.getTime()));
                // 下一条路线为起点或没有下一条路线则结束
                if (city.equals(to.getCity()) || !maps.containsKey(to.getCity())) {
                    break;
                }
                // 指定下一个起点
                from = maps.get(to.getCity());
                size--;
            }
            return routes;
        }
        return Collections.emptyList();
    }

    public ROICalculator(String city, Map<String, From> maps) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city cannot be null or empty");
        }
        if (maps == null || maps.isEmpty()) {
            throw new IllegalArgumentException("maps cannot be null or empty");
        }
        this.city = city;
        this.maps = maps;
    }
}
