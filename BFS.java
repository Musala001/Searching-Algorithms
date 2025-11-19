import java.util.*;

public class BFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String start = in.nextLine().trim();
        String goal = in.nextLine().trim();

        System.out.println(bfs(start, goal));
        in.close();
    }

    static class Node {
        String state;
        int cost;

        Node(String s, int c) {
            state = s;
            cost = c;
        }
    }

    static class Move {
        String state;
        int moveCost;

        Move(String s, int c) {
            state = s;
            moveCost = c;
        }
    }

    public static int bfs(String start, String goal) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (visited.contains(current.state))
                continue;
            visited.add(current.state);

            if (current.state.equals(goal)) {
                return current.cost;
            }

            for (Move mv : neighbors(current.state)) {
                queue.add(new Node(mv.state, current.cost + mv.moveCost));

            }
        }
        return -1;
    }

    public static List<Move> neighbors(String state) {
        List<Move> results = new ArrayList<>();

        int blank = state.indexOf('#');
        int row = blank / 3;
        int col = blank % 3;

        if (row > 0) {
            results.add(new Move(apply(state, row, col, row - 1, col), 1));
        }
        if (row < 2) {
            results.add(new Move(apply(state, row, col, row + 1, col), 1));
        }
        if (col > 0) {
            results.add(new Move(apply(state, row, col, row, col - 1), 1));
        }
        if (col < 2) {
            results.add(new Move(apply(state, row, col, row, col + 1), 1));
        }

        return results;
    }

    public static String apply(String state, int row, int col, int row1, int col1) {
        int blank = row * 3 + col;
        int newPos = row1 * 3 + col1;

        char[] tiles = state.toCharArray();
        tiles[blank] = tiles[newPos];
        tiles[newPos] = '#';

        return new String(tiles);
    }
}
