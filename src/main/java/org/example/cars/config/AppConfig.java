package org.example.cars.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "car")
public class AppConfig {

    private int maxCars;
    private Map<String, String[]> sorting;

    public void setMaxCars(int maxCount) {
        this.maxCars = maxCount;
    }

    public void setSorting(Map<String, String[]> sorting) {
        this.sorting = sorting;
    }

    public int getMaxCars() {
        return maxCars;
    }

    public Map<String, String[]> getSorting() {
        return sorting;
    }
}
