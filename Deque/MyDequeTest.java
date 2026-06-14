package Deque;

public class MyDequeTest {
    public static void main(String[] args) throws Exception {
    MyDeque<Integer> deque = new MyDeque<>();

    // addLast 테스트
    deque.addLast(1);
    deque.addLast(2);
    deque.addLast(3);
    System.out.println("addLast 1,2,3: " + deque); // [1-->2-->3]

    // addFirst 테스트
    deque.addFirst(0);
    deque.addFirst(-1);
    System.out.println("addFirst 0,-1: " + deque); // [-1-->0-->1-->2-->3]

    // removeFirst 테스트
    System.out.println("removeFirst: " + deque.removeFirst()); // -1
    System.out.println("결과: " + deque); // [0-->1-->2-->3]

    // removeLast 테스트
    System.out.println("removeLast: " + deque.removeLast()); // 3
    System.out.println("결과: " + deque); // [0-->1-->2]

    // peek 테스트
    System.out.println("peekFirst: " + deque.peekFirst()); // 0
    System.out.println("peekLast: " + deque.peekLast());   // 2

    // size, isEmpty 테스트
    System.out.println("size: " + deque.size());       // 3
    System.out.println("isEmpty: " + deque.isEmpty());  // false

    // contains 테스트
    System.out.println("contains(1): " + deque.contains(1)); // true
    System.out.println("contains(5): " + deque.contains(5)); // false
  }
    
}
