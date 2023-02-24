package reflection_hw_three;

import javax.imageio.IIOException;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Boolean.*;

public class Main {
    public static void main(String[] args) {
        SerializationExample se1 = new SerializationExample(111, "toSerialize", false);
        SerializationExample se2 = new SerializationExample(0, "test", true);

        File file = new File("for_serialization.txt");

        try {
            serialize(file, se1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("Test object before: ");
        System.out.println(se2);

        deSerialize(se2, file);

        System.out.println("Test object after: ");
        System.out.println(se2);

    }

    public static void serialize(File file, SerializationExample se) throws IllegalAccessException {

        Class<?> classs = se.getClass();
        Field[] fields = classs.getDeclaredFields();

        try (FileWriter fw = new FileWriter(file)) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    fw.append(field.getName() + " ");

                    if (field.getType().equals(int.class)) {
                        fw.append("" + field.getInt(se) + System.lineSeparator());
                    }
                    if (field.getType().equals(String.class)) {
                        fw.append("" + (String) field.get(se) + System.lineSeparator());
                    }
                    if (field.getType().equals(boolean.class)) {
                        fw.append("" + field.getBoolean(se) + System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SerializationExample deSerialize(SerializationExample se, File file) {
        Class<?> classs = se.getClass();
        Field[] fields = classs.getDeclaredFields();

        try (Scanner scanner = new Scanner(file)) {
            for (;scanner.hasNextLine();) {
                String temp = scanner.nextLine();

                String fieldName = temp.substring(0, temp.indexOf(" "));

                for(Field field: fields) {
                    if(field.getName().equals(fieldName)) {
                        if (Modifier.isPrivate(field.getModifiers())) {
                            field.setAccessible(true);
                        }

                        String value = temp.substring(temp.lastIndexOf(" ") + 1);

                        try {
                            if (field.getType().equals(int.class)) {
                                field.setInt(se, Integer.valueOf(value));
                            }
                            if (field.getType().equals(String.class)) {
                                field.set(se, value);
                            }
                            if (field.getType().equals(boolean.class)) {
                                field.setBoolean(se, Boolean.valueOf(value));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return se;
    }
}
