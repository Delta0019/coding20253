import java.util.Scanner;

public class HW20250417_3 {

    static long[] inital_pre;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int r = input.nextInt() + 1, k = input.nextInt(), n = input.nextInt();
        long[] cities = new long[n];
        inital_pre = new long[n];

        for (int i = 0; i < n; ++i) {
            cities[i] = input.nextInt();

            if (i + r < n) {
                inital_pre[i + r] -= cities[i];
            }
            if (i - r + 1 >= 0) {
                inital_pre[i - r + 1] += cities[i];
            } else {
                inital_pre[0] += cities[i];
            }
        }
        input.close();

        long cur_cnt = 0;
        long right = 0, left = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            cur_cnt += inital_pre[i];
            left = Math.min(left, cur_cnt);
            right = Math.max(right, cur_cnt);
        }

        right += k;

        while (left < right) {
            long mid = left + (right - left + 1) / 2;
            if (check(mid, r, k, n)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);

    }

    public static boolean check(long target, int r, int k, int n) {
        long[] pre_sum = new long[n];
        long rest = k;
        long cur_cnt = 0;

        for (int i = 0; i < n; ++i) {
            cur_cnt += pre_sum[i] + inital_pre[i];

            if (cur_cnt >= target) {
                continue;
            }

            long diff = target - (cur_cnt);
            if (diff > rest) {
                return false;
            }
            rest -= diff;
            cur_cnt += diff;

            if (i + 2 * r - 1 < n) {
                pre_sum[i + 2 * r - 1] = -diff;
            }
        }
        return true;
    }
}