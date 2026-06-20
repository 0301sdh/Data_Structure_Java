package HashMap;

public class MyHashMapTest {

    public static void main(String[] args){
        
        MyHashMap<String, Integer> map = new MyHashMap<>();
        // put 테스트
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry",3);
        System.out.println(map);

        // get 테스트
        System.out.println(map.get("apple"));
        System.out.println(map.get("grape"));

        // put 중복 키 -> 값 갱신
        map.put("apple",100);
        System.out.println(map.get("apple"));

        // containsKey 테스트
        System.out.println(map.containsKey("banana"));
        System.out.println(map.containsKey("grape"));

        // remove 테스트
        System.out.println(map.remove("banana"));
        System.out.println(map);

        // isEmpty 테스트
        System.out.println(map.isEmpty());

        // resize 테스트
        for(int i=0; i<20; i++){
            map.put("key" + i, i);
        }
        System.out.println(map.size());
        System.out.println(map);

    }

}
