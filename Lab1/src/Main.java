package com.gmail.kpi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        //TASK 1
        //Создание объектов классов
        Matrix matrix = new Matrix();
        SLAR slar = new SLAR();

        Class clss1 = matrix.getClass();
        Class clss2 = slar.getClass();

        //TASK 3
        //Список интерфейсов реализованных классом Matrix
        Class[] interfaces = clss1.getInterfaces();
        System.out.println("Interfaces:");
        for (Class intrfc : interfaces)
            System.out.println("\t" + intrfc);

        Class[] interfaces2 = clss2.getInterfaces();
        System.out.println("Interfaces:");
        for (Class intrfc : interfaces2)
            System.out.println("\t" + intrfc);

        //TASK 3
        //Список полей класса Matrix
        Field[] fields = clss1.getDeclaredFields();
        System.out.println("Fields:\n");
        for (Field field : fields )
            System.out.println("\t" + field);

        Field[] fields2 = clss2.getDeclaredFields();
        System.out.println("Fields:\n");
        for (Field field : fields2 )
            System.out.println("\t" + field);

        //TASK 2
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

        //TASK 4
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
