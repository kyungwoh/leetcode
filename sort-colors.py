# https://leetcode.com/problems/sort-colors/description/
class Solution:
    # Use 3 Pointers (left, mid, right)
    # left, mid: go only right
    # right: go only left
    # Time: O(n), Space: O(1)
    def sortColors(self, nums):
        l, m, r = 0, 0, len(nums)-1
        while l<=m and m<=r:
            while nums[l]==0 and l<len(nums)-1: l += 1
            while nums[r]==2 and r>0: r -= 1
            m = max(m,l)
            #print(0,l,m,r)
            if nums[m]==0 and l<m:
                nums[l], nums[m] = nums[m], nums[l]
                #print(1,l,m,r,nums)
            elif nums[m]==2 and m<r:                
                nums[m], nums[r] = nums[r], nums[m]
                #print(2,l,m,r,nums)
            else:
                m += 1
                #print(3,l,m,r,nums)
    # Counting sort
    # 1st path: count all
    # 2nd path: write down
    # Time: O(n), Space: O(k) k = # of numbers
    def sortColors2(self, nums):
        cnt0, cnt1, cnt2 = 0, 0, 0
        for n in nums:
            if n==0: cnt0 += 1
            if n==1: cnt1 += 1
            if n==2: cnt2 += 1
        
        for i in range(len(nums)):
            if cnt0 > 0:
                nums[i] = 0
                cnt0 -= 1
            elif cnt1 > 0:
                nums[i] = 1
                cnt1 -= 1
            else:
                nums[i] = 2
            
