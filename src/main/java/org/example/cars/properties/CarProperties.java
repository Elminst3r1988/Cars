package org.example.cars.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@ConfigurationProperties(prefix = "car")
public class CarProperties {

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
