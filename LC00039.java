import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class LC00039 {
    public HashMap<Integer, List<List<Integer>>> map = new HashMap<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);

        return dfs(candidates, target);
    }

    public List<List<Integer>> dfs(int[] candidates, int total_num) {
        List<List<Integer>> re = new ArrayList<>();
        for (int i = 0; i < candidates.length; ++i) {
            if (total_num < candidates[i]) {
                break;
            }

            int new_total_num = total_num - candidates[i];

            if (new_total_num == 0) {
                List<Integer> meta_list = new ArrayList<>();
                meta_list.add(candidates[i]);
                re.add(meta_list);
                break;
            }

            List<List<Integer>> list;
            if (map.get(new_total_num) != null) {
                list = map.get(new_total_num);
            } else {
                list = dfs(candidates, new_total_num);
            }

            for (List<Integer> l : list) {
                if (l.size() == 0) {
                    continue;
                }

                if (l.get(l.size() - 1) <= candidates[i]) {
                    l.add(candidates[i]);
                    re.add(l);
                }
            }
        }
        if (map.get(total_num) != null) {
            map.put(total_num, re);
        }

        return re;
    }
}

class Main_LC00039 {
    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        LC00039 solution = new LC00039();
        System.out.println(solution.combinationSum(candidates, target));
        System.out.println(solution.map);
    }
}