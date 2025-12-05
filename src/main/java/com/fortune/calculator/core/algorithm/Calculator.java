package com.fortune.calculator.core.algorithm;

import com.fortune.calculator.core.model.Route;
import com.fortune.calculator.core.model.Timer;

import java.util.List;

public interface Calculator {

    List<Route> calculate(String city);
}
