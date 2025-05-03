import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HW20240522_2 {

    static String[][] net;
    static int n, m;
    static HashSet<String> notNum = new HashSet<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        n = input.nextInt();
        m = input.nextInt();

        net = new String[n][m];
        int[] s = new int[2], e = new int[2];
        ArrayList<int[]> cs = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                String next = input.next();
                if (Objects.equals("S", next)) {
                    s = new int[] { i, j };
                }
                if (Objects.equals("E", next)) {
                    e = new int[] { i, j };
                }
                if (Objects.equals("C", next)) {
                    cs.add(new int[] { i, j });
                }
                net[i][j] = next;
            }
        }
        input.close();
        notNum.add("S");
        notNum.add("E");
        notNum.add("C");

        int min_cost = Integer.MAX_VALUE;
        for (int[] c : cs) {
            int resC = find(s, c, 'C');
            if (resC == -1) {
                continue;
            }
            int resE = find(c, e, 'E');
            if (resE == -1) {
                continue;
            }
            min_cost = Math.min(min_cost, resC + resE);
        }

        min_cost = min_cost == Integer.MAX_VALUE ? -1 : min_cost;
        System.out.println(min_cost);
    }

    public static Integer deal(int i, int j, int cost, int[][] visited, int[] e, PriorityQueue<int[]> heap, char c) {
        visited[i][j] = 1;

        if (c == 'C') {
            if (Objects.equals(net[i][j], "C")) {
                if (i == e[0] && j == e[1]) {
                    return cost;
                }
            }
        }

        if (c == 'E') {
            if (Objects.equals(net[i][j], "E")) {
                return cost;
            }
        }

        int delta = 0;
        if (!notNum.contains(net[i][j])) {
            delta = Integer.valueOf(net[i][j]);
        }

        heap.add(new int[] { i, j, cost + delta });
        return null;
    }

    public static Integer find(int[] s, int[] e, char c) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            // a: i, j, cur_cost
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        };
        int[][] visited = new int[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>(comparator);
        heap.add(new int[] { s[0], s[1], 0 });
        visited[s[0]][s[1]] = 1;
        while (!heap.isEmpty()) {
            int[] new_node = heap.poll();
            int i = new_node[0], j = new_node[1], cost = new_node[2];
            if (i - 1 >= 0 && !Objects.equals(net[i - 1][j], "B") && visited[i - 1][j] == 0) {
                Integer res = deal(i - 1, j, cost, visited, e, heap, c);
                if (res != null) {
                    return res;
                }
            }
            if (i + 1 < n && !Objects.equals(net[i + 1][j], "B") && visited[i + 1][j] == 0) {
                Integer res = deal(i + 1, j, cost, visited, e, heap, c);
                if (res != null) {
                    return res;
                }
            }
            if (j - 1 >= 0 && !Objects.equals(net[i][j - 1], "B") && visited[i][j - 1] == 0) {
                Integer res = deal(i, j - 1, cost, visited, e, heap, c);
                if (res != null) {
                    return res;
                }
            }
            if (j + 1 < m && !Objects.equals(net[i][j + 1], "B") && visited[i][j + 1] == 0) {
                Integer res = deal(i, j + 1, cost, visited, e, heap, c);
                if (res != null) {
                    return res;
                }
            }
        }
        return -1;
    }
}