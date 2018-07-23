// https://leetcode.com/problems/count-different-palindromic-subsequences/description/
// DP
// Time: O(n^2), Space: O(n^2)
class Solution {
    public int countPalindromicSubsequences(String S) {
        int len = S.length(), ans = 0;
        int[][] dp = new int[len][len];
        for(int k=0; k<len; k++){
            for(int i=0; (i+k)<len; i++){
                if(k==0) dp[i][i] = 1;
                else{
                    if(S.charAt(i)==S.charAt(i+k)){
                        dp[i][i+k] += dp[i+1][i+k-1]*2;
                        
                        int lo = i+1, hi = i+k-1;
                        while(lo<=hi && S.charAt(lo)!=S.charAt(i+k)) lo++;
                        while(lo<=hi && S.charAt(hi)!=S.charAt(i+k)) hi--;
                        
                        if(lo>hi) dp[i][i+k] += 2;
                        else if(lo==hi) dp[i][i+k] += 1;
                        else dp[i][i+k] -= dp[lo+1][hi-1];                        
                    }else{
                        dp[i][i+k] += dp[i][i+k-1] + dp[i+1][i+k] - dp[i+1][i+k-1];
                    }
                    dp[i][i+k] = dp[i][i+k]<0 ? dp[i][i+k]+1000000007 : dp[i][i+k]%1000000007;                    
                }
                //System.out.println(i+" "+(i+k)+" "+dp[i][i+k]);
            }
        }
        return dp[0][len-1];
    }
}
