import java.util.Scanner;

public class NC16644 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int p1 = input.nextInt();
        int p2 = input.nextInt();
        int p3 = input.nextInt();
        StringBuilder str = new StringBuilder(input.next());
        for (int i = 1; i < str.length() - 1; ++i) {
            if (str.charAt(i) == '-' && isOrder(str.charAt(i - 1), str.charAt(i + 1))) {
                String product = dealStr(p1, p2, p3, str.substring(i - 1, i + 2));
                str.replace(i, i + 1, product);
            }
        }
        System.out.println(str);
        input.close();
    }

    public static boolean isOrder(char a, char b) {
        if (Character.isDigit(a) && Character.isDigit(b) && a < b) {
            return true;
        }
        if (Character.isLowerCase(a) && Character.isLowerCase(b) && a < b) {
            return true;
        }
        return false;
    }

    public static String dealStr(int p1, int p2, int p3, String str) {
        char pre = str.charAt(0);
        char post = str.charAt(2);

        StringBuilder product = new StringBuilder();

        for (int i = pre + 1; i < post; ++i) {
            char next = (char) i;
            if (p1 == 2 && Character.isLowerCase(pre)) {
                next -= 32;
            } else if (p1 == 3) {
                next = '*';
            }
            for (int j = 0; j < p2; ++j) {
                product.append(next);
            }
        }
        if (p3 == 2) {
            product.reverse();
        }
        return product.toString();
    }
}