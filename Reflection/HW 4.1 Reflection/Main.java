package reflection_hw_one;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Class<?> classs = Main.class;

        Method[] methods = classs.getDeclaredMethods();

        System.out.println("Methods from Main:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                System.out.print("Annotated method is: " + method.getName() + System.lineSeparator());

                MyMethodAnnotation annotation = method.getAnnotation(MyMethodAnnotation.class);
                try {
                    boolean b = (boolean) method.invoke(null, annotation.paramInt(), annotation.paramString());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @MyMethodAnnotation(paramInt = 111, paramString = "some text")
    public static boolean printAnnotaionParameters(int paramInt, String paramString) {
        System.out.println("int parameter form annotation is: " + paramInt);
        System.out.println("String parameter from annotation is: " + paramString);
        return true;
    }
}
