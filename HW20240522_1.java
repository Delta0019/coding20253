import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HW20240522_1 {

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        String[] ints1 = input.nextLine().split("\\ ");
        String[] ints2 = input.nextLine().split("\\ ");

        for (String i : ints1) {
            list1.add(Integer.valueOf(i));
        }

        for (String i : ints2) {
            list2.add(Integer.valueOf(i));
        }

        input.close();

        int max_len = 0, n = list1.size(), m = list2.size();
        List<Integer> sub_list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int len = 0;
                int rpointer1 = i;
                int rpointer2 = j;

                while (list1.get(rpointer1) == list2.get(rpointer2)) {
                    ++len;
                    ++rpointer1;
                    ++rpointer2;
                    if (rpointer1 == n || rpointer2 == m) {
                        break;
                    }
                }

                if (len > max_len) {
                    max_len = len;
                    sub_list = list1.subList(i, rpointer1);
                }
            }
        }

        if (sub_list.size() == 0) {
            System.out.println(-1);
            return;
        }

        for (int i : sub_list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}