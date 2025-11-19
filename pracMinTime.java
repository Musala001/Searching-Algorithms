import java.util.Arrays;

public class pracMinTime {
    public static void main(String[] args) {
        int [] arr = {3, 2, 4, 1};
        System.out.println(MinTime(arr));
    }

    public static int MinTime(int [] arr){
        Arrays.sort(arr);
        int total = 0;

        for(int i = 0; i < arr.length; i++){
            int numOfPeople = arr.length - i - 1;
            total += arr[i] * numOfPeople;
        }
        return total;
    }
}
