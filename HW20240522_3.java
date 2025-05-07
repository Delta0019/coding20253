import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HW20240522_3 {

    static HashMap<Integer, ArrayList<Integer>> index2info;
    static HashMap<Integer, ArrayList<Integer>> group2indexes;
    static HashMap<Integer, Integer> parent;
    static HashMap<Integer, ArrayList<Integer>> children;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int b = input.nextInt(), n = input.nextInt(), m = input.nextInt();
        index2info = new HashMap<>();
        group2indexes = new HashMap<>();
        parent = new HashMap<>();
        children = new HashMap<>();
        dp = new int[m][b + 1];

        for (int i = 0; i < n; ++i) {
            int i1 = input.nextInt(), i2 = input.nextInt(), i3 = input.nextInt();
            index2info.put(i, new ArrayList<>());
            index2info.get(i).add(i1);
            index2info.get(i).add(i2);
            index2info.get(i).add(i3);
            if (!group2indexes.containsKey(i1)) {
                group2indexes.put(i1, new ArrayList<>());
            }
            group2indexes.get(i1).add(i);
        }

        for (int i = 0; i < m; ++i) {
            int p = input.nextInt();
            parent.put(i, p);
            if (!children.containsKey(p)) {
                children.put(p, new ArrayList<>());
            }
            children.get(p).add(i);
        }

        input.close();

        int root = children.get(-1).get(0);
        solve(root, b);
        System.out.println(dp[root][b]);
    }

    public static void solve(int root, int b) {
        int[] packet = new int[b + 1];

        // 虽然其中部分点会被重复求，但存储结果用于下一次会使得代码略显冗余
        for (int node : group2indexes.get(root)) {
            int cost = index2info.get(node).get(2);
            int value = index2info.get(node).get(1);
            for (int i = b; i >= cost; --i) {
                packet[i] = Math.max(packet[i], value + packet[i - cost]);
            }
        }
        dp[root][b] = packet[b];

        if (children.containsKey(root)) {
            for (int child : children.get(root)) {
                int cur = 0;
                for (int i = 0; i <= b; ++i) {
                    solve(child, b - i);
                    if (packet[i] != 0) {
                        cur = Math.max(cur, dp[child][b - i] + packet[i]);
                    }
                }
                dp[root][b] = Math.max(dp[root][b], cur);
            }
        }
    }
}