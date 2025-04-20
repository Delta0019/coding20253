import java.util.HashMap;
import java.util.PriorityQueue;

//  Key points: 
//      1. A large variaty of boundary condition
class HW1224 {
    HashMap<Integer, Integer> map = new HashMap<>();
    int max_cnt = 1;
    int index_len = 2;
    PriorityQueue<Pair> heap = new PriorityQueue<>(
            (a, b) -> a.value - b.value);

    public int maxEqualFreq(int[] nums) {

        for (int i = 0; i < nums.length; ++i) {
            int next = nums[i];
            if (map.containsKey(next)) {
                int num_cnt = map.get(next);
                ++num_cnt;
                if (num_cnt > max_cnt) {
                    max_cnt = num_cnt;
                }
                map.put(next, num_cnt);
            } else {
                map.put(next, 1);
            }

            heap.add(new Pair(next, map.get(next)));

            while (!heap.isEmpty()) {
                Pair peek = heap.peek();
                // System.out.println("peek: " + peek);
                // System.out.println("max: " + max_cnt);
                if (peek.value == map.get(peek.key)) {
                    // case1 n * m
                    if (max_cnt == peek.value && i != nums.length - 1) {
                        index_len = i + 2;
                    }
                    // case2 n * m + 1
                    if (1 == peek.value && heap.size() > 2) {
                        heap.poll();

                        while (!heap.isEmpty()) {
                            Pair pair = heap.peek();
                            if (pair.value != map.get(pair.key)) {
                                heap.poll();
                                continue;
                            }
                            break;
                        }
                        if (max_cnt == heap.peek().value) {
                            index_len = i + 1;
                        }

                        heap.add(peek);
                    }
                    // case3 (n - 1) * m + n
                    if (max_cnt - 1 == peek.value) {
                        if ((max_cnt - 1) * map.size() + 1 == i + 1) {
                            index_len = i + 1;
                        }
                    }
                    // case4 n * 1
                    if (max_cnt == 1) {
                        if (i + 1 == map.size()) {
                            index_len = i + 1;
                        }
                    }
                    // case5 1 * m
                    if (max_cnt == i + 1) {
                        index_len = i + 1;
                    }
                    break;
                } else {
                    heap.poll();
                }
            }
        }

        return index_len;
    }

    class Pair {
        public int key;
        public int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "<" + key + " , " + value + ">";
        }
    }
}

class Main_HW1224 {
    public static void main(String[] args) {
        HW1224 solution = new HW1224();
        int[] nums = { 1, 1 };
        int res = solution.maxEqualFreq(nums);
        System.out.println(res);
    }
}
