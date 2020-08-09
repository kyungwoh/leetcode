/*
139. Word Break
트라이 초기화하는 방법: 각 단어별로 head부터 차근차근 new 해나가고 (없을때만), 마지막에 isEnd. 값을 저장할 필요 없음. 그 다음에 트라이를 쓸 때도 head부터 해당하는 걸 next로 찾아 내려가는데, isEnd가 여러개일 수 있으므로 멈추면 안됨!
*/
class Solution {
    public boolean wordBreak(String s, List<String> words) {
        Trie head = new Trie();
        for (String w : words) {
            Trie curr = head;
            for (int i = 0; i < w.length(); i++) {
                int j = (int) w.charAt(i);
                if (curr.next[j] == null) curr.next[j] = new Trie();
                curr = curr.next[j];
            }
            curr.isEnd = true;
        }
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            if (dp[i]) traverse(head, dp, i, s);
        }
        return dp[s.length()];
    }
    void traverse(Trie curr, boolean[] dp, int i, String s) {
        if (curr == null) return;
        if (curr.isEnd) dp[i] = true;
        if (i == s.length()) return;
        int j = (int) s.charAt(i);
        traverse(curr.next[j], dp, i+1, s);
    }
}
class Trie {
    Trie[] next;
    boolean isEnd;
    Trie() {
        next = new Trie[128];
        isEnd = false;
    }
}