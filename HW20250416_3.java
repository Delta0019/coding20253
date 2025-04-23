import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class HW20250416_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        LinkedList<int[][]> ips = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            String ip1 = input.next();
            String ip2 = input.next();

            String[] nodes = ip1.split("\\.");
            int[] part1 = new int[4];
            for (int j = 0; j < 4; ++j) {
                part1[j] = Integer.valueOf(nodes[j]);
            }
            nodes = ip2.split("\\.");
            int[] part2 = new int[4];
            for (int j = 0; j < 4; ++j) {
                part2[j] = Integer.valueOf(nodes[j]);
            }

            ips.add(new int[][] { part1, part2 });
        }

        Comparator<int[][]> comparator_ip = new Comparator<int[][]>() {
            public int compare(int[][] a, int[][] b) {
                int[] a_end = a[1], b_end = b[1];
                int i = 0;
                while (i <= 3) {
                    if (a_end[i] == b_end[i]) {
                        ++i;
                        continue;
                    }
                    return a_end[i] - b_end[i];
                }
                int a_cnt = ip_diff(a[0], a[1]);
                int b_cnt = ip_diff(b[0], b[1]);
                return a_cnt - b_cnt;
            }
        };

        Collections.sort(ips, comparator_ip);

        ArrayList<int[][]> res = new ArrayList<>();
        int[] last = new int[4];
        while (!ips.isEmpty()) {
            int[][] next = ips.removeFirst();
            int[] start = next[0];
            if (compatible(last, start)) {
                res.add(next);
                last = next[1];
            }
        }

        output(res);
        input.close();
    }

    public static int ip_diff(int[] ip2, int[] ip1) {
        int cnt = 0;
        for (int i = 0; i < 4; ++i) {
            int exp = (int) Math.pow(256, 3 - i);
            cnt += (ip1[i] - ip2[i]) * exp;
        }
        return cnt;
    }

    public static boolean compatible(int[] last, int[] begin) {
        for (int i = 0; i < 4; ++i) {
            if (last[i] == begin[i]) {
                continue;
            }
            return last[i] < begin[i];
        }
        return false;
    }

    public static void output(ArrayList<int[][]> ips) {
        for (int[][] list : ips) {
            for (int[] l : list) {
                for (int i = 0; i < 3; i++) {
                    System.out.print(l[i] + ".");
                }
                System.out.print(l[3] + " ");
            }
            System.out.println();
        }
    }
}