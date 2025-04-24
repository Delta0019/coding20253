import java.util.Arrays;
import java.util.PriorityQueue;

//  Key Point:
//      1. 常数级优化: dfs(对每个点，记录还有多少步能到达终点, 对每个点会遍历多次——即便是O(1)) 
//          -> heap for each col and row(对每个点，记录从原点来此需要的步数，对每个点只遍历一次)
//      2. 密集图，路径问题
class LC02617 {
    public int minimumVisitedCells(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int f = 0;

        // Heap<(f, g + i(j))>
        @SuppressWarnings("unchecked")
        PriorityQueue<int[]>[] colHs = new PriorityQueue[col];
        Arrays.setAll(colHs, i -> new PriorityQueue<int[]>((a, b) -> a[0] - b[0]));
        PriorityQueue<int[]> rowH = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]);

        for (int i = 0; i < row; ++i) {
            rowH.clear();
            for (int j = 0; j < col; ++j) {
                while (!rowH.isEmpty() && rowH.peek()[1] < j) {
                    rowH.poll();
                }

                PriorityQueue<int[]> colH = colHs[j];
                while (!colH.isEmpty() && colH.peek()[1] < i) {
                    colH.poll();
                }

                f = Integer.MAX_VALUE;
                if (i == 0 && j == 0) {
                    f = 1;
                }
                if (!colH.isEmpty()) {
                    f = colH.peek()[0] + 1;
                }
                if (!rowH.isEmpty()) {
                    f = Math.min(f, rowH.peek()[0] + 1);
                }

                int g = grid[i][j];
                if (g != 0 && f < Integer.MAX_VALUE) {
                    rowH.add(new int[] { f, j + g });
                    colH.add(new int[] { f, i + g });
                }
            }
        }

        return f == Integer.MAX_VALUE ? -1 : f;
    }
}

class Main_LC02617_beta {
    public static void main(String[] args) {
        int[][] grid = { { 0, 1, 0 } };
        LC02617 solution = new LC02617();
        int res = solution.minimumVisitedCells(grid);
        System.out.println(res);
    }
}
