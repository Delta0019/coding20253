import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class HW3102 {
    public int minimumDistance(int[][] points) {

        for (int i = 0; i < points.length; ++i) {
            int x = points[i][0];
            int y = points[i][1];
            points[i][0] = x + y;
            points[i][1] = x - y;
        }

        List<int[]> list_x = new ArrayList<>();
        for (int[] point : points) {
            list_x.add(point);
        }

        List<int[]> list_y = new ArrayList<>();
        for (int[] point : points) {
            list_y.add(point);
        }

        Comparator<int[]> xComparator = new Comparator<int[]>() {
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

        Collections.sort(list_x, xComparator);

        Comparator<int[]> yComparator = new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[1] > b[1]) {
                    return 1;
                } else if (a[1] < b[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(list_y, yComparator);

        int last = points.length - 1;
        int min = Integer.MAX_VALUE;

        // x[0]
        min = Math.min(min, remove_cal(list_x, list_y, 0));

        // x[last]
        min = Math.min(min, remove_cal(list_x, list_y, last));

        // y[0]
        min = Math.min(min, remove_cal(list_y, list_x, 0));

        // y[last]
        min = Math.min(min, remove_cal(list_y, list_x, last));

        return min;
    }

    public int remove_cal(List<int[]> list1, List<int[]> list2, int index) {
        int last = list1.size() - 1;
        int[] removed = list1.remove(index);
        int res;

        if (list2.get(0) == removed) {
            list2.remove(0);
            res = compute_dis(list1, list2);
            list2.add(0, removed);
        } else if (list2.get(last) == removed) {
            list2.remove(last);
            res = compute_dis(list1, list2);
            list2.add(last, removed);
        } else {
            res = compute_dis(list1, list2);
        }
        list1.add(index, removed);

        return res;
    }

    public int compute_dis(List<int[]> point_x, List<int[]> point_y) {
        int disx = qbxf_dis(point_x.get(0), point_x.get(point_x.size() - 1));
        int disy = qbxf_dis(point_y.get(0), point_y.get(point_y.size() - 1));
        return Math.max(disx, disy);
    }

    public int qbxf_dis(int[] a, int[] b) {
        return Math.max(Math.abs(a[0] - b[0]), Math.abs(a[1] - b[1]));
    }
}

class Main_HW3102 {
    public static void main(String[] args) {
        HW3102 solution = new HW3102();
        int[][] points = { { 5, 4 }, { 3, 4 }, { 10, 5 }, { 5, 4 }, { 5, 5 }, { 3, 7 } };
        System.out.println(solution.minimumDistance(points));
    }

}
