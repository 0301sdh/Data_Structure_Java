package HashSet;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample2 {

    public static void main(String[] args){
        int[] numbers = {1,2,3,2,4,1,5};

        Set<Integer> set = new HashSet<>();

        for(int number : numbers){
            set.add(number);
        }

        System.out.println(set);
    }
    
}
