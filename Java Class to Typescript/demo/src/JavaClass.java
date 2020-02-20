import java.util.List;
import java.util.Map;
/**
 * classDoc
 */
public class JavaClass {
    private String demo;
    private int number1;
    private long number2;
    private final Boolean aBoolean = true;
    private List<String> list;
    private String[] array;
    private CustomClass customClass;
    private Map<String,CustomClass> map;
    private C c;
    /**
     * fieldDoc
     * fieldDoc
     */
    private float f;
}

enum C{
    /**
     * enumDoc
     * enumDoc
     */
    A,
    B
}

class CustomClass{
    private String a;

}