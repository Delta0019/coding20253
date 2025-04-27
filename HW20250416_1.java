import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class HW20250416_1 {

    static class idAndSet {
        int id;
        int len;

        idAndSet(int id, Set<Integer> set) {
            this.id = id;
            this.len = set.size();
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();

        Set<Integer>[] test2model = new HashSet[n];
        Arrays.setAll(test2model, i -> new HashSet<>());
        ArrayList<Integer>[] model2test = new ArrayList[m];
        Arrays.setAll(model2test, i -> new ArrayList<Integer>());

        Comparator<idAndSet> compare = new Comparator<idAndSet>() {
            @Override
            public int compare(idAndSet a, idAndSet b) {
                return b.len - a.len;
            }
        };

        PriorityQueue<idAndSet> maxH = new PriorityQueue<>(compare);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (input.nextInt() == 1) {
                    test2model[i].add(j);
                    model2test[j].add(i);
                }
            }

            maxH.add(new idAndSet(i, test2model[i]));
        }
        input.close();

        Set<Integer> covered = new HashSet<>();
        int min_cnt = 0;
        while (covered.size() != m && !maxH.isEmpty()) {
            idAndSet next = maxH.poll();
            while (!maxH.isEmpty() && next.len != test2model[next.id].size()) {
                next = maxH.poll();
            }
            if (maxH.isEmpty()) {
                break;
            }
            min_cnt += 1;

            ArrayList<Integer> nextRoll = new ArrayList<>();
            Set<Integer> models = new HashSet<>(test2model[next.id]);
            for (int model : models) {
                covered.add(model);
                for (int test : model2test[model]) {
                    test2model[test].remove(model);
                    nextRoll.add(test);
                }
            }
            for (int test : nextRoll) {
                maxH.add(new idAndSet(test, test2model[test]));
            }
        }

        min_cnt = covered.size() == m ? min_cnt : -1;
        System.out.println(min_cnt);

    }
}