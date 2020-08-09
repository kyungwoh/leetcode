/*
17. Letter Combinations of a Phone Number
모든 combination을 따져봐야 하므로 sb.append(c); dfs(); sb.setLength(sb.length()-1); 이렇게 들어갔다 나왔다 하다가 맨 끝에서 ans.add(); 해준다. if문을 절약하기 위해 String[] letters = new String[]{"", "", "abc", "def".... 하면 편리하다. time & space O(3^(3인 것들의 갯수)*4^(4인 것의 갯수))
*/
class Solution {
    List<String> ans;
    String digits;
    static String[] letters = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        this.digits = digits;
        dfs(0, new StringBuilder());
        return ans;
    }
    void dfs(int i, StringBuilder sb) {
        if (i == digits.length()) {
            if (sb.length() > 0) ans.add(sb.toString());
            return;
        }
        for (char c : letters[(int)(digits.charAt(i) - '0')].toCharArray()) {
            sb.append(c);
            dfs(i+1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}