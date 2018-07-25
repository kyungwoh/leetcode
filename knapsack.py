# https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem/0
# https://www.geeksforgeeks.org/knapsack-problem/
def dfs(n, w, v, wt):
    if n==0 or w==0: return 0
    elif wt[n-1] > w: return dfs(n-1, w, v, wt)
    else: return max(v[n-1] + dfs(n-1, w - wt[n-1], v, wt), dfs(n-1, w, v, wt) )

def dp(n, w, v, wt):
    dp = [[0 for x in range(w+1)] for x in range(n+1)]
    for i in range(n+1):
        for j in range(w+1):
            if i==0 or j==0:
                dp[i][j] = 0
            elif wt[i-1] > j:
                dp[i][j] = dp[i-1][j]
            else:
                dp[i][j] = max(v[i-1] + dp[i-1][j-wt[i-1]], dp[i-1][j])
    return dp[n][w]

t = int(input())
for i in range(t):
    n = int(input())
    w = int(input())
    v = list(map(int, input().split()))
    wt = list(map(int, input().split()))
    
    #print(dfs(n, w, v, wt))
    print(dp(n, w, v, wt))
