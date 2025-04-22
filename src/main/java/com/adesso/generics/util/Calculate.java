package com.adesso.generics.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculate {

    //aspect ile loglama

    /**
     * Upper bounded için örnek
     *
     * @param list Number olan Integer, Float, Double vs. gibi değerleri içeren liste
     * @return ortalama
     */
    public double calculateAverage(List<? extends Number> list) {
        if (list == null || list.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum / list.size();
    }
}
