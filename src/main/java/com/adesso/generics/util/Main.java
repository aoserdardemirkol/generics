package com.adesso.generics.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

interface Vehicle {
    void run();
}

class Bus implements Vehicle {
    public String stop;

    @Override
    public void run() {

    }

    private void doSomethingAboutBus() {
        System.out.println("Bus is running");
    }

}

class Car implements Vehicle {
    private String make;

    @Override
    public void run() {

    }

    public void doSomethingAboutCar() {
        System.out.println("Car is running");
    }
}

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Vehicle v1 = new Bus();
        Vehicle v2 = new Car();

        System.out.println(v1.getClass());
        System.out.println(v2.getClass());

        Class<?> c1 = v1.getClass();
        Method busMethod = c1.getDeclaredMethod("doSomethingAboutBus");
        busMethod.setAccessible(true);
        busMethod.invoke(v1);

        Field field1 = c1.getDeclaredField("stop");
        field1.setAccessible(true);
        System.out.println(field1.getName());

        Class<?> c2 = v2.getClass();
        Method carMethod = c2.getMethod("doSomethingAboutCar");
        carMethod.invoke(v2);

        Field field2 = c2.getDeclaredField("make");
        field2.setAccessible(true);
        System.out.println(field2.getName());
    }
}


