/*
140. Word Break II
Trie를 쓰는 것보다 HashSet에 dictionary를 넣고 찾는게 더 효율적이다. O(1) vs O(max(len))

start to end, bottom-up으로 가거나 end to start, top-down으로 가서 가능한 것만 찾으면 된다. 다 찾으면 느리니까.
만약 가능한 경우가 적다면 top-down이 더 효율적이다.

prev index만 저장해놓고 그걸 가지고 String을 잘라서 만들어나가면 된다. 저장했던 역순으로 읽으면 된다. LinkedList에 넣고 거꾸로 읽고.

모든 combination을 다 찾아야 하기 때문에 time O(2^n, space O(2^n*n) n = len(string)
word dictionary 만드는데 time O(w), space O(w)
answer list 만드는데 time O(n^2), space O(n^2)
다 합치면 time O(2^n + n^2 + w), space O(2^n*n + n^2 + w)
*/
class Solution {
    String s;
    List<String> ans;
    Set<String> words;
    List<Integer>[] dp;
    public List<String> wordBreak(String s, List<String> wordDict) {
        this.s = s;
        ans = new ArrayList<>();
        words = new HashSet<>(wordDict);
        dp = new ArrayList[s.length() + 1];
        for (int i = 0; i <= s.length(); i++) dp[i] = new ArrayList<>();
        dp[0].add(0);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j].isEmpty() && words.contains(s.substring(j, i))) dp[i].add(j);
            }
        }
        helper(s.length(), new LinkedList<>());
        return ans;
    }
    void helper(int i, LinkedList<Integer> list) {
        if (i == 0) {
            StringBuilder sb = new StringBuilder();
            int l = 0;
            for (int r : list) {
                if (sb.length() > 0) sb.append(' ');
                sb.append(s.substring(l, r));
                l = r;
            }
            ans.add(sb.toString());
        } else {
            list.addFirst(i);
            for (int j : dp[i]) helper(j, list);
            list.removeFirst();
        }
    }
}
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Trie head = new Trie();
        for (String w : wordDict) {
            Trie curr = head;
            for (char c : w.toCharArray()) {
                int i = (int)(c - 'a');
                if (curr.next[i] == null) curr.next[i] = new Trie();
                curr = curr.next[i];
            }
            curr.isEnd = true;
        }
        
        List<Integer>[] prev = new ArrayList[s.length()+1];
        prev[0] = new ArrayList<>();
        prev[0].add(-1);
        for (int i = 0; i < s.length(); i++) {
            if (prev[i] == null) continue;
            
            Trie curr = head;
            for (int j = i; j < s.length(); j++) {
                int k = (int)(s.charAt(j) - 'a');
                if (curr.next[k] == null) break;
                
                curr = curr.next[k];
                if (curr.isEnd) {
                    if (prev[j+1] == null) prev[j+1] = new ArrayList<>();
                    prev[j+1].add(i);
                }
            }
        }
        
        List<String> ans = new ArrayList<>();
        helper(s.length(), new LinkedList<Integer>(), ans, s, prev);
        return ans;
    }
    
    private void helper(int i, LinkedList<Integer> list, List<String> ans, String s, List<Integer>[] prev) {
        if (i == -1) {
            StringBuilder sb = new StringBuilder();
            for (int j = list.size() - 1, k = -1; j >= 0; j--) {
                if (k != -1) {
                    sb.append(s.substring(k, list.get(j))).append(' ');
                }
                k = list.get(j);
            }
            ans.add(sb.toString().trim());
            return;
        }
        
        if (prev[i] == null) return;
        
        for (Integer j : prev[i]) {
            list.addLast(i);
            helper(j, list, ans, s, prev);
            list.removeLast();
        }
    }
}
class Trie {
    Trie[] next = new Trie[26];
    boolean isEnd = false;
}