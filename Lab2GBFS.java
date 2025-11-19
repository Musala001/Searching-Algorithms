import java.util.*;

public class Lab2GBFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String start = in.nextLine().trim();
        String goal = in.nextLine().trim();

        System.out.println(gbfs(start, goal));

        in.close();
    }

    public static int gbfs(String start, String goal) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                if (a.heuristic != b.heuristic)
                    return a.heuristic - b.heuristic;
                return a.state.compareTo(b.state);
            }
        });

        Set<String> visited = new HashSet<>();

        pq.add(new Node(start, 0, heuristic(start, goal)));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited.contains(current.state))
                continue;
            visited.add(current.state);

            if (current.state.equals(goal))
                return current.depth;

            for (String next : neighbors(current.state)) {
                pq.add(new Node(next, current.depth + 1, heuristic(next, goal)));
            }
        }

        return -1;
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

    public static List<String> neighbors(String state) {
        List<String> result = new ArrayList<>();

        int blank = state.indexOf('#');
        int row = blank / 3;
        int col = blank % 3;

        if (row > 0)
            result.add(apply(state, row, col, row - 1, col)); // UP
        if (row < 2)
            result.add(apply(state, row, col, row + 1, col)); // DOWN
        if (col > 0)
            result.add(apply(state, row, col, row, col - 1)); // LEFT
        if (col < 2)
            result.add(apply(state, row, col, row, col + 1)); // RIGHT

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

    public static int heuristic(String state, String goal) {
        int dist = 0;
        for (int i = 0; i < state.length(); i++) {
            char c = state.charAt(i);
            if (c == '#')
                continue;
            int goalIndex = goal.indexOf(c);
            dist += Math.abs(i / 3 - goalIndex / 3) + Math.abs(i % 3 - goalIndex % 3);
        }
        return dist;
    }
}
