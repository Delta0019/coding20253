import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HW20250417_2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PriorityQueue<String> minH = new PriorityQueue<>();
        LinkedList<String> list = new LinkedList<>();
        HashSet<String> toBeRemoved = new HashSet<>();

        int q = input.nextInt();
        input.nextLine();
        while (q > 0) {
            --q;
            String opera = input.nextLine();
            String pre = opera.substring(0, 2);
            switch (pre) {
                case "in": {
                    String s = opera.split("\\ ")[1];
                    minH.add(s);
                    list.add(s);
                }
                    break;
                case "ou": {
                    String out = list.removeLast();
                    toBeRemoved.add(out);
                }
                    break;
                case "co": {
                    int cnt = list.size();
                    System.out.println(cnt);
                }
                    break;
                case "ch": {
                    while (!minH.isEmpty()) {
                        String peek = minH.peek();
                        if (toBeRemoved.contains(peek)) {
                            toBeRemoved.remove(peek);
                            minH.poll();
                            continue;
                        }
                        break;
                    }
                    String peek = minH.isEmpty() ? "EMPTY" : minH.peek();
                    System.out.println(peek);
                }
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
        input.close();
    }
}