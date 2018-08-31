# https://leetcode.com/problems/minimum-size-subarray-sum/description/
class Solution:
    # Partial Sum + Binary Search
    # pSum[j]-pSum[i]=sum
    # Time: O(nlogn), Space: O(n)
    def minSubArrayLen(self, s, nums):
        if len(nums)==0: return 0
        pSum = []
        pSum.append(0)
        for i in range(len(nums)): pSum.append(pSum[i]+nums[i])
        ans = sys.maxsize
        for i in range(len(pSum)):
            j = bisect.bisect_left(pSum, s+pSum[i])
            if j==len(pSum): break
            if j>i: ans = min(ans, j-i)
        return ans if ans!=sys.maxsize else 0
    
    # Sliding Window
    # 1. Expand right (till sum)
    # 2. Shrink left (till sum)
    # 1-2 repeat
    # Time: O(n), Space: O(1)
    def minSubArrayLen2(self, s, nums):
        l,r,su,ans = 0,0,0,sys.maxsize
        while r<len(nums):
            while True:
                su += nums[r]
                r += 1
                if r==len(nums) or su>=s: break
            while l<r and su-nums[l]>=s:
                su -= nums[l]
                l += 1
            if su>=s: ans = min(ans, r-l)
        return ans if ans!=sys.maxsize else 0
