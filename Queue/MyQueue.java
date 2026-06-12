package Queue;

public class MyQueue{
    private int size; //배열의 최대 크기
    private Object[] data;
    private int front;
    private int rear;
    private int count; //저장된 데이터 수

    public MyQueue(){
        this.size = 1;
        this.data = new Object[this.size];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    // 배열 크기를 늘리는 함수
    private void doubling(){
        Object[] newData = new Object[this.size*2];
        for(int i=0; i<this.count; i++){
            newData[i] = data[(this.front + i) % this.size];
        }
        this.data = newData;
        this.size = this.size*2;
        this.front = 0;
        this.rear = this.count;
    }

    // 데이터 추가(큐 맨 뒤에 삽입)
    public void enqueue(Object obj){
        if(this.count == this.size){
            doubling();
        }
        this.data[this.rear] = obj;
        this.rear = (this.rear + 1) % this.size;
        this.count++;
    }

    //데이터 제거(큐 맨 앞에서 꺼냄)
    public Object dequeue() throws Exception{
        if(isEmpty()){
            throw new Exception("QueueUnderFlow");
        }
        Object removed = this.data[this.front];
        this.front = (this.front + 1) % this.size;
        this.count--;
        return removed;
    }

    // 큐 맨 앞 데이터 확인(제거하지 않음)
    public Object peek() throws Exception{
        if(isEmpty()){
            throw new Exception("QueueUnderFlow");
        }
        return this.data[this.front];
    }

    // 큐에 저장된 데이터 크기 측정
    public int size(){
        return this.count;
    }

    // 큐가 비어있는지 확인
    public boolean isEmpty(){
        return this.count==0;
    }

    //데이터 검색
    public boolean contains(Object obj){
        for(int i = 0 ; i<this.count; i++){
            if(this.data[(this.front + i)% this.size].equals(obj))
                return true;
        }
        return false;
    }

    //데이터 출력
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i =0; i<this.count; i++){
            sb.append(this.data[(this.front+i)%this.size]);
            if(i<this.count -1)
                sb.append("-->");
        }
        sb.append("]");
        return sb.toString();
    }
}