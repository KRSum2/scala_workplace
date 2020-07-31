package test.Java与Scala集合互操作;

import java.util.ArrayList;
import java.util.List;

public class JavaWildcardGeneric {
    public static List<?> getList(){
        List<String> listStr = new ArrayList<>();
        listStr.add("Hadoop");
        listStr.add("Hive");
        return listStr;
    }
}
