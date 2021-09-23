package leetcode;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/anagrams/
 */
public class LC049GroupAnagrams {
    public class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> seen = new HashMap<>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                if (seen.containsKey(key)) {
                    seen.get(key).add(str);
                } else {
                    List<String> group = new ArrayList<>();
                    group.add(str);
                    seen.put(key, group);
                }
            }
            ArrayList<List<String>> result = new ArrayList<>();
            for (List<String> group : seen.values()) {
                Collections.sort(group);
                result.add(group);
            }
            return result;
        }
    }

    private class OrderlyComparator implements Comparator<List<String>> {
        @Override
        public int compare(List<String> l1, List<String> l2) {
            int difference = l1.size() - l2.size();
            if (0 != difference) {
                return difference;
            } else {
                for (int i = 0; i < l1.size(); i++) {
                    difference = l1.get(i).compareTo(l2.get(i));
                    if (0 != difference) {
                        return difference;
                    }
                }
                return 0;
            }
        }
    }

    public void test(String[][] expected, List<List<String>> result) throws Exception {
        Collections.sort(result, new OrderlyComparator());
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j));
            }
        }
    }

    @Test
    public void test_abc_cab_bad_dab_zzz_dot() throws Exception {
        String[] strs = {"abc", "cab", "bad", "dab", "zzz", "dot"};
        String[][] expected = {{"dot"}, {"zzz"}, {"abc", "cab"}, {"bad", "dab"}};
        List<List<String>> result = new Solution().groupAnagrams(strs);
        test(expected, result);
    }

    @Test
    public void test_tea_and_ate_eat_den() throws Exception {
        String[] strs = {"tea", "and", "ate", "eat", "den"};
        String[][] expected = {{"and"}, {"den"}, {"ate", "eat", "tea"}};
        List<List<String>> result = new Solution().groupAnagrams(strs);
        test(expected, result);
    }
}
