import java.util.*;

public class Lab1Sub1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String state = in.nextLine().trim();
        String action = in.nextLine().trim().toUpperCase();

        in.close();

        int blank = state.indexOf('#');
        int row = blank / 3;
        int col = blank % 3;

        int targetRow = row;
        int targetCol = col;

        switch (action) {
            case "UP":
                targetRow--;
                break;
            case "DOWN":
                targetRow++;
                break;
            case "LEFT":
                targetCol--;
                break;
            case "RIGHT":
                targetCol++;
                break;
            default:
                System.out.println(state);
                return;
        }

        // invalid move → return original state
        if (targetRow < 0 || targetRow > 2 || targetCol < 0 || targetCol > 2) {
            System.out.println(state);
            return;
        }

        int targetIndex = targetRow * 3 + targetCol;

        char[] tiles = state.toCharArray();
        tiles[blank] = tiles[targetIndex];
        tiles[targetIndex] = '#';

        System.out.println(new String(tiles));
    }
}
