import java.util.*;

public class prasub2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String grid = in.nextLine().trim();
        in.close();

        int blank = grid.indexOf('#');

        int row = blank / 3;
        int col = blank % 3;

        // UP
        if (row > 0) {
            System.out.println("UP");
        }
        // Down
        if (row < 2) {
            System.out.println("DOWN");
        }
        // LEFT
        if (col > 0) {
            System.out.println("LEFT");
        }
        // RIGHT
        if (col < 2) {
            System.out.println("RIGHT");
        }

    }
}
