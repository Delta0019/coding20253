import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HW20250417_1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        ArrayList<int[][]> iplist = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            String in = input.next();
            String[] ips = in.substring(1, in.length() - 1).split("\\,");

            int[] start = new int[4];
            String[] starts = ips[0].split("\\.");
            for (int j = 0; j < 4; ++j) {
                start[j] = Integer.valueOf(starts[j]);
            }

            int[] end = new int[4];
            String[] ends = ips[1].split("\\.");
            for (int j = 0; j < 4; ++j) {
                end[j] = Integer.valueOf(ends[j]);
            }

            iplist.add(new int[][] { start, end });
        }

        input.close();

        Comparator<int[][]> comparatorEnd = new Comparator<int[][]>() {
            public int compare(int[][] a, int[][] b) {
                for (int i = 0; i < 4; ++i) {
                    if (a[1][i] == b[1][i]) {
                        continue;
                    }
                    return a[1][i] - b[1][i];
                }
                return 0;
            }
        };

        Comparator<int[][]> comparatorStart = new Comparator<int[][]>() {
            public int compare(int[][] a, int[][] b) {
                for (int i = 0; i < 4; ++i) {
                    if (a[0][i] == b[0][i]) {
                        continue;
                    }
                    return a[0][i] - b[0][i];
                }
                return 0;
            }
        };

        Collections.sort(iplist, comparatorStart);

        ArrayList<int[][]> res = new ArrayList<>();

        int[] begin = null, end = null;
        for (int[][] ipPair : iplist) {
            if (begin == null) {
                begin = ipPair[0];
                end = ipPair[1];
                continue;
            }

            if (compInterval(end, ipPair[0])) {
                res.add(new int[][] { begin, end });
                begin = ipPair[0];
                end = ipPair[1];
            } else {
                if (compEnd(end, ipPair[1])) {
                    end = ipPair[1];
                }
            }
        }

        res.add(new int[][] { begin, end });

        for (int[][] ip : res) {
            int[] s = ip[0];
            int[] e = ip[1];
            System.out.print("[" + s[0] + "." + s[1] + "." + s[2] + "." + s[3] + ",");
            System.out.print(e[0] + "." + e[1] + "." + e[2] + "." + e[3] + "]");
        }
    }

    public static boolean compInterval(int[] a1, int[] b) {
        int[] a = new int[4];

        for (int i = 0; i < 4; ++i) {
            a[i] = a1[i];
        }

        int index = 3;
        while (a[index] == 255) {
            a[index] = 0;
            --index;
        }
        if (index >= 0) {
            a[index] += 1;
        }

        for (int i = 0; i < 4; ++i) {
            if (a[i] == b[i]) {
                continue;
            }
            return a[i] < b[i];
        }
        return false;
    }

    public static boolean compEnd(int[] a, int[] b) {
        for (int i = 0; i < 4; ++i) {
            if (a[i] == b[i]) {
                continue;
            }
            return a[i] < b[i];
        }
        return false;
    }
}