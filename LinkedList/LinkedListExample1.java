package LinkedList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample1{
    public static void main(String[] args){
        List<String> names = new LinkedList<> ();
        
        names.add("Kim");
        names.add("Dong");
        names.add("Park");
        System.out.println(names);
        System.out.println(names.get(1)); 
        // 배열처럼 index 1에 바로 가는게 아니라 0 -> 1 순차적으로 탐색을 해서 시간복잡도가 O(n)이다

        names.remove("Park");
        names.addFirst("Yoon");
        System.out.println(names);
        names.addLast("Jung");
        System.out.println(names);
    }
}