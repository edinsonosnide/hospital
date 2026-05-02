package com.solvd.hospital.model;

import java.lang.reflect.Method;

import static com.solvd.hospital.Main.LOGGER;

public class ReflectionUtility {

    public static void process(Object obj) throws Exception {
        Class<?> myClass = obj.getClass();
        for (Method method : myClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                LOGGER.info("Method´s annotation value: {}",method.getAnnotation(MyAnnotation.class).value());
                LOGGER.info("Invoking the method: {}",method.invoke(obj));
            }
        }
    }
}
