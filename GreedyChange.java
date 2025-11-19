import java.util.*;

public class GreedyChange {

    public static List<Integer> makeChange(int amount, int[] coins) {
        Arrays.sort(coins);    
        List<Integer> result = new ArrayList<>();
        int mincoin = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                result.add(coins[i]);
                mincoin++;     
            }
        }
        System.out.println(mincoin);
        return result;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10, 20, 50, 100};
        System.out.println(makeChange(250, coins));

     
    }
}

