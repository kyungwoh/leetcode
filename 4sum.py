# https://leetcode.com/problems/4sum/description/
# Use Hash of 2sum
# Time: O(n^2), Space: O(n^2)
class Solution:
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        dic = {}
        nLen = len(nums)
        for i in range(nLen):
            for j in range(i+1, nLen):
                ij = nums[i]+nums[j]
                if not ij in dic: dic[ij] = []
                dic[ij].append([i,j])
        rLists = []
        rTuples = set()
        for k in range(nLen):
            for l in range(k+1, nLen):
                kl = target-nums[k]-nums[l]
                if kl in dic:
                    for ij in dic[kl]:
                        if not k in ij and not l in ij:
                            rList = [nums[ij[0]],nums[ij[1]],nums[k],nums[l]]
                            rList.sort()
                            rTuple = tuple(rList)
                            if not rTuple in rTuples:
                                rTuples.add(rTuple)
                                rLists.append(rList)
        return rLists
