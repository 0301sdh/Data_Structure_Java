package LinkedList;

public class MyDlLinkedListTest {
    public static void main(String[] args) throws Exception{

        MyDoubleLinkedList<String> list = new MyDoubleLinkedList<>();

        // add
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println(list);

        // insert
        list.insert(0, "Avocado");
        list.insert(2, "Blueberry");
        list.insert(list.size(), "Durian");
        System.out.println(list);

        // get
        System.out.println("get(0): " + list.get(0));
        System.out.println("get(5): " + list.get(5));

        // contains
        System.out.println("contains(Banana): " + list.contains("Banana"));
        System.out.println("contains(Mango): " + list.contains("Mango"));

        // remove
        list.remove(0);
        list.remove(list.size() - 1);
        list.remove(1);
        System.out.println(list);

        // size, isEmpty
        System.out.println("size: " + list.size());
        System.out.println("isEmpty: " + list.isEmpty());

        // 역방향 출력
        System.out.println("reverse: " + list.toReverseString());
    }

}
