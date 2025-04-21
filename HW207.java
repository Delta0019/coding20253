import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] asPre = new ArrayList[numCourses];
        Arrays.setAll(asPre, i -> new ArrayList<>());
        int[] unfinPre = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < prerequisites.length; ++i) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            unfinPre[course] += 1;
            asPre[pre].add(course);
        }

        for (int i = 0; i < numCourses; ++i) {
            if (unfinPre[i] == 0) {
                queue.add(i);
            }
        }

        if (queue.isEmpty()) {
            return false;
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int post_course : asPre[course]) {
                unfinPre[post_course] -= 1;
                if (unfinPre[post_course] == 0) {
                    queue.add(post_course);
                }
            }
        }

        int fin = 0;
        for (int i = 0; i < numCourses; ++i) {
            if (unfinPre[i] == 0) {
                ++fin;
            }
        }

        return fin == numCourses;
    }
}

class Main {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = { { 1, 0 } };
        Solution solution = new Solution();
        boolean res = solution.canFinish(numCourses, prerequisites);
        System.out.println(res);
    }
}
