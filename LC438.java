import java.util.ArrayList;
import java.util.List;

class HW438 {
    public List<Integer> findAnagrams(String s, String p) {
        int lens = s.length();
        int lenp = p.length();
        List<Integer> list = new ArrayList<>();

        long[] prefixes = new long[lens + 1];
        long prefix = 0;

        for (int i = 1; i <= lens; ++i) {
            prefixes[i] = prefixes[i - 1] + (long) Math.pow(3, (s.charAt(i - 1) - 95));
        }

        for (int i = 0; i < lenp; ++i) {
            prefix += (long) Math.pow(3, (p.charAt(i) - 95));
        }

        for (int i = 0; i <= lens - lenp; ++i) {
            if (prefix == prefixes[i + lenp] - prefixes[i]) {
                list.add(i);
            }
        }

        return list;
    }
}

class Main_HW438 {
    public static void main(String[] args) {
        String s = "abab", p = "ab";
        HW438 solution = new HW438();
        List<Integer> res = solution.findAnagrams(s, p);
        System.out.println(res);
    }
}
