package Array;
public class ArrayExample4{
    public static void main(String[] args){
        int[] numbers = {1,2,3,4,5,6};
        int count = 0;
        for(int number : numbers){
            if(number % 2 == 0){
                count ++;
            }
        }
        System.out.println("짝수의 개수 : " + count);
    }
}