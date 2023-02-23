package reflection_hw_two;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        TextContainer tc = new TextContainer("new text from saver");

        Class<?> classs = TextContainer.class;
        Method[] methods = classs.getMethods();

        for (Method method: methods) {
            if (method.isAnnotationPresent(Saver.class)) {
                SaveTo savePath = TextContainer.class.getAnnotation(SaveTo.class);

                try {
                    boolean b = (Boolean) method.invoke(tc, savePath.path());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
