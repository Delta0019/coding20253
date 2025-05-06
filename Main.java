import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> zyTable = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), k = input.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            a.add(input.nextInt());
        }
        input.close();

        if (k == n) {
            System.out.println(0);
            return;
        }

        zyTable.add(2);
        for (int i = 3; i < 1e4; ++i) {
            boolean flag = true;
            for (int j : zyTable) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                zyTable.add(i);
            }
        }

        int total = 0;
        int interval = 0;
        int min_interval = Integer.MAX_VALUE;
        int pre_num = 0;
        for (int i = 0; i < n; ++i) {
            int num = getNumOfZy(a.get(i));
            total += num;
            if (i < k) {
                interval += num;
            } else {
                pre_num = getNumOfZy(a.get(i - k));
                interval += num - pre_num;
                min_interval = Math.min(min_interval, interval);
            }
        }
        System.out.println(total - min_interval);

    }

    public static int getNumOfZy(int a) {
        int result = 0;
        for (int i : zyTable) {
            if (a < i) {
                break;
            }
            if (a % i == 0) {
                result++;
            }
        }
        return result;
    }
}