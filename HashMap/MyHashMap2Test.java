package HashMap;

public class MyHashMap2Test {
    public static void main(String[] args){

        MyHashMap2<String, Integer> map = new MyHashMap2<>();

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

        // remove 후 다시 같은 키 put
        map.put("banana", 99);
        System.out.println(map.get("banana"));
        
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
