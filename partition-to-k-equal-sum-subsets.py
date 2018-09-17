# https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
# Backtracking + pruning
# Time: O(k^n), Space: O(n)
class Solution:
    def canPartitionKSubsets(self, nums, k):
        def backTrack(nums,subs):
            if not nums: return True
            n = nums.pop()
            for i in range(len(subs)):
                if subs[i]>=n:
                    subs[i]-=n
                    if backTrack(nums,subs): return True
                    subs[i]+=n
            nums.append(n)
            return False
        if sum(nums)%k!=0: return False     
        nums.sort()        
        ksum = sum(nums)//k
        subs = [ksum for x in range(k)]
        while nums:
            if nums[-1]>ksum: return False
            elif nums[-1]==ksum:
                nums.pop()
                subs.pop()
            else: break
        return backTrack(nums,subs)
