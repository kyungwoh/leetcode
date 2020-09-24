/*
1246. Palindrome Removal
Divide & Conquer - top-down으로 풀어야 한다 (그 얘기는 bottom-up도 가능하다는 얘기다)
DP로 풀려면 먼저 문제를 잘 정의해야 한다.
dp(l,r) = l부터 r까지의 palidrome removal 횟수
          l == r이면 1
          ㅣ > r이면 0
        = min(
               1 + dp(l+1,r),
               arr[l] == arr[i] 인 곳에서 dp(l+1,i-1) + dp(i+1,r)
                                        단, 여기서 l+1 == i 인 경우는 1로 만들어준다.
             )
*/
class Solution {
    int[] arr;
    int[][] memo;
    public int minimumMoves(int[] arr) {
        this.arr = arr;
        int len = arr.length;
        memo = new int[len+1][len+1];
        return helper(0, len-1);
    }
    int helper(int l, int r) {
        if (l == r) return 1;
        if (l > r) return 0;
        if (memo[l][r] > 0) return memo[l][r];
        
        int minLen = helper(l + 1, r) + 1;
        for (int i = l + 1; i <= r; i++) {
            if (arr[l] == arr[i]) {
                int lLen = l + 1 == i ? 1 : helper(l + 1, i - 1);
                int rLen = helper(i + 1, r);
                minLen = Math.min(minLen, lLen + rLen);
            }
        }
        return memo[l][r] = minLen;
    }
}