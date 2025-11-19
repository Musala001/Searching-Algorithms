import java.util.*;

public class MinTotalWaitingTime {
    public static int minWait(int[] times) {
        Arrays.sort(times);   // greedy: shortest first
        int total = 0;
        int n = times.length;

        for (int i = 0; i < n; i++) {
            int peopleWaiting = n - i - 1;
            total += times[i] * peopleWaiting;
        }

        return total;
    }

    public static void main(String[] args) {
        int[] times = {3, 2, 4, 1};
        System.out.println(minWait(times));   // 10
    }
}


