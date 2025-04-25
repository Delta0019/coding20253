import java.util.ArrayList;
import java.util.Scanner;

public class HW20250416_1 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> models = new ArrayList<>();
        ArrayList<ArrayList<Integer>> tests = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();

        int[][] covers = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                covers[i][j] = input.nextInt();
                if (covers[i][j] == 1) {
                    models.get(j).add(i);
                    tests.get(i).add(j);
                }
            }
        }

    }
}