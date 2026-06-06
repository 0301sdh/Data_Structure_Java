package HashMap;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample2 {

    public static void main(String[] args){

        Map<String, Integer> scores = new HashMap<>();

        scores.put("Kim", 90);
        scores.put("Lee", 80);
        scores.put("Park", 95);
        scores.put("Choi", 70);

        System.out.println("전체 학생과 점수");
        System.out.println(scores);

        int sum = 0;
        for(int score : scores.values()){
            sum += score;
        }
        double average = (double)sum/ scores.size();
        System.out.println("평균 : " + average);

        String TopStudent = "";
        int MaxScore = 0;

        for(String name : scores.keySet()){
            if(scores.get(name) > MaxScore){
                MaxScore = scores.get(name);
                TopStudent = name;
            }
        }

        System.out.println("반 1등 : " + TopStudent +"  "+ MaxScore);
        
    }
    
}
