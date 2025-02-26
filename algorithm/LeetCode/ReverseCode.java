package java_practice.algorithm.LeetCode;

public class ReverseCode {
    class Solution {
        public int reverse(int x) {
            String num = String.valueOf(x);
            String reversed = "";
            int maxLine = 0;

            if(num.startsWith("-")){
                maxLine = 1;
                reversed += "-";
            }

            for (int i = num.length()-1; i >= maxLine; i--) {
                reversed += num.charAt(i);
            }

            try {
                return Integer.parseInt(reversed);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}
