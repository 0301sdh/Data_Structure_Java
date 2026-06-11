package Stack;

public class MyStack {
    private int size; //스택의 최대 크기
    private int top; // 맨 위의 index
    private Object[] data; // 스택을 위한 배열

    public MyStack(){
        this.size = 1;
        this.data = new Object[this.size];
        this.top = 0;
    }

    // 배열 크기를 늘리는 함수
    private void doubling(){
        this.size = this.size*2;
        Object[] newData = new Object[this.size];
        for(int i=0; i<this.top; i++){
            newData[i] = data[i];
        }
        this.data = newData;
    }

    // 데이터 추가(스택 최상단에 삽입)
    public void push(Object obj){
        if(this.top == this.size){
            doubling();
        }
        this.data[this.top] = obj;
        this.top ++;
    }

    //데이터 제거(스택 최상단에서 꺼냄)
    public Object pop() throws Exception{
        if(isEmpty()){
            throw new Exception("StackUnderFlow");
        }
        this.top--;
        Object removed = data[this.top];
        data[this.top] = null;
        return removed;
    }

    // 스택 최상단 데이터 확인 (제거하지 않음)
    public Object peek() throws Exception{
        if(isEmpty()){
            throw new Exception("StackUnderFLow");
        }
        return data[this.top - 1];
    }

    // 스택에 저장된 데이터 크기 측정
    public int size(){
        return this.top;
    }

    // 스택이 비어있는지 확인
    public boolean isEmpty(){
        return this.top == 0;
    }

    // 데이터 검색
    public boolean contains(Object obj){
        for(int i = 0; i< this.top; i++){
            if(this.data[i].equals(obj))
                return true;
        }
        return false;
    }

    // 데이터 출력 (bottom -> top)
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i=0; i<this.top; i++){
            sb.append(this.data[i]);
            if(i<this.top-1){
                sb.append("-->");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
