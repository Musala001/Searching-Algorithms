import java.util.*;

public class Lab2UCS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String start = in.nextLine().trim();
        String goal = in.nextLine().trim();

        System.out.println(ucs(start, goal));

        in.close();
    }

    public static int ucs(String start, String goal) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        Set<String> visited = new HashSet<>();

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited.contains(current.state))
                continue;
            visited.add(current.state);

            if (current.state.equals(goal))
                return current.cost;

            for (Move mv : neighbors(current.state)) {
                pq.add(new Node(mv.state, current.cost + mv.moveCost));
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
        int moveCost;

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

        if (row > 0) {
            result.add(new Move(apply(state, row, col, row - 1, col), 5));
        }
        if (row < 2) {
            result.add(new Move(apply(state, row, col, row + 1, col), 1));
        }
        if (col > 0) {
            result.add(new Move(apply(state, row, col, row, col - 1), 1));
        }
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
