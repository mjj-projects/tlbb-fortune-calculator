package com.fortune.calculator.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.fortune.calculator.core.algorithm.CalculatorAlgorithm;
import com.fortune.calculator.core.algorithm.ROICalculator;
import com.fortune.calculator.core.model.From;
import com.fortune.calculator.core.model.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorUtil {

    public void calculate() {
        // 初始化
        String json = "[{\"city\":\"大理\",\"tos\":[{\"city\":\"镜湖\",\"number\":2,\"buyPrice\":11700,\"sellPrice\":14625,\"roi\":0.25,\"time\":84},{\"city\":\"苏州\",\"number\":3,\"buyPrice\":6720,\"sellPrice\":9800,\"roi\":0.46,\"time\":130},{\"city\":\"剑阁\",\"number\":2,\"buyPrice\":4050,\"sellPrice\":4590,\"roi\":0.13,\"time\":70}]},{\"city\":\"洛阳\",\"tos\":[{\"city\":\"剑阁\",\"number\":4,\"buyPrice\":11700,\"sellPrice\":14625,\"roi\":0.25,\"time\":79},{\"city\":\"大理\",\"number\":3,\"buyPrice\":7680,\"sellPrice\":11200,\"roi\":0.46,\"time\":124},{\"city\":\"太湖\",\"number\":1,\"buyPrice\":4950,\"sellPrice\":6325,\"roi\":0.28,\"time\":96}]},{\"city\":\"苏州\",\"tos\":[{\"city\":\"敦煌\",\"number\":4,\"buyPrice\":11700,\"sellPrice\":14625,\"roi\":0.25,\"time\":167},{\"city\":\"洛阳\",\"number\":3,\"buyPrice\":6600,\"sellPrice\":10500,\"roi\":0.59,\"time\":125},{\"city\":\"镜湖\",\"number\":1,\"buyPrice\":4365,\"sellPrice\":4590,\"roi\":0.05,\"time\":46}]},{\"city\":\"镜湖\",\"tos\":[{\"city\":\"太湖\",\"number\":2,\"buyPrice\":14500,\"sellPrice\":18125,\"roi\":0.25,\"time\":78},{\"city\":\"无量山\",\"number\":1,\"buyPrice\":9680,\"sellPrice\":11220,\"roi\":0.16,\"time\":53},{\"city\":\"洛阳\",\"number\":4,\"buyPrice\":9215,\"sellPrice\":14250,\"roi\":0.55,\"time\":164}]},{\"city\":\"太湖\",\"tos\":[{\"city\":\"敦煌\",\"number\":3,\"buyPrice\":12800,\"sellPrice\":18125,\"roi\":0.42,\"time\":84},{\"city\":\"无量山\",\"number\":3,\"buyPrice\":8160,\"sellPrice\":11220,\"roi\":0.38,\"time\":130},{\"city\":\"苏州\",\"number\":1,\"buyPrice\":4950,\"sellPrice\":5610,\"roi\":0.13,\"time\":70}]},{\"city\":\"无量山\",\"tos\":[{\"city\":\"太湖\",\"number\":3,\"buyPrice\":11560,\"sellPrice\":17000,\"roi\":0.47,\"time\":133},{\"city\":\"敦煌\",\"number\":3,\"buyPrice\":9600,\"sellPrice\":14000,\"roi\":0.46,\"time\":125},{\"city\":\"大理\",\"number\":1,\"buyPrice\":8500,\"sellPrice\":8670,\"roi\":0.02,\"time\":33}]},{\"city\":\"剑阁\",\"tos\":[{\"city\":\"无量山\",\"number\":2,\"buyPrice\":11875,\"sellPrice\":15625,\"roi\":0.32,\"time\":100},{\"city\":\"镜湖\",\"number\":3,\"buyPrice\":8000,\"sellPrice\":11200,\"roi\":0.4,\"time\":150},{\"city\":\"嵩山\",\"number\":3,\"buyPrice\":4950,\"sellPrice\":7140,\"roi\":0.44,\"time\":122}]},{\"city\":\"敦煌\",\"tos\":[{\"city\":\"大理\",\"number\":2,\"buyPrice\":11400,\"sellPrice\":15000,\"roi\":0.32,\"time\":95},{\"city\":\"剑阁\",\"number\":1,\"buyPrice\":7500,\"sellPrice\":7650,\"roi\":0.02,\"time\":51},{\"city\":\"洛阳\",\"number\":1,\"buyPrice\":4960,\"sellPrice\":5610,\"roi\":0.13,\"time\":45}]},{\"city\":\"嵩山\",\"tos\":[{\"city\":\"敦煌\",\"number\":2,\"buyPrice\":10625,\"sellPrice\":15625,\"roi\":0.47,\"time\":90},{\"city\":\"镜湖\",\"number\":3,\"buyPrice\":8800,\"sellPrice\":12320,\"roi\":0.4,\"time\":125},{\"city\":\"太湖\",\"number\":1,\"buyPrice\":6790,\"sellPrice\":7140,\"roi\":0.05,\"time\":53}]}]";
        Gson gson = new Gson();
        List<From> froms = gson.fromJson(json, new TypeToken<List<From>>() {}.getType());
        Map<String, From> maps = new HashMap<>(froms.size());
        for (From from : froms) {
            maps.put(from.getCity(), from);
        }
        long shipTime = 3000;
        long totalTime = 900 * 1000;
        CalculatorAlgorithm algorithm = new ROICalculator("大理", shipTime, totalTime, maps);
        List<Route> routes = algorithm.calculate();
        System.out.println(gson.toJson(routes));
    }
}
