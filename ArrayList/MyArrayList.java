package ArrayList;

public class MyArrayList{
     private Object[] data;
     private int size;
     private int index; //다음 데이터 추가 위치 index

     public MyArrayList(){
        this.size = 1;
        this.data = new Object[this.size];
        this.index = 0;
     }

     //데이터 추가 함수
     public void add(Object obj){
        if(this.index == this.size){//할당한 배열이 다 찬 경우
            doubling();
        }
        data[this.index] = obj;
        this.index++;
     }

     // 배열 크기를 늘리는 함수
     public void doubling(){
        this.size = this.size*2;
        Object[] newData = new Object[this.size]; //배열의 크기를 2배로 늘려줌
        for(int i=0; i<data.length; i++){
            newData[i] = data[i]; //기존 배열 복사
        }
        this.data = newData;
     }
     // index로 data 가져오는 겨우
     public Object get(int i) throws Exception{
        if(i> this.index-1){// 가지고 있는 데이터보다 큰 경우
            throw new Exception("ArrayIndexOutOfBound");
        }
        else if(i<0){
            throw new Exception("NegativeValue");
        }
        return this.data[i];
     }
     //데이터 제거
     public void remove(int i) throws Exception{
        if(i > this.index-1){ // 가지고 있는 데이터보다 큰 경우
            throw new Exception("ArrayIndexOutOfBound");
        }
        else if(i<0){
            throw new Exception("NegativeValue");
        }
        //삭제할 데이터 위치 기준으로 한칸씩 앞으로 덮어씌움
        for(int x=i; x < this.data.length-1; x++){
            data[x] = data[x+1];
        }
        this.index--;
     }

     //데이터 중간 삽입 -> 다시 고민하기
     public void insert(int i, Object obj) throws Exception{
        if(i > this.index-1){ // 가지고 있는 데이터보다 큰 경우
            throw new Exception("ArrayIndexOutOfBound");
        }
        else if(i<0){
            throw new Exception("NegativeValue");
        }

        if(this.index == this.size){//할당한 배열이 다 찬 경우
            doubling();
        }
        // 데이터를 추가할 자리까지 데이터를 역순으로 뒤로 이동
        this.index++;
        for(int x = this.data.length-1; x >= i; x--){
            this.data[x+1] = this.data[x];
        }
        this.data[i] = obj;
     }
     //데이터 검색

}