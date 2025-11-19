import java.util.*;

public class Lab2Astar {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String start = in.nextLine().trim();
        String goal = in.nextLine().trim();

        System.out.println(astar(start, goal));

        in.close();
    }

    public static int astar(String start, String goal) {

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                if (a.f != b.f)
                    return a.f - b.f; // sort by f
                return a.state.compareTo(b.state); // tie-break lexicographically
            }
        });

        Set<String> visited = new HashSet<>();

        pq.add(new Node(start, 0, heuristic(start, goal))); // g=0, f=h

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited.contains(current.state))
                continue;
            visited.add(current.state);

            if (current.state.equals(goal))
                return current.g;

            for (String next : neighbors(current.state)) {
                int g2 = current.g + 1;
                int h2 = heuristic(next, goal);
                pq.add(new Node(next, g2, g2 + h2));
            }
        }

        return -1;
    }

    static class Node {
        String state;
        int g; // path cost so far
        int f; // g + h

        Node(String s, int gCost, int fCost) {
            state = s;
            g = gCost;
            f = fCost;
        }
    }

    public static int heuristic(String start, String goal) {
        int dist = 0;
        for (int i = 0; i < 9; i++) {
            char ch = start.charAt(i);
            if (ch == '#')
                continue;

            int gi = goal.indexOf(ch);

            int r1 = i / 3, c1 = i % 3;
            int r2 = gi / 3, c2 = gi % 3;

            dist += Math.abs(r1 - r2) + Math.abs(c1 - c2);
        }
        return dist;
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
}
