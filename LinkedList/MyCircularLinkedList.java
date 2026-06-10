package LinkedList;

class CNode<T>{
    T data;
    CNode<T> next;

    CNode(T data){
        this.data = data;
        this.next = null;
    }
}

class MyCircularLinkedList<T> {
    CNode<T> head;
    CNode<T> tail;
    int size;

    MyCircularLinkedList(){
        head = null; // 가장 첫 번째 노드를 가리킴
        tail = null; // 가장 마지막 노드를 가리킴
        size = 0;
    }

    // 맨 뒤에 데이터 추가
    public void add(T data){
        CNode<T> newCNode = new CNode<>(data);
        if(head == null){
            head = newCNode;
            tail = newCNode;
            tail.next = head;
        }
        else{
            tail.next = newCNode;
            tail = newCNode;
            tail.next = head;
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
        CNode<T> currentCNode = head;
        for(int i=0; i<index; i++){
            currentCNode = currentCNode.next;
        }
        return currentCNode.data;
    }

    // 값이 포함돼어 있는지 검색
    public boolean contains(T data){
        CNode<T> currentCNode = head;
        for(int i = 0; i < size; i++){
            if(currentCNode.data.equals(data)){
                return true;
            }
            currentCNode = currentCNode.next;
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
        CNode<T> newCNode = new CNode<>(data);
        CNode<T> currentCNode = head;
        if(index == 0){ // 맨 앞에 데이터 추가
            if(head == null){
                head = newCNode;
                tail = newCNode;
                tail.next = head;
            }
            else{
                newCNode.next = head;
                head = newCNode;
                tail.next = head;
            }
        }
        else if(index<size){ // 중간에 데이터 추가
            for(int i = 0; i< index - 1; i++){
                currentCNode = currentCNode.next;
            }
            newCNode.next = currentCNode.next;
            currentCNode.next = newCNode;
        }
        else if(index == size){// 맨 뒤에 데이터 추가
            tail.next = newCNode;
            tail = newCNode;
            tail.next = head;
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

        CNode<T> currentCNode = head;
        if(index == 0){ // 맨 앞 데이터 삭제
            if(size == 1){
                head = null;
                tail = null;
            }
            else{
                head = head.next;
                tail.next = head;
            }
        }
        else if(index<size-1){ // 중간에 데이터 삭제
            for(int i=0; i<index-1; i++){
                currentCNode = currentCNode.next;
            }
            currentCNode.next = currentCNode.next.next;
        }
        else if(index == size -1){ // 마지막 데이터 삭제
            for(int i = 0; i<index-1; i++){
                currentCNode = currentCNode.next;
            }
            tail = currentCNode;
            tail.next = head;
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
        CNode<T> currentCNode = head;
        for(int i = 0 ; i<size; i++){
            sb.append(currentCNode.data);
            if(i< size -1){
                sb.append("-->");
            }
            currentCNode = currentCNode.next;
        }
        sb.append("]");
        return sb.toString();
     }

     //데이터 순환 확인(n번 순환)
     public String toCircularString(int n){
        StringBuilder sb = new StringBuilder("[");
        CNode<T> currentCNode = head;
        for(int i = 0 ; i<size*n; i++){
            sb.append(currentCNode.data);
            if(i< size*n -1){
                sb.append("-->");
            }
            currentCNode = currentCNode.next;
        }
        sb.append("]");
        return sb.toString();
     }

}