import java.util.ArrayList;
import java.util.Scanner;

public class Meituan202503022Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while (input.hasNext()) {
            int n = input.nextInt();
            int cnt = n;
            ArrayList<Integer> ps = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ps.add(input.nextInt());
            }
            for (int i = 3; i <= ps.size(); i += 2) {
                for (int j = 0; j <= ps.size() - i; j++) {
                    if (isGA(ps, j, j + i)) {
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
        input.close();
    }

    public static boolean isGA(ArrayList<Integer> ps, int l, int r) {
        System.out.println("l: " + l + " r " + r);
        int index = (l + r) / 2;
        int mid = ps.get(index);
        System.out.println("mid: " + mid);
        int cnt_left = 0;
        int cnt_right = 0;
        for (int i = l; i < r; i++) {
            int c = ps.get(i);
            if (c <= mid) {
                cnt_left++;
            }
            if (c >= mid) {
                cnt_right++;
            }
        }
        return cnt_left == cnt_right;
    }
}