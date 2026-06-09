package LinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) throws Exception{
        MyLinkedList<Integer> list = new MyLinkedList<>();

        // add 테스트
          list.add(10);
          list.add(20);
          list.add(30);
          System.out.println(list);  // [10-->20-->30]

          // get 테스트
          System.out.println(list.get(1));  // 20

          // insert 테스트
          list.insert(0, 5);     // 맨 앞 삽입
          list.insert(2, 15);    // 중간 삽입
          System.out.println(list);  // [5-->10-->15-->20-->30]

          // remove 테스트
          list.remove(0);        // 맨 앞 삭제
          list.remove(3);        // 마지막 삭제
          System.out.println(list);  // [10-->15-->20]

          // contains 테스트
          System.out.println(list.contains(15));  // true
          System.out.println(list.contains(99));  // false

          // size, isEmpty 테스트
          System.out.println(list.size());     // 3
          System.out.println(list.isEmpty());  // false
        
    }
}
