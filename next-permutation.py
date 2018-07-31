# https://leetcode.com/problems/next-permutation/
# 1. Find the first decreasing number (right to left)
# 2. Find just bigger number than 1. (right to left)
# 3. Swap 1. 2.
# 4. Reverse 1.(exclusive) to the end(inclusive)
# Time: O(n), Space: O(1)
class Solution:
    def nextPermutation(self, nums):
        last = -sys.maxsize-1
        firstDec = -1
        for i in range(len(nums)-1, -1, -1):
            if last > nums[i]:
                firstDec = i
                break
            else: last = nums[i]
        
        if firstDec > -1:
            nextBig = len(nums)
            for i in range(len(nums)-1, firstDec, -1):
                if nums[firstDec] < nums[i]:
                    nextBig = i
                    break
            #print(firstDec, nextBig)
            if nextBig!=len(nums):
                nums[firstDec], nums[nextBig] = nums[nextBig], nums[firstDec]
        
        l = firstDec + 1
        r = len(nums) - 1
        while l<r:
            nums[l], nums[r] = nums[r], nums[l]
            l += 1
            r -= 1
