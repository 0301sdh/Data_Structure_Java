package Array;
public class ArrayExample3 {
    public static void main(String[] args){
        int[] numbers = {10,20,30,40,50};

        int target = 30;
        boolean found = false;
        for(int number : numbers){
            if(target == number){
                found = true;
            }
        }

        System.out.println(found);
    }
}
