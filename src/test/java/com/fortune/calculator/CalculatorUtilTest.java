package com.fortune.calculator;

import com.fortune.calculator.core.algorithm.Calculator;
import com.fortune.calculator.core.algorithm.MaxROICalculator;
import com.fortune.calculator.core.config.CalculatorConfig;
import com.fortune.calculator.core.model.City;
import com.fortune.calculator.core.model.Timer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorUtilTest {

    // 根据成本回报率计算路线
    public static void main(String[] args) {
        // 初始化
        String data = "[{\"name\":\"大理\",\"locations\":[{\"name\":\"镜湖\",\"number\":2,\"buyPrice\":11700,\"sellPrice\":14625,\"roi\":0.25,\"time\":84},{\"name\":\"苏州\",\"number\":3,\"buyPrice\":6720,\"sellPrice\":9800,\"roi\":0.46,\"time\":130},{\"name\":\"剑阁\",\"number\":2,\"buyPrice\":4050,\"sellPrice\":4590,\"roi\":0.13,\"time\":70}]},{\"name\":\"洛阳\",\"locations\":[{\"name\":\"剑阁\",\"number\":4,\"buyPrice\":11700,\"sellPrice\":14625,\"roi\":0.25,\"time\":79},{\"name\":\"大理\",\"number\":3,\"buyPrice\":7680,\"sellPrice\":11200,\"roi\":0.46,\"time\":124},{\"name\":\"太湖\",\"number\":1,\"buyPrice\":4950,\"sellPrice\":6325,\"roi\":0.28,\"time\":96}]},{\"name\":\"苏州\",\"locations\":[{\"name\":\"敦煌\",\"number\":4,\"buyPrice\":11700,\"sellPrice\":14625,\"roi\":0.25,\"time\":167},{\"name\":\"洛阳\",\"number\":3,\"buyPrice\":6600,\"sellPrice\":10500,\"roi\":0.59,\"time\":125},{\"name\":\"镜湖\",\"number\":1,\"buyPrice\":4365,\"sellPrice\":4590,\"roi\":0.05,\"time\":46}]},{\"name\":\"镜湖\",\"locations\":[{\"name\":\"太湖\",\"number\":2,\"buyPrice\":14500,\"sellPrice\":18125,\"roi\":0.25,\"time\":78},{\"name\":\"无量山\",\"number\":1,\"buyPrice\":9680,\"sellPrice\":11220,\"roi\":0.16,\"time\":53},{\"name\":\"洛阳\",\"number\":4,\"buyPrice\":9215,\"sellPrice\":14250,\"roi\":0.55,\"time\":164}]},{\"name\":\"太湖\",\"locations\":[{\"name\":\"敦煌\",\"number\":3,\"buyPrice\":12800,\"sellPrice\":18125,\"roi\":0.42,\"time\":84},{\"name\":\"无量山\",\"number\":3,\"buyPrice\":8160,\"sellPrice\":11220,\"roi\":0.38,\"time\":130},{\"name\":\"苏州\",\"number\":1,\"buyPrice\":4950,\"sellPrice\":5610,\"roi\":0.13,\"time\":70}]},{\"name\":\"无量山\",\"locations\":[{\"name\":\"太湖\",\"number\":3,\"buyPrice\":11560,\"sellPrice\":17000,\"roi\":0.47,\"time\":133},{\"name\":\"敦煌\",\"number\":3,\"buyPrice\":9600,\"sellPrice\":14000,\"roi\":0.46,\"time\":125},{\"name\":\"大理\",\"number\":1,\"buyPrice\":8500,\"sellPrice\":8670,\"roi\":0.02,\"time\":33}]},{\"name\":\"剑阁\",\"locations\":[{\"name\":\"无量山\",\"number\":2,\"buyPrice\":11875,\"sellPrice\":15625,\"roi\":0.32,\"time\":100},{\"name\":\"镜湖\",\"number\":3,\"buyPrice\":8000,\"sellPrice\":11200,\"roi\":0.4,\"time\":150},{\"name\":\"嵩山\",\"number\":3,\"buyPrice\":4950,\"sellPrice\":7140,\"roi\":0.44,\"time\":122}]},{\"name\":\"敦煌\",\"locations\":[{\"name\":\"大理\",\"number\":2,\"buyPrice\":11400,\"sellPrice\":15000,\"roi\":0.32,\"time\":95},{\"name\":\"剑阁\",\"number\":1,\"buyPrice\":7500,\"sellPrice\":7650,\"roi\":0.02,\"time\":51},{\"name\":\"洛阳\",\"number\":1,\"buyPrice\":4960,\"sellPrice\":5610,\"roi\":0.13,\"time\":45}]},{\"name\":\"嵩山\",\"locations\":[{\"name\":\"敦煌\",\"number\":2,\"buyPrice\":10625,\"sellPrice\":15625,\"roi\":0.47,\"time\":90},{\"name\":\"镜湖\",\"number\":3,\"buyPrice\":8800,\"sellPrice\":12320,\"roi\":0.4,\"time\":125},{\"name\":\"太湖\",\"number\":1,\"buyPrice\":6790,\"sellPrice\":7140,\"roi\":0.05,\"time\":53}]}]";
        Gson gson = new Gson();
        List<City> list = gson.fromJson(data, new TypeToken<List<City>>() {
        }.getType());
        Map<String, City> cites = new HashMap<>(list.size());
        for (City city : list) {
            cites.put(city.getName(), city);
        }
        CalculatorConfig config = new CalculatorConfig()
                .setCites(cites)
                .setShipTime(3)
                .setTotalTime(900)
                .setInitCapital(BigDecimal.valueOf(40000));
        Calculator calculator = new MaxROICalculator(config);
        List<Timer> timers = calculator.calculate("大理");
        System.out.println(gson.toJson(timers));
    }
}
