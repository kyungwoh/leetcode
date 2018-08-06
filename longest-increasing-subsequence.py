# https://leetcode.com/problems/longest-increasing-subsequence/description/
class Solution:
    # Build LIS using Binary Search
    # replace the smaller (or the same) number only (doesn't matter to the len(LIS))
    # otherwise, expand LIS
    # Time: O(nlogn), Space: O(n)
    def lengthOfLIS(self, nums):
        if not nums: return 0
        nLen = len(nums)
        seq = [0 for x in range(nLen)]
        seq[0] = nums[0]
        seqLen = 1
        for i in range(1, nLen):
            if nums[i] > seq[seqLen-1]:
                seq[seqLen] = nums[i]
                seqLen += 1
            elif nums[i] < seq[0]:
                seq[0] = nums[i]
            else:
                lo, mid, hi = 0, 0, seqLen-1
                while lo<=hi:
                    mid = (lo+hi)//2
                    if seq[mid]==nums[i]: break
                    elif seq[mid]<nums[i]:
                        if mid<seqLen-1 and seq[mid+1]>nums[i]:
                            mid +=1
                            break
                        else: lo = mid+1
                    else:
                        if mid>0 and seq[mid-1]<nums[i]:
                            break
                        else: hi = mid-1
                seq[mid] = nums[i]
                if mid==seqLen: seqLen += 1
        return seqLen
    
    # DP
    # save len(LIS) on each position
    # dp[i] = max(dp[0]...dp[i-1])+1
    # Time: O(n^2), Space: O(n)
    def lengthOfLIS2(self, nums):
        if not nums: return 0
        nLen = len(nums)
        dp = [1 for x in range(nLen)]
        ans = 1
        for i in range(1,nLen):
            prevMax = 0
            for j in range(i):
                if nums[j]<nums[i]:
                    prevMax = max(prevMax,dp[j])
            dp[i] = prevMax+1
            ans = max(ans, dp[i])
        return ans
