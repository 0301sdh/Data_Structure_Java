package Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample2 {
    public static void main(String[] args){

        // Deque를 Stack으로 활용
        Deque<Integer> stack = new ArrayDeque<> ();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.peek());

        // Deque를 Queue로 활용
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.peek());
    }
}
