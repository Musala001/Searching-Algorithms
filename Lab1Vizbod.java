import java.util.*;

public class Lab1Vizbod {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String grid = in.nextLine().trim();
        in.close();

        char[] tiles = grid.toCharArray();
        int counter = 0;
        for (char c : tiles) {
            System.out.print(c + " ");
            counter++;
            if (counter == 3) {
                counter = 0;
                System.out.println();
            }
        }
    }
}
