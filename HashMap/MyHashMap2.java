package HashMap;

public class MyHashMap2<K,V>{

    //Open Addressing메서는 연결 리스트가 필요없다
    // 각 슬롯에 key, value를 직접 저장한다
    private K[] keys;
    private V[] values;
    private boolean[] deleted; // 삭제된 슬롯 표시

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.5f;

    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap2(){
        this.capacity = DEFAULT_CAPACITY;
        this.keys = (K[]) new Object[capacity]; //자바에서 제네릭을 배열로 설정못해서 캐스팅 해야함
        this.values = (V[]) new Object[capacity];
        this.deleted = new boolean[capacity];
        this.size = 0;
    }

    //해시 함수 chaining 방식과 동일
    private int hash(K key){
        if(key == null)
            return 0;
        int h = key.hashCode();
        h ^= (h>>>16);
        return h&(capacity-1);
    }

    //put : 키- 값 저장(Linear Probing)
    // 충돌 시 다음 슬롯으로 이동하며 빈 자리를 찾기
    public void put(K key, V value){
        if(size >= capacity*LOAD_FACTOR){
            resize();
        }
        int index = hash(key);
        while (keys[index] != null) {
            if(!deleted[index] && keys[index].equals(key)){
                values[index] = value;
                return;
            }
            if(deleted[index]){
                break;
            }
            index = (index + 1) % capacity; // 다음 슬롯으로 이동(원형 순환)
        }
        keys[index] = key;
        values[index] = value;
        size++;
        deleted[index] = false;
    }

    public void remove(K key){
        int index = hash(key);
        if(!deleted[index] || (keys[index] == null))
            System.out.println("empty");
    }
}