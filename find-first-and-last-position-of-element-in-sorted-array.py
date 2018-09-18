# https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
# Binay search + Find leftmost + rightmost position
# Time: O(logn), Space: O(1)
class Solution:
    def searchRange(self, nums, target):
        l,r=0,len(nums)
        while l<r:
            m=(r-l)//2+l
            if nums[m]>=target: r=m
            else: l=m+1
        if l==len(nums) or nums[l]!=target: return [-1,-1]
        else:
            leftmost=l
            l,r=0,len(nums)
            while l<r:
                m=(r-l)//2+l
                if nums[m]>target: r=m
                else: l=m+1
            return [leftmost,l-1]
