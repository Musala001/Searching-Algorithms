import java.util.*;

public class Lab1Sub3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String start = in.nextLine().trim();
        String goal = in.nextLine().trim();

        System.out.println(bfs(start, goal));

        in.close();
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

            if (current.state.equals(goal))
                return current.cost;

            for (Move mv : neighbors(current.state)) {
                // for BFS every move costs 1 step regardless of direction
                queue.add(new Node(mv.state, current.cost + 1));
            }
        }

        return -1;
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
        int moveCost; // not used by BFS but kept for same structure

        Move(String s, int c) {
            state = s;
            moveCost = c;
        }
    }

    public static List<Move> neighbors(String state) {
        List<Move> result = new ArrayList<>();

        int blank = state.indexOf('#');
        int row = blank / 3;
        int col = blank % 3;

        // UP
        if (row > 0) {
            result.add(new Move(apply(state, row, col, row - 1, col), 1));
        }
        // DOWN
        if (row < 2) {
            result.add(new Move(apply(state, row, col, row + 1, col), 1));
        }
        // LEFT
        if (col > 0) {
            result.add(new Move(apply(state, row, col, row, col - 1), 1));
        }
        // RIGHT
        if (col < 2) {
            result.add(new Move(apply(state, row, col, row, col + 1), 1));
        }

        return result;
    }

    public static String apply(String state, int r1, int c1, int r2, int c2) {
        int i1 = r1 * 3 + c1;
        int i2 = r2 * 3 + c2;

        char[] arr = state.toCharArray();
        arr[i1] = arr[i2];
        arr[i2] = '#';

        return new String(arr);
    }
}
