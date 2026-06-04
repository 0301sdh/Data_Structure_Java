package Stack;

import java.util.Stack;


public class StackExample1{
    public static void main(String[] args){
        
        Stack<String> history = new Stack<> ();

        history.push("Google");
        history.push("Naver");
        history.push("Youtube");

        System.out.println("현재 기록");
        System.out.println(history);

        String currentPage = history.pop();

        System.out.println("현재 페이지 : " + currentPage);

        System.out.println("뒤로가기 후 페이지 : " + history.peek());


        System.out.println("나머지 출력");
        while(!history.isEmpty()){
            System.out.println(history.pop());
        }
    }
}
