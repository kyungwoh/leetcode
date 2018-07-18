// https://leetcode.com/problems/generate-parentheses/description/
// Backtracking
class Solution {
    List<String> rList = new ArrayList<String>();
    public List<String> generateParenthesis(int n) {
        generateParenthesis(n, 0, 0, "");
        return rList;
    }
    private void generateParenthesis(int n, int open, int close, String s){
        //System.out.println(open+" "+close+" "+s);
        if(open==n && close==n) rList.add(s);
        else{
            if(open<n) generateParenthesis(n, open+1, close, s+"(");
            if(close<open) generateParenthesis(n, open, close+1, s+")");
        }
    }
}
