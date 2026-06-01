package ArrayList;
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample1{
    public static void main(String[] args){
        List<String> names = new ArrayList<> ();

        names.add("kim");
        names.add("seo");
        names.add("Park");

        System.out.println(names);

        System.out.println(names.get(0));
        System.out.println(names.size());
        System.out.println(names.contains("seo"));
        System.out.println(names.contains("dong"));
        names.remove("seo");
        names.remove(1);
        System.out.println(names);
    }
}