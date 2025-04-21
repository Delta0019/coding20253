class HW2617_beta {
    int col, row;
    int[][] to_dest;
    boolean[][] searched;

    public int minimumVisitedCells(int[][] grid) {
        col = grid.length;
        row = grid[0].length;
        to_dest = new int[col][row];
        searched = new boolean[col][row];

        return dfs(0, 0, grid);
    }

    public int dfs(int a, int b, int[][] grid) {
        if (a == col - 1 && b == row - 1) {
            return 1;
        }
        if (searched[a][b]) {
            return to_dest[a][b];
        }

        int min_step = Integer.MAX_VALUE;
        int ele = grid[a][b];
        for (int i = 1; i <= ele; ++i) {
            if (a + i >= grid.length) {
                break;
            }
            int res = dfs(a + i, b, grid);
            if (res < min_step && res != -1) {
                min_step = res;
            }
        }
        for (int i = 1; i <= ele; ++i) {
            if (b + i >= grid[0].length) {
                break;
            }
            int res = dfs(a, b + i, grid);
            if (res < min_step && res != -1) {
                min_step = res;
            }
        }

        searched[a][b] = true;
        if (min_step == Integer.MAX_VALUE) {
            to_dest[a][b] = -1;
        } else {
            ++min_step;
            to_dest[a][b] = min_step;
        }
        return to_dest[a][b];
    }
}

class Main_HW2617_beta {
    public static void main(String[] args) {
        int[][] grid = { { 2, 1, 0 }, { 1, 0, 0 } };
        HW2617_beta solution = new HW2617_beta();
        int res = solution.minimumVisitedCells(grid);
        System.out.println(res);
    }
}
