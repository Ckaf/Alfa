package com.Alfa.tools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
@SpringBootApplication
public class JsonParser {
    private @Value("${feign.client.setting.relative.currency:RUB}")
    String currency;
    public Float getRelativeCurrency(Map map) {
        Map value = (Map) map.get("rates");
        return Float.valueOf(String.valueOf(value.get(currency)));
    }

    public boolean isRankHigherToday(Map yesterday, Map today) {
        if (getRelativeCurrency(yesterday) >= getRelativeCurrency(today)) return false;
        else return true;
    }
}
