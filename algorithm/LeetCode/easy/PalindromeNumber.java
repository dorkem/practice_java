package java_practice.algorithm.LeetCode.easy;

public class PalindromeNumber {
    class Solution {
        public static boolean isPalindrome(int x) {
            String num = String.valueOf(x);

            for (int i = 0; i < num.length()-1; i++){
                if(num.charAt(i) != num.charAt(num.length()-i-1)){
                    return false;
                }
            }
            return true;
        }
    }
}
