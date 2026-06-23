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
        return h & (capacity-1);
    }

    //put : 키- 값 저장(Linear Probing)
    // 충돌 시 다음 슬롯으로 이동하며 빈 자리를 찾기(원형)
    public void put(K key, V value){
        if(size >= capacity*LOAD_FACTOR){
            resize();
        }
        int index = hash(key);
        int firstDeleted = -1; // 탐색중 만난 첫번째 deleted: true 위치
        while(keys[index] != null || deleted[index]){
            if(deleted[index]){
                if(firstDeleted == -1){
                    firstDeleted = index;
                }
            }
            else if(keys[index].equals(key)){
                values[index] = value;
                return;
            }
            index = (index + 1) % capacity;
        }
        if(firstDeleted != -1){
            index = firstDeleted;
        }
        keys[index] = key;
        values[index] = value;
        deleted[index] = false;
        size++; 
    }

    // get : 키로 값 조회
    public V get(K key){
        int index = findIndex(key);
        if(index == -1)
            return null;
        return values[index];
    }

    // remove : 키로 삭제, 삭제된 값 반환
    // 그냥 삭제하면 같은 해시값을 가진 뒤쪽 데이터를 찾을 수 없음
    // 그래서 deleted를 사용함
    public V remove(K key){
        int index = findIndex(key);
        if(index == -1)
            return null;
        V oldValue = values[index];
        deleted[index] = true;
        keys[index] = null;
        values[index] = null;
        size --;
        return oldValue;
    }

    // findIndex: 키가 저장된 슬롯의 인덱스를 찾는다
    private int findIndex(K key){
        int index = hash(key);
        while(keys[index] != null || deleted[index]){
            if(!deleted[index] && keys[index].equals(key))// deleted[index] 로 불필요한 equals 호출 감소 null.equals -> Null Pointer Exception
                return index;
            index = (index+1)%capacity;
        }
        return -1;
    }

    // containsKey : 키 존재 여부
    public boolean containsKey(K key){
        return findIndex(key) != -1;
    }

    // size: 저장된 데이터 개수
    public int size(){
        return size;
    }

    //isEmpty : 비어있는지 확인
    public boolean isEmpty(){
        return size == 0;
    }

    // resize : 배열을 2배로 확장하고 모든 데이터를 재배치
    @SuppressWarnings("unchecked")
    private void resize(){
        K[] oldKeys = keys;
        V[] oldValues = values;
        boolean[] oldDeleted = deleted;
        int oldCapacity = capacity;

        capacity = oldCapacity*2;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
        deleted = new boolean[capacity];
        size = 0;

        for(int i = 0 ; i< oldCapacity; i++){
            if(!oldDeleted[i] && oldKeys[i] != null){
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    // toString: 내용 출력
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        int count = 0;
        for(int i = 0; i<capacity; i++){
            if(!deleted[i] && keys[i] != null){
                sb.append(keys[i]).append("=").append(values[i]);
                if(count < size - 1){
                    sb.append(" , ");
                }
                count++;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}