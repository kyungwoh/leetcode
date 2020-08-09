/*
43. Multiply Strings
add하고 carry하면서 순서대로 풀면 된다. 더 나은 방법이 없다.
num1의 각 자리별로 num2의 모든 자리를 곱해서 한 칸씩 전진하며 더해나간다.

문제는 data structure인데, StringBuilder에 쌓을수도 있지만 그러면 더할 때 매번 char -> int 변환해야 한다.
그냥 len(num3) <= len(num1) + len(num2) 니까 넉넉하게 int[len(num1)+len(num2)] 만들어놓고 쓰자.

뒤집어서 계산한 다음에 뒤집어서 return해도 되고,
그냥 뒤에서 앞으로 순서대로 풀어서 그대로 return해도 된다.
*/
class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int[] num3 = new int[len1+len2];
        for (int i = len1 - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int n3 = num3[i+j+1];
                int sum = n1 * n2 + n3;
                num3[i+j+1] = sum%10;
                num3[i+j] += sum/10;
            }
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for(; i < num3.length; i++) if (num3[i] > 0) break;
        for(; i < num3.length; i++) sb.append(num3[i]);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}