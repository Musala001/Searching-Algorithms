import java.util.Scanner;

public class Lab1Sub2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String grid = in.nextLine().trim();

        int index = grid.indexOf('#');

        int row = index / 3;
        int col = index % 3;

        // UP
        if (row > 0) {
            System.out.println("UP");
        }
        // DOWN
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

        in.close();
    }
}
