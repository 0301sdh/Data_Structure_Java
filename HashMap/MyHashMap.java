package HashMap;

public class MyHashMap<K,V>{
    
    // 각 버킷에 저장되는 노드(연결 리스트)
    public static class Node<K,V>{ // K,V 선언 오류 -> static
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
        return h & (table.length -1 ); //index 가 0~15 니까 // 나머지연산이랑 and 연산 비교해보기
    }

    // put : 키 - 값 저장
    public void put(K key, V value){
        int index = hash(key);
        Node<K,V> current = table[index];
        while(current != null){
            if(key.equals(current.key)){
                current.value = value;
                return;
            }
            current = current.next;
        }
        // 새 노드를 버킷의 앞에 삽입
        table[index] = new Node<>(index, key, value, table[index]);
        this.size++;

        // 적재율 초과 시 resize resize 를 0.75로 하는이유
        if(this.size> table.length * LOAD_FACTOR)
            resize();
    }

    // get : 키로 값 조회
    public V get(K key){
        int index = hash(key);
        Node<K,V> current = table[index];
        while(current != null){
            if(current.key.equals(key))
                return current.value;
            current = current.next;
        }
        return null; // 키가 없으면 null 반환
    }

    // remove : 키로 삭제, 삭제된 값 반환
    public V remove(K key){
        int index = hash(key);
        Node<K,V> current = table[index];
        Node<K,V> prev = null;
        while(current != null){
            if(current.key.equals(key)){
                if(prev == null){//첫번째 노드 삭제할때
                    table[index] = current.next;
                }
                else{
                    prev.next = current.next;
                }
                this.size --;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    //containsKey : 키 존재 여부 확인
    public boolean containsKey(K key){
        return get(key) != null;
    }

    // size : 저장된 노드의 개수
    public int size(){
        return size;
    }

    // isEmpty : 비어있는지 확인
    public boolean isEmpty(){
        return size == 0;
    }

    //resize : 버킷 배열을 2배로 확장하고 모든 노드를 재배치
    @SuppressWarnings("unchecked")
    private void resize(){
        Node<K,V> oldTable[] = this.table;
        table = new Node[oldTable.length*2];
        size = 0;

        // 기존 테이블의 모든 노드를 새 테이블에 put
        for(int i = 0; i< oldTable.length; i++){
            Node<K,V> current = oldTable[i];
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    // toString : 내용 출력
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        int check=0;
        for(int i = 0; i<table.length; i++){
            Node<K,V> current = table[i];
            while(current != null){
                sb.append(current.key).append("=").append(current.value);
                if(check<this.size-1){
                    sb.append(" , ");
                }
                check++;
                current = current.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

}