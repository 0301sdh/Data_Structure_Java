package Queue;

import java.util.Queue;
import java.util.LinkedList;
public class QueueExample2 {

    public static void main(String[] args){

        Queue<Integer> numbers = new LinkedList<> ();

        int sum = 0;

        for(int i = 1; i<=5; i++){
            numbers.offer(i);
        }

        while(!numbers.isEmpty()){
            sum += numbers.poll();
        }

        System.out.println("합계 : " + sum);
    }
    
}
