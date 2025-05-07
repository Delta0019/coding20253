import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HW20250423_1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int w = input.nextInt(), h = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();

        int[][] map = new int[h][w];
        ArrayList<int[]> equalm = new ArrayList<>();
        int midx = (w - 1) / 2, midy = (h - 1) / 2;
        int[] mid = new int[] { midx, midy };

        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                map[i][j] = input.nextInt();
                if (map[i][j] == m) {
                    equalm.add(new int[] { j, i });
                }
            }
        }

        input.close();

        Comparator<int[]> sortDis = new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int disa = dis(a, mid);
                int disb = dis(b, mid);
                if (disa != disb) {
                    return disa - disb;
                }
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        };

        Collections.sort(equalm, sortDis);

        List<int[]> res;
        if (equalm.size() >= k) {
            res = equalm.subList(0, k);
        } else {
            res = equalm;
        }

        for (int[] node : res) {
            System.out.print(node[0] + " " + node[1] + " ");
        }
        System.out.println();
    }

    public static int dis(int[] a, int[] mid) {
        return Math.abs(a[0] - mid[0]) + Math.abs(a[1] - mid[1]);
    }
}