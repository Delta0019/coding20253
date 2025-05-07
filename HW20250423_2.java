import java.util.Scanner;

public class HW20250423_2 {
    static int[] cur;
    static int[] target;
    static int ans = 0;

    public static int next(int n) {
        switch (n) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 1;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        cur = new int[n];
        target = new int[n];

        for (int i = 0; i < n; i++) {
            cur[i] = input.nextInt();
        }

        for (int i = 0; i < n; i++) {
            target[i] = input.nextInt();
        }
        input.close();

        for (int i = 0; i < n; i++) {
            int time = 0;
            int temp = cur[i];
            while (temp != target[i]) {
                temp = next(temp);
                time++;
            }
            dfs(i, time);
            ans += time;
        }

        System.out.println(ans);

    }

    public static void dfs(int curSite, int time) {
        if (time == 0) {
            return;
        }
        if (curSite >= cur.length) {
            return;
        }
        if (cur[curSite] == 0) {
            return;
        }
        int temp = cur[curSite];
        for (int i = 0; i < time; i++) {
            temp = next(temp);
        }
        cur[curSite] = temp;
        dfs(2 * curSite + 1, time);
        dfs(2 * curSite + 2, time);
    }
}
