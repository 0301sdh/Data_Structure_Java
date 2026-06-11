package Stack;

public class MyStackTest {

    public static void main(String[] args) throws Exception{
        MyStack history = new MyStack();
        history.push("Google");
        history.push("Naver");
        history.push("Youtube");
        history.push("Github");

        System.out.println(history);
        // peek 테스트
        System.out.println("현재 페이지 : " + history.peek());  

        // pop 테스트
        history.pop();
        System.out.println("뒤로가기 후 : " + history.peek());  

        // contains 테스트
        System.out.println("Google 방문? : " + history.contains("Google")); 
        System.out.println("Youtube 방문? : " + history.contains("Youtube")); 

        // size, isEmpty 테스트
        System.out.println("기록 수 : " + history.size());  
        System.out.println("비어있나? : " + history.isEmpty()); 

        // 전체 pop
        history.pop();
        history.pop();
        history.pop();
        System.out.println("비어있나? : " + history.isEmpty());  

    }

}
