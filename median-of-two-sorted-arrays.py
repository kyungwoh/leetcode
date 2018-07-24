# https://leetcode.com/problems/median-of-two-sorted-arrays/description/
# Binary search on nums1 (nums2 = the rest of nums1)
# Because median = median of (nums1 + nums2)
# <--left nums1--> <--right nums1-->
# <--left nums2--> <--right nums2-->
# max(left2) > min(right1) => too small, go right
# max(left1) > min(right2) => too big, go left
# otherwise, matched!
# Time: O(min(nums1,nums2)), Space: O(1)
class Solution:
    def findMedianSortedArrays(self, nums1, nums2):
        len1, len2 = len(nums1), len(nums2)
        if len1 > len2:
            nums1, nums2, len1, len2 = nums2, nums1, len2, len1
        if len2==0:
            raise ValueError
        
        mLen = math.trunc((len1 + len2 + 1)/2)
        lo1, hi1 = 0, len1
        while lo1 <= hi1:
            m1 = math.trunc((lo1 + hi1)/2)
            m2 = mLen - m1
            if m1<len1 and nums2[m2-1] > nums1[m1]:
                lo1 = m1 + 1
            elif m1>0 and nums1[m1-1] > nums2[m2]:
                hi1 = m1 - 1
            else:
                if m1==0: maxL = nums2[m2-1]
                elif m2==0: maxL = nums1[m1-1]
                else: maxL = max(nums1[m1-1], nums2[m2-1])

                if (len1 + len2)%2==1:
                    return maxL
                else:
                    if m1==len1: minR = nums2[m2]
                    elif m2==len2: minR = nums1[m1]
                    else: minR = min(nums1[m1], nums2[m2])

                    return (maxL + minR)/2
