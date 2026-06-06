package HashMap;

import java.util.HashMap;
import java.util.Map;
public class HashMapExample1 {
    public static void main(String[] args){

        Map<String,Integer> scores = new HashMap<> ();

        scores.put("Kim", 80);
        scores.put("Seo", 100);
        scores.put("Park", 90);

        System.out.println(scores);

        System.out.println("학생의 수 : " + scores.size());

        System.out.println("Seo의 점수 : " + scores.get("Seo"));

    }
}
