package com.fortune.calculator;

import com.fortune.calculator.core.config.CalculatorConfig;
import com.fortune.calculator.util.CalculatorUtil;

import java.math.BigDecimal;
import java.util.Set;

public class CalculatorUtilTest {

    // 根据成本回报率计算路线
    public static void main(String[] args) {
        CalculatorConfig config = new CalculatorConfig()
                .setTotalTime(900L)
                .setCites(Set.of("大理、苏州、洛阳"))
                .setInitCapital(BigDecimal.valueOf(40000));
        new CalculatorUtil().calculate(config);
    }
}
