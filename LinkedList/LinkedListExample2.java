package LinkedList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample2 {
    public static void main(String[] args){
        List<Integer> numbers = new LinkedList<>();
        numbers.add(101);
        numbers.add(103);
        numbers.add(1,102); 
        numbers.add(104);
        System.out.println(numbers);
    }
    
}
