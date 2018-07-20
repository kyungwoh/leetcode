// https://leetcode.com/problems/excel-sheet-column-title/description/
// when mod==0, put 'Z' and n-1
// Time: O(log26 n), Space: O(1)
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        int m = 0;
        while(n>0){
            m = n%26;
            n = n/26;
            n = m==0 ? n-1 : n;
            char c = m==0 ? 'Z' : (char)('A'+m-1);
            //System.out.println(m+" "+n+" "+c);
            sb.insert(0,c);
        }
        return sb.toString();
    }
}
