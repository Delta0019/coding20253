import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class HW20250416_2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<Integer, String> hash2name = new HashMap<>();
        HashSet<Integer> visitedNode = new HashSet<>();
        HashSet<Integer> visitedLine = new HashSet<>();
        HashMap<Integer, ArrayList<Integer>> line2nodes = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> node2lines = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> lineFromSrc = new HashMap<>();
        ArrayList<Integer> filledLine = new ArrayList<>();

        int n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n + 1; ++i) {
            filledLine.add(-1);
        }

        for (int i = 0; i < n; ++i) {
            line2nodes.put(i, new ArrayList<>());

            String[] nodes = input.nextLine().split("\\ ");
            for (String node : nodes) {
                int hash = node.hashCode() * 13;
                line2nodes.get(i).add(hash);
                hash2name.put(hash, node);
                if (!node2lines.containsKey(hash)) {
                    node2lines.put(hash, new ArrayList<>());
                }
                node2lines.get(hash).add(i);
            }
        }

        int src = input.next().hashCode() * 13, dst = input.next().hashCode() * 13;

        input.close();

        LinkedList<Integer> toVisit = new LinkedList<>();
        for (int line : node2lines.get(src)) {
            lineFromSrc.put(line, new ArrayList<>());
            toVisit.add(line);
        }
        visitedNode.add(src);

        ArrayList<Integer> line2dst = src == dst ? new ArrayList<>() : null;

        while (line2dst == null && !toVisit.isEmpty()) {

            int nextline = toVisit.poll();
            visitedLine.add(nextline);

            for (int node : line2nodes.get(nextline)) {
                if (visitedNode.contains(node)) {
                    continue;
                }

                if (node == dst) {
                    line2dst = lineFromSrc.get(nextline);
                    break;
                }

                visitedNode.add(node);

                for (int line : node2lines.get(node)) {
                    if (lineFromSrc.containsKey(line)) {
                        continue;
                    }

                    ArrayList<Integer> newLineFromSrc = new ArrayList<>(lineFromSrc.get(nextline));
                    newLineFromSrc.add(node);
                    lineFromSrc.put(line, newLineFromSrc);
                    toVisit.add(line);
                }
            }
        }

        if (line2dst == null) {
            System.out.println("NA");
        } else {
            System.out.print(hash2name.get(src));
            for (int node : line2dst) {
                System.out.print("-" + hash2name.get(node));
            }
            System.out.println("-" + hash2name.get(dst));
            int cost = src == dst ? 0 : line2dst.size() + 2;
            System.out.println(cost);
        }
    }
}