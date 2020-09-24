/*
131. Palindrome Partitioning
파티셔닝이 가능한지 보려면 결국 다 돌아보는 수밖에 없다. recursive Time O(n!)
그래도 Palindrome인지를 미리 다 계산해놓으면 좀 낫다. Time, Space O(n^2)
*/
class Solution {
    List<List<String>> ans;
    String s;
    boolean[][] memo;
    LinkedList<String> list;
    public List<List<String>> partition(String s) {
        this.ans = new ArrayList<>();
        this.s = s;
        this.memo = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            boolean isPalindrome = true;
            for (int j = 0; i-j >= 0 && i+j < s.length(); j++) {
                if (!isPalindrome || s.charAt(i-j) != s.charAt(i+j)) {
                    memo[i-j][i+j] = false;
                    isPalindrome = false;
                } else {
                    memo[i-j][i+j] = true;
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            boolean isPalindrome = true;
            for (int j = 0; i-j >= 0 && i+j+1 < s.length(); j++) {
                if (!isPalindrome || s.charAt(i-j) != s.charAt(i+j+1)) {
                    memo[i-j][i+j+1] = false;
                    isPalindrome = false;
                } else {
                    memo[i-j][i+j+1] = true;
                }
            }
        }
        this.list = new LinkedList<>();
        dfs(0, s.length()-1);
        return ans;
    }
    void dfs(int l, int r) {
        if (l > r) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int m = l; m <= r; m++) {
            if (!memo[l][m]) continue;
            
            list.add(s.substring(l, m+1));
            dfs(m+1, r);
            list.removeLast();
        }
    }
}