package com.fortune.calculator.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private Long usedTime;
    /**
     * 剩余时间
     */
    private Long remainingTime;
    /**
     * 记时器快照
     */
    private List<Timer> timers;

    public void setTimer(Long time) {
        this.usedTime += time;
        this.remainingTime -= time;
    }

    public void setTimer(String name, Long time) {
        this.name = name;
        this.usedTime += time;
        this.remainingTime -= time;
        // 记录快照
        addTimer();
    }

    private void addTimer() {
        this.timers.add(new Timer()
                .setName(this.name)
                .setUsedTime(this.usedTime)
                .setRemainingTime(this.remainingTime));
    }

    public boolean isEnd() {
        return this.remainingTime < 0;
    }

    public int compareTo(long time) {
        return this.remainingTime > time ? 1 : this.remainingTime == time ? 0 : -1;
    }

    public Timer(Long usedTime, Long remainingTime) {
        this.name = "";
        this.usedTime = usedTime;
        this.remainingTime = remainingTime;
        this.timers = Collections.emptyList();
    }

    public Timer(String name, Long usedTime, Long remainingTime) {
        this.name = name;
        this.usedTime = usedTime;
        this.remainingTime = remainingTime - usedTime;
        this.timers = new ArrayList<>();
        // 记录快照
        addTimer();
    }
}
