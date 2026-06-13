package Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample1 {
    public static void main(String[] args){

        Deque<Integer> deque = new ArrayDeque<>();

        //뒤에 추가
        deque.addLast(10);
        deque.addLast(20);

        //앞에 추가
        deque.addFirst(5);

        System.out.println(deque);

        // 앞 요소 조회
        System.out.println("첫 번째: " + deque.peekFirst());

        // 마지막 요소 조회
        System.out.println("마지막 : " + deque.peekLast());

        // 앞에서 제거
        System.out.println("삭제된 앞 요소: " + deque.removeFirst());

        System.out.println(deque);

        //뒤에서 제거
        System.out.println("삭제된 뒤 요소 : " + deque.removeLast());

        System.out.println(deque);
    }
}
