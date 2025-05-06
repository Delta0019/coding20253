import java.util.Scanner;

public class XC20250313_1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.next();
        StringBuilder sBuilder = new StringBuilder();
        int len = 0;
        int i = 1;
        while (len < s.length()) {
            sBuilder.append(s.charAt(len));
            len += i;
            i++;
        }
        System.out.println(sBuilder.toString());
        input.close();
    }
}
