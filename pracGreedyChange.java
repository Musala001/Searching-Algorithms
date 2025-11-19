import java.util.*;

public class pracGreedyChange {
    public static void main(String [] args){
        int [] arr = {1, 2, 5, 10, 20, 50, 100};
        System.out.println(GreedyChange(250, arr));
    }

    public static List<Integer> GreedyChange(int amount,int [] coins ){
        List<Integer> result = new ArrayList<>();

        int counter = 0;
        Arrays.sort(coins);

        for(int i = coins.length - 1; i >= 0; i--){
            while (amount >= coins[i]) {
                amount -= coins[i];
                counter++;
                result.add(coins[i]);
            }
        }
        System.out.println(counter);
        return result;
    }
}
