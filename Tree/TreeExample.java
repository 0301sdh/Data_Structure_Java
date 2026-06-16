package Tree;

import java.util.TreeMap;
import java.util.TreeSet;

public class TreeExample {
    
    public static void main(String[] args){
        TreeSet<Integer> ts = new TreeSet<>();

        //데이터 추가
        ts.add(50);
        ts.add(10);
        ts.add(30);
        ts.add(70);
        ts.add(10);

        System.out.println(ts);
        ts.remove(30);
        System.out.println(ts);


        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("Apple", 1000);
        tm.put("Banana", 500);
        tm.put("Cherry", 2000);
        tm.put("Apple", 4000);
        tm.remove("Banana");

        System.out.println(tm);
    }
}
