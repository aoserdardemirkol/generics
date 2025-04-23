package com.adesso.generics.util;

import java.util.List;

public class Logger {

    public void logList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj.toString());
        }
    }
}