package Deque;

public class MyDeque<E> {

    private Object[] data;
    private int size;   // 배열의 최대 크기
    private int front;
    private int rear;
    private int count; // 저장된 데이터 수

    // 생성자1 (초기 용적 할당을 안할 경우)
    public MyDeque(){
        this.size = 1;
        this.data = new Object[this.size];
        this.count = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void doubling(){
        Object[] newData = new Object[this.size*2];
        for(int i = 0; i< this.count; i++){
            newData[i] = data[(this.front + i) % this.size];
        }
        this.data = newData;
        this.front = 0;
        this.rear = this.count;
        this.size = this.size*2;
    }

    //데이터 추가(맨 앞에 삽입)
    public void addFirst(E e){
        if(this.count == this.size){
            doubling();
        }
        this.front = (this.front-1+this.size)%this.size; // -1%5는 -1 -> 오류
        this.data[this.front] = e;
        this.count++;

    }
    //데이터 추가(덱 맨 뒤에 삽입)
    public void addLast(E e){
        if(this.count == this.size)
            doubling();
        this.data[this.rear] = e;
        this.rear = (this.rear + 1) % this.size;
        this.count ++;
    }

    //데이터 삭제( 맨 앞 삭제)
    @SuppressWarnings("unchecked")
    public E removeFirst() throws Exception{
        if(isEmpty()){
            throw new Exception("DequeUnderflow");
        }
        E removed = (E) this.data[this.front];
        this.data[this.front] = null;
        this.front = (this.front + 1) % this.size;
        this.count --;
        return removed;
    }

    //데이터 삭제(맨 뒤 삭제)
    @SuppressWarnings("unchecked")
    public E removeLast() throws Exception{
        if(isEmpty()){
            throw new Exception("DequeUnderflow");
        }
        this.rear = (this.rear-1+this.size) % this.size;
        E removed = (E) this.data[this.rear];
        this.data[this.rear] = null;
        this.count--;
        return removed;
    }

    //데이터 가져오기(맨 앞 데이터 가져오기(삭제 X))
    @SuppressWarnings("unchecked")
    public E peekFirst() throws Exception{
        if(isEmpty()){
            throw new Exception("DequeUnderflow");
        }
        return (E) this.data[this.front];
    }

    //데이터 가져오기(맨 뒤 데이터 가져오기(삭제 X))
    @SuppressWarnings("unchecked")
    public E peekLast() throws Exception{
        if(isEmpty()){
            throw new Exception("DequeUnderflow");
        }
        return (E) this.data[(this.rear-1 + this.size)%this.size];
    }

    public int size(){
        return this.count;
    }
    
    public boolean isEmpty(){
        return this.count == 0;
    }

    public boolean contains(E e){
        for(int i = 0; i<count; i++){
            if(this.data[(this.front + i) % this.size].equals(e))
                return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i<this.count; i++){
            sb.append(this.data[(this.front + i) % this.size]);
            if(i<this.count-1)
                sb.append("-->");
        }
        sb.append("]");
        return sb.toString();
    }

}
