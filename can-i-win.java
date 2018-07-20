// https://leetcode.com/problems/can-i-win/description/
// DFS + memo
// if you lose, I win.
// o.w. I lose.
// Time: O(n!)->O(2^n) w/ memo, Space: O(2^n)->O(1) w/ bit masking (under 32)
class Solution {
    HashMap<Integer, Boolean> memo = new HashMap<Integer, Boolean>();
    int used = 0;
    public boolean canIWin(int max, int total) {
        if(max*(max+1)/2 < total) return false;
        if(total<=0) return true;
        return dfs(max, total);
    }
    private boolean dfs(int max, int total){
        if(total<=0) return false;
        if(!memo.containsKey(used)){
            for(int i=1; i<=max; i++){
                if(notUsed(i)){
                    setUsedTrue(i);
                    if(!dfs(max, total-i)){
                        setUsedFalse(i);
                        memo.put(used, true);
                        return true;
                    }
                    setUsedFalse(i);
                }
            }
            memo.put(used, false);
        }
        return memo.get(used);
    }
    private boolean notUsed(int i){ return ((1<<i)&used)==0; }
    private void setUsedTrue(int i){ used |= 1<<i; }
    private void setUsedFalse(int i){ used ^= 1<<i; }
}
