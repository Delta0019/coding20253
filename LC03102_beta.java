import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class HW3102_beta {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public int minimumDistance(int[][] points) {
        Comparator<int[]> arrayComparator = new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] > b[0]) {
                    return 1;
                } else if (a[0] < b[0]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Arrays.sort(points, arrayComparator);

        fz(0, points.length - 1, points);
        minHeap.poll();

        return minHeap.peek();
    }

    public int compute_mhd(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int compute_mhd(int a, int b, int[][] points) {
        return compute_mhd(points[a], points[b]);
    }

    public void deal_set(Set<int[]> set) {
        List<int[]> list = new ArrayList<>(set);
        for (int i = 0; i < set.size(); ++i) {
            for (int j = 1; j < set.size(); ++j) {
                if (i == j) {
                    continue;
                }
                minHeap.add(compute_mhd(list.get(i), list.get(j)));
            }
        }
    }

    public void fz(int left, int right, int[][] points) {
        System.out.println("left: " + left + " right: " + right);

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        fz(left, mid, points);
        fz(mid + 1, right, points);
        merge(left, right, mid, points);
    }

    public void merge(int left, int right, int mid, int[][] points) {
        System.out.println("left: " + left + " right: " + right);
        if (right == left + 1) {
            minHeap.add(compute_mhd(left, right, points));
            System.out.println("base: " + minHeap);
            return;
        }

        int min1 = minHeap.peek();
        Set<int[]> set = new HashSet<>();

        int bound = mid;
        int middle = points[mid][0];
        while (bound >= left && (points[bound][0] >= middle - min1)) {
            set.add(points[bound]);
            --bound;
        }

        bound = mid + 1;
        while (bound <= right && (points[bound][0] <= middle + min1)) {
            set.add(points[bound]);
            ++bound;
        }

        deal_set(set);
    }
}

class Main_HW3102_beta {
    public static void main(String[] args) {
        int[][] points = { { 3, 10 }, { 5, 15 }, { 10, 2 }, { 4, 4 } };
        HW3102_beta solution = new HW3102_beta();
        System.out.println(solution.minimumDistance(points));
    }
}