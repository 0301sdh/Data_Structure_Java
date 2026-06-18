package HashMap;

public class MyHashMap<K,V>{
    
    // 각 버킷에 저장되는 노드(연결 리스트)
    public class Node<K,V>{
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<K,V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap(){
        this.table = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    //해시 함수: key의 hashCode를 버킷 인덱스로 변환
    private int hash(K key){
        if(key == null)
            return 0;
        int h = key.hashCode();
        // hascode 가 겹치면 해싱 충돌이 일어난다. 현재 배열의 크기가 16
        // hashCode의 하위 비트만 영향을 주기 때문에 내부적으로 해싱을 더해준다
        h ^= (h>>>16);
        return h = (table.length -1 ); //index 가 0~15 니까 
    }

    // put : 키 - 값 저장
    public void put(K key, V value){
        int index = hash(key);
        Node<K,V> current = table[index];
        while(current != null){
            if(key.equals(current.key)){
                current.value = value;
            }
            current = current.next;
        }

        // 새 노드를 버킷의 앞에 삽입
        table[index] = new Node<>(index, key, value, table[index]);
    }

}