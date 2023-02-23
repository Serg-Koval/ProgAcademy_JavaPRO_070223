package reflection_hw_two;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface SaveTo {
    String path() default "C:\\Users\\AsusWallet\\Desktop\\Prog Academy\\Java PRO EE_2023 02\\Vsevolod\\Reflection1\\Reflection1\\src\\reflection_hw_two\\hw2_path.txt";
}
