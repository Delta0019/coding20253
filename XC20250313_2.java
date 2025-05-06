import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class XC20250313_2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int t = 0; t < T; ++t) {
            int n = input.nextInt();
            Integer a[] = new Integer[n];
            for (int i = 0; i < n; ++i) {
                a[i] = input.nextInt();
            }
            Arrays.sort(a, Collections.reverseOrder());
            int res = 0;
            for (int i = 0; i < n; ++i) {
                res = Math.max(res, i + a[i] + 1);
            }
            System.out.println(res);
        }
        input.close();
    }
}