package Queue;

public class MyQueueTest {
    public static void main(String[] args) throws Exception{
        MyQueue q = new MyQueue();

        // enqueue 테스트
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        System.out.println("enqueue 후: " + q);  // [10-->20-->30-->40-->50]

        // dequeue 테스트
        System.out.println("dequeue: " + q.dequeue());  // 10
        System.out.println("dequeue: " + q.dequeue());  // 20
        System.out.println("dequeue 후: " + q);  // [30-->40-->50]

        // peek 테스트
        System.out.println("peek: " + q.peek());  // 30

        // size, isEmpty 테스트
        System.out.println("size: " + q.size());  // 3
        System.out.println("isEmpty: " + q.isEmpty());  // false

        // contains 테스트
        System.out.println("contains 30: " + q.contains(30));  // true
        System.out.println("contains 10: " + q.contains(10));  // false

        // doubling 확인 (초기 size=1이므로 여러번 doubling 발생)
        q.enqueue(60);
        q.enqueue(70);
        q.enqueue(80);
        System.out.println("추가 enqueue 후: " + q);  // [30-->40-->50-->60-->70-->80]
        System.out.println("size: " + q.size());  // 6
    }
}
