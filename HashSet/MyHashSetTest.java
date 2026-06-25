package HashSet;

public class MyHashSetTest {
    
    public static void main(String[] args){
        MyHashSet<String> set = new MyHashSet<>();

        set.add("Kim");
        set.add("Seo");
        set.add("Jung");

        System.out.println(set);

        System.out.println("중복 확인 : " + set.add("Kim"));

        set.remove("Jung");

        System.out.println("잘못된 데이터 삭제 : " + set.remove("Dong"));

        //resize 테스트
        for(int i=0; i<16; i++){
            set.add("element" + i);
        }

        System.out.println(set.size());
        System.out.println(set.isEmpty());
        System.out.println(set);
    }
}
