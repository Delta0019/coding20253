import java.util.PriorityQueue;

class LC00055 {
    public boolean canJump(int[] nums) {
        // row: (f, g + f)
        PriorityQueue<int[]> rowH = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int len = nums.length;
        int f = 0;

        for (int i = 0; i < len; ++i) {
            while (!rowH.isEmpty() && rowH.peek()[1] < i) {
                rowH.poll();
            }

            f = Integer.MAX_VALUE;
            if (i == 0) {
                rowH.add(new int[] { 1, i + nums[i] });
                f = 1;
            }

            if (!rowH.isEmpty()) {
                f = rowH.peek()[0] + 1;
            }

            if (f < Integer.MAX_VALUE) {
                rowH.add(new int[] { f, i + nums[i] });
            }
        }

        return f != Integer.MAX_VALUE;
    }
}

class Main_LC00055 {
    public static void main(String[] args) {
        int[] nums = new int[] { 2, 3, 1, 1, 4 };
        LC00055 solution = new LC00055();
        boolean res = solution.canJump(nums);
        System.out.println(res);
    }
}
