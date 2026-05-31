public class ArrayExample2 {
    public static void main(String[] args){
        int[] numbers = {10,20,30,40,50};
        int max = numbers[0];
        for(int number : numbers){
            if(number>= max){
                max = number;
            }
        }
        System.out.println(max);

        int min = numbers[0];
        for(int number : numbers){
            if(number<=min){
                min = number;
            }
        }
        System.out.println(min);
        

    }
}
