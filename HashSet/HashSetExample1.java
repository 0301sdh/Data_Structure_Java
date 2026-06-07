package HashSet;

import java.util.Set;
import java.util.HashSet;

public class HashSetExample1{
    
    public static void main(String[] args){
        Set<String> names = new HashSet<>();
        names.add("Kim");
        names.add("Lee");
        names.add("Park");
        names.add("Kim");
        System.out.println(names);

        System.out.println(names.contains("Lee"));
        System.out.println(names.size());
    }
}