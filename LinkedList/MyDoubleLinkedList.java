package LinkedList;

class DNode<T>{
    T data;
    DNode<T> next; //다음 노드객체를 가리키는 래퍼런스 변수
    DNode<T> prev; //이전 노드객체를 가리키는 래퍼런스 변수

    DNode(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class MyDoubleLinkedList<T>{
    DNode<T> head;
    DNode<T> tail;
    int size;
    
    MyDoubleLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    //맨 뒤에 데이터 추가
    public void add(T data){
        DNode<T> newDNode = new DNode<>(data);
        if(head == null){
            head = newDNode;
            tail = newDNode;
        }
        else{
            tail.next = newDNode;
            newDNode.prev = tail;
            tail = newDNode;
        }
        size++;
    }

    // 인덱스로 값 조회
    public T get(int index) throws Exception{
        if(index >=size){ // 가지고 있는 데이터보다 큰 경
            throw new Exception("ArrayIndexOutOfBound");
        }
        else if(index < 0){
            throw new Exception("NegativeValue");
        }
        DNode<T> currentDNode = head;
        DNode<T> currentDNodeT = tail;
        if(index>size/2){ // index 가 size의 반보다 크다면 tail에서 prev을 사용하여 조회
            for(int i = 0; i< size- index -1; i++){
                currentDNodeT = currentDNodeT.prev;
            }
            return currentDNodeT.data;
        }
        else{
            for(int i=0; i<index; i++){
                currentDNode = currentDNode.next;
            }
            return currentDNode.data;
        }
        
    }

    // 값이 포함돼어 있는지 검색
    public boolean contains(T data){
        DNode<T> currentDNode = head;
        for(int i = 0; i < size; i++){
            if(currentDNode.data.equals(data)){
                return true;
            }
            currentDNode = currentDNode.next;
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
        DNode<T> newDNode = new DNode<>(data);
        DNode<T> currentDNode = head;
        if(index == 0){ // 맨 앞에 데이터 추가
            if(head == null){ // List가 비어있다면
                head = newDNode;
                tail = newDNode;
            }
            else{
                newDNode.next = head;
                head.prev = newDNode;
                head = newDNode;
            }
        }
        else if(index<size){ // 중간에 데이터 추가
            for(int i = 0; i< index - 1; i++){
                currentDNode = currentDNode.next;
            }
            newDNode.next = currentDNode.next;
            currentDNode.next.prev = newDNode;
            currentDNode.next = newDNode;
            newDNode.prev = currentDNode;
        }
        else if(index == size){// 맨 뒤에 데이터 추가
            tail.next = newDNode;
            newDNode.prev = tail;
            tail = newDNode;
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

        DNode<T> currentDNode = head;
        if(index == 0){ // 맨 앞 데이터 삭제
            head = head.next;
            if(head == null){ // 데이터 삭제로 list가 비었을때
                tail = null;
            }
            else{
                head.prev = null;
            }
        }
        else if(index<size-1){ // 중간에 데이터 삭제
            for(int i=0; i<index-1; i++){
                currentDNode = currentDNode.next;
            }
            currentDNode.next = currentDNode.next.next;
            currentDNode.next.prev = currentDNode;
        }
        else if(index == size -1){ // 마지막 데이터 삭제
            for(int i = 0; i<index-1; i++){
                currentDNode = currentDNode.next;
            }
            tail = currentDNode;
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
        DNode<T> currentDNode = head;
        for(int i = 0 ; i<size; i++){
            sb.append(currentDNode.data);
            if(i< size -1){
                sb.append("-->");
            }
            currentDNode = currentDNode.next;
        }
        sb.append("]");
        return sb.toString();
     }

     //데이터 역방향 출력(prev 검사)
     public String toReverseString(){
        StringBuilder sb = new StringBuilder("[");
        DNode<T> currentDNodeT = tail;
        for(int i = 0; i < size; i++){
            sb.append(currentDNodeT.data);
            if(i<size-1){
                sb.append("-->");
            }
            currentDNodeT = currentDNodeT.prev;
        }
        sb.append("]");
        return sb.toString();
     }
} 