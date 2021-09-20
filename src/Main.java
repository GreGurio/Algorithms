package com.gmail.kpi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        //Создание объектов классов
        Matrix matrix = new Matrix();
        SLAR slar = new SLAR();

        Class clss1 = matrix.getClass();

        //Список интерфейсов реализованных классом Matrix
        Class[] interfaces = clss1.getInterfaces();
        System.out.println("Interfaces:");
        for (Class intrfc : interfaces)
            System.out.println("\t" + intrfc);

        //Список полей класса Matrix
        Field[] fields = clss1.getDeclaredFields();
        System.out.println("Fields:\n");
        for (Field field : fields )
            System.out.println("\t" + field);

        //invoke
        Method[] methods = clss1.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CustomAnnotation.class)) {
                try {
                    System.out.println(method.invoke(matrix));
                } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException exception) {
                    exception.printStackTrace();
                }
            }
        }

        //Прокси
        MatrixInterface matrix1 = (MatrixInterface) Proxy.newProxyInstance(matrix.getClass().getClassLoader(), matrix.getClass().getInterfaces(), new MyProxy(matrix));
        try {
            matrix1.setIdentifier(10);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        matrix.toString();
    }
}
