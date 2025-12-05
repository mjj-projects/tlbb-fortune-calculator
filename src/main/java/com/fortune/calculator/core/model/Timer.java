package com.fortune.calculator.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 记时器
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Timer {
    /**
     * 名称
     */
    private String name;
    /**
     * 已用时间
     */
    private Integer usedTime;
    /**
     * 剩余时间
     */
    private Integer remainingTime;
    /**
     * 路线
     */
    private List<Route> routes;

    public void setTimer(Integer time) {
        this.usedTime += time;
        this.remainingTime -= time;
    }

    public void setTimer(Location location, Integer time) {
        this.name = location.getName();
        this.usedTime += time;
        this.remainingTime -= time;
        BigDecimal roi = location.getRoi();
        addTimer(roi);
    }

    private void addTimer(BigDecimal roi) {
        this.routes.add(new Route()
                .setName(this.name)
                .setRoi(roi)
                .setUsedTime(this.usedTime)
                .setRemainingTime(this.remainingTime));
    }

    public boolean isEnd() {
        return this.remainingTime < 0;
    }

    public Integer compareTo(Integer time) {
        return this.remainingTime.compareTo(time);
    }

    public Timer(Integer usedTime, Integer remainingTime) {
        this.name = "";
        this.usedTime = usedTime;
        this.remainingTime = remainingTime;
        this.routes = Collections.emptyList();
    }

    public Timer(String name, Integer usedTime, Integer remainingTime) {
        this.name = name;
        this.usedTime = usedTime;
        this.remainingTime = remainingTime - usedTime;
        this.routes = new ArrayList<>();
        addTimer(BigDecimal.ZERO);
    }
}
