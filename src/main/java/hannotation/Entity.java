package hannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // thoi diem su dung
@Target(ElementType.TYPE)//pham vi su dung
public @interface Entity {
    String tableName();

}
