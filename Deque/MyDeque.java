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

    //데이터 추가(덱 맨 뒤에 삽입)
    public void addLast(Object obj){
        if(this.count == this.size)
            doubling();
        
    }
}
