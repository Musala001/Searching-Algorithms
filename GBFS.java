import java.util.*;

public class GBFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String start = in.nextLine().trim();
        String goal = in.nextLine().trim();
        System.out.println(gbfs(start, goal));
        in.close();
    }

    static class Node {
        String state;
        int depth;
        int heuristic;

        Node(String s, int d, int h) {
            state = s;
            depth = d;
            heuristic = h;
        }
    }

    public static int gbfs(String start, String goal) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                if (a.heuristic != b.heuristic)
                    return a.heuristic - b.heuristic;
                return a.state.compareTo(b.state);
            }
        });

        Set<String> visited = new HashSet<>();

        queue.add(new Node(start, 0, heuristic(start, goal)));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (visited.contains(current.state))
                continue;
            visited.add(current.state);

            if (current.state.equals(goal))
                return current.depth;

            for (String next : neighbors(current.state)) {
                queue.add(new Node(next, current.depth + 1, heuristic(next, goal)));

            }
        }
        return -1;
    }

    public static List<String> neighbors(String state) {
        List<String> results = new ArrayList<>();

        int blank = state.indexOf('#');
        int row = blank / 3;
        int col = blank % 3;

        if (row > 0) {
            results.add(apply(state, row, col, row - 1, col));
        }
        if (row < 2) {
            results.add(apply(state, row, col, row + 1, col));
        }
        if (col > 0) {
            results.add(apply(state, row, col, row, col - 1));
        }
        if (col < 2) {
            results.add(apply(state, row, col, row, col + 1));
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

    public static int heuristic(String start, String goal) {
        int dist = 0;
        for (int i = 0; i < start.length(); i++) {
            char c = start.charAt(i);
            if (c == '#')
                continue;
            int g = goal.indexOf(c);
            dist = dist + Math.abs(i / 3 - g / 3) + Math.abs(i % 3 - g % 3);
        }
        return dist;
    }
}
