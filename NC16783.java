import java.util.ArrayList;
import java.util.Scanner;

public class NC16783 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            nums.add(input.nextInt());
        }
    }
}