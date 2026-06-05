package Queue;

import java.util.Queue;
import java.util.LinkedList;


public class QueueExample1{

    public static void main(String[] args){
        Queue<String> queue = new LinkedList<>();
        queue.offer("Kim");
        queue.offer("Lee");
        queue.offer("Park");

        System.out.println("현재 대기열");
        System.out.println(queue);

        System.out.println("상담 고객 : " + queue.poll());

        System.out.println("다음 순서 고객 : " + queue.peek());
        System.out.println("남은 고객 수 : " + queue.size());



    }
}