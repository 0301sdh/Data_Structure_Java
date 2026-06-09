package LinkedList;

class Node<T>{
    T data;
    Node<T> next;

    Node(T data){
        this.data = data;
        this.next = null;
    }
}

class MyLinkedList<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    MyLinkedList(){
        head = null; // 가장 첫 번째 노드를 가리킴
        tail = null; // 가장 마지막 노드를 가리킴
        size = 0;
    }

    // 맨 뒤에 데이터 추가
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size ++;
    }
    
    // 인덱스로 값 조회
    public T get(int index) throws Exception{
        if(index >= size){ // 가지고 있는 데이터보다 큰 경우
            throw new Exception("ArrayIndexOutOfBound");
        }
        else if(index < 0){
            throw new Exception("NegativeValue");
        }
        Node<T> currentNode = head;
        for(int i=0; i<index; i++){
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    // 값이 포함돼어 있는지 검색
    public boolean contains(T data){
        Node<T> currentNode = head;
        for(int i = 0; i < size; i++){
            if(currentNode.data.equals(data)){
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }
    
    // 데이터 삽입
    public void insert(int index, T data) throws Exception{
        if(index > size){
            throw new Exception("ArrayIndexOutOfBound");
        }
        else if(index < 0){
            throw new Exception("NegativeValue");
        }
        Node<T> newNode = new Node<>(data);
        Node<T> currentNode = head;
        if(index == 0){ // 맨 앞에 데이터 추가
            newNode.next = head;
            head = newNode;
            if(tail == null){ // 데이터 삽입 전 list가 비었더라면 tail을 새로 지정해줘야한다
                tail = newNode;
            }
        }
        else if(index<size){ // 중간에 데이터 추가
            for(int i = 0; i< index - 1; i++){
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
        else if(index == size){// 맨 뒤에 데이터 추가
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    //데이터 제거
    public void remove(int index) throws Exception{
        if(index > size-1){
            throw new Exception("ArrayIndexOutOfBound");
        }
        else if(index < 0){
            throw new Exception("NegativeValue");
        }

        Node<T> currentNode = head;
        if(index == 0){ // 맨 앞 데이터 삭제
            head = head.next;
            if(head == null){ // 데이터 삭제로 list가 비었을때
                tail = null;
            }
        }
        else if(index<size-1){ // 중간에 데이터 삭제
            for(int i=0; i<index-1; i++){
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
        }
        else if(index == size -1){ // 마지막 데이터 삭제
            for(int i = 0; i<index-1; i++){
                currentNode = currentNode.next;
            }
            tail = currentNode;
            tail.next = null;
        }
        size--;

    }

    // List 크기 측정
    public int size(){
        return size;
    }

    // List가 비어있는지 확인
    public boolean isEmpty(){
        return size == 0;
    }

     //데이터 출력
     @Override
     public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node<T> currentNode = head;
        for(int i = 0 ; i<size; i++){
            sb.append(currentNode.data);
            if(i< size -1){
                sb.append("-->");
            }
            currentNode = currentNode.next;
        }
        sb.append("]");
        return sb.toString();
     }

}