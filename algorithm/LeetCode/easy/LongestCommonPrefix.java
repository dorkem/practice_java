package java_practice.algorithm.LeetCode.easy;

public class LongestCommonPrefix {
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            if (strs[0].isEmpty()) return "";

            StringBuilder prefix = new StringBuilder();
            String firstStr = strs[0];

            for (int i = 0; i < firstStr.length(); i++) {
                char cha = firstStr.charAt(i);

                for (int j = 1; j < strs.length; j++) {
                    if (i >= strs[j].length() || strs[j].charAt(i) != cha) {
                        return prefix.toString();
                    }
                }

                prefix.append(cha);
            }
            return prefix.toString();
        }
    }
}
