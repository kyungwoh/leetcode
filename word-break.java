// https://leetcode.com/problems/word-break/description/
class Solution {
    // DP : Using List.contains()
    // string length = l, # of words = n
    // Time: O(l^2 m), Space: O(l)
    public boolean wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        boolean[] dp = new boolean[l+1];
        dp[0] = true;
        HashSet<String> wordSet = new HashSet<String>(wordDict);
        for(int i=1; i<=l; i++){
            for(int j=i-1; j>=0; j--){
                if(dp[j] && wordSet.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[l];
    }
    
    // DP : Using HashSet.contains()
    // Time: O(max(l^2,lm)), Space: O(lm)
    public boolean wordBreak2(String s, List<String> wordDict) {
        int l = s.length();
        boolean[] dp = new boolean[l+1];
        dp[0] = true;
        for(int i=1; i<=l; i++){
            for(int j=i-1; j>=0; j--){
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[l];
    }
    
    // Use Trie (assume lowercase characters = 26)
    // Search string with Trie, and find meeting Trie's end
    // Time: O(26^l), Space: O(26^l)
    public boolean wordBreak3(String s, List<String> wordDict) {
        Trie root = new Trie();
        for(String w : wordDict){
            Trie curr = root, prev = null;
            for(char c : w.toCharArray()){
                int i = c - 'a';
                curr = curr.getNext(i);
            }
            curr.isEnd = true;
        }
        HashSet<Trie> set = new HashSet<Trie>();
        set.add(root);
        for(char c : s.toCharArray()) {
        	int i = c - 'a';
            //System.out.println(i+" "+set);
        	HashSet<Trie> newset = new HashSet<Trie>();
        	for(Trie t : set) {
                if(t.isEnd) if(root.next[i]!=null) newset.add(root.next[i]);
        		if(t.next[i]!=null) newset.add(t.next[i]);
        	}
            //System.out.println(i+"  "+newset);
        	if(newset.isEmpty()) return false;
        	else set = newset;
        }
        //System.out.println(set);
        for(Trie t : set) if(t.isEnd) return true;
        return false;
    }
}
class Trie{
    boolean isEnd = false;
    Trie[] next = new Trie[26];
    Trie getNext(int i){
        if(next[i]==null) next[i] = new Trie();
        return next[i];
    }
    /*public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(isEnd+"(");
        for(int i=0; i<next.length; i++){
            if(next[i]!=null) sb.append(i+",");
        }
        sb.append(")");
        return sb.toString();
    }*/
}
