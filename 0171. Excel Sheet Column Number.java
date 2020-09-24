/*
171. Excel Sheet Column Number
각 자리마다 26씩 곱해서 더해나간다.
*/
class Solution {
    public int titleToNumber(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            int i = (int)(c - 'A') + 1;
            sum = sum * 26 + i;
        }
        return sum;
    }
}