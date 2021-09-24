
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {
    private final MatrixInterface matrix; //Объект типа MatrixInterface

    //Конструктор
    public MyProxy(MatrixInterface matrix) {
        this.matrix = matrix;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Обработка вызова геттеров и сеттеров
        if(method.isAnnotationPresent(Getter.class) )
            return method.invoke(matrix, args);
        else if (method.isAnnotationPresent(Setter.class) || method.getName().startsWith("set"))
            throw new IllegalAccessException("Сеттеры использовать не разрешено.");
        else
            return null;
    }
}
