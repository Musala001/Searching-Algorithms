import java.util.*;

public class pracsub1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String state = in.nextLine().trim();
        String action = in.nextLine().trim().toUpperCase();

        in.close();

        int blank = state.indexOf('#');
        int row = blank / 3;
        int col = blank % 3;

        switch (action) {
            case "UP":
                row--;
                break;
            case "DOWN":
                row++;
                break;
            case "LEFT":
                col--;
                break;
            case "RIGHT":
                col++;
                break;
            default:
                System.out.println(state);
                return;
        }

        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println(state);
        }

        int NewPos = row * 3 + col;

        char[] tiles = state.toCharArray();
        tiles[blank] = tiles[NewPos];
        tiles[NewPos] = '#';

        System.out.println(new String(tiles));
    }
}
