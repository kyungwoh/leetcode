# https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
class Solution(object):
    # Scan nums2 (with dic of nums1)
    # Good when nums2 is on a disk (sequential read)
    # Time: O(n), Space: O(n)
    def intersect(self, nums1, nums2):
        dic = {}
        for n in nums1:
            if n in dic: dic[n] += 1
            else: dic[n] = 1
        ans = []
        for n in nums2:
            if n in dic:
                ans.append(n)
                dic[n] -= 1
                if dic[n]==0: del dic[n]
        return ans
    
    # Compare with dics
    # Time: O(n), Space: O(n)
    def intersect2(self, nums1, nums2):
        dic1, dic2 = {}, {}
        for n in nums1:
            if n in dic1: dic1[n] += 1
            else: dic1[n] = 1
        for n in nums2:
            if n in dic2: dic2[n] += 1
            else: dic2[n] = 1
        if len(dic1)>len(dic2): dic1, dic2 = dic2, dic1
        ans = []
        for k,v in dic1.items():
            if k in dic2:
                m = min(v, dic2[k])
                for i in range(m): ans.append(k)
        return ans
