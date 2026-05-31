package Array;
public class ArrayExample1 {
    public static void main(String[] args){
        int[] numbers = {10,20,30,40,50};

        System.out.println(numbers[2]);
        System.out.println(numbers[4]);

        numbers[2] = 100; // 배열 값 수정

        System.out.println("배열 출력");
        for(int number : numbers){
            System.out.println(number);
        }

        System.out.println("배열 출력");
        for(int i = 0; i<numbers.length; i++){
            System.out.println(numbers[i]);
        }
        
        System.out.println("배열의 합 구하기");
        int sum = 0;

        for(int number2 : numbers){
            sum += number2;
        }
        System.out.println(sum);

        System.out.println("배열의 평균 구하기");
        double average = (double)sum/numbers.length;
        System.out.println(average);
    

    }
}
