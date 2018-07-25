// https://leetcode.com/problems/wildcard-matching/description/
// DFS : compare 1 by 1 (string & pattern)
// 1. When '*': pattern++
// 2. When '?' or matched: string++ & pattern++
// 3. When ends: if string is remaining, try another!
//    otherwise break
// Time: O(3^n), Space: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        return dfs(s,p,0,0)>1;        
    }
    private int dfs(String s, String p, int si, int pi){
        int sl = s.length(), pl = p.length();
        if(si==sl && pi==pl) return 2; //matched
        if(si==sl && p.charAt(pi)!='*') return 0; //unmatched with all s
        if(pi==pl) return 1; //unmatched without all s
        if(p.charAt(pi)=='*'){
            if(pi+1<pl && p.charAt(pi+1)=='*') return dfs(s,p,si,pi+1); //skip **
            for(int i=0; i<=(sl-si); i++){
                int r = dfs(s,p,si+i,pi+1);
                if(r==0 || r==2) return r;
            }
        }
        if(p.charAt(pi)=='?' || s.charAt(si)==p.charAt(pi)) return dfs(s,p,si+1,pi+1);
        return 1;
    }
}
