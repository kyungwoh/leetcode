# https://leetcode.com/problems/find-k-closest-elements/description/
# Binary Search -> Expand lo & hi
# Time: O(min(logn,k)), Space: O(k)
class Solution:
    def findClosestElements(self, arr, k, x):
        arrLen = len(arr)
        lo, hi = 0, arrLen-1
        while lo<hi:
            mid = (lo+hi)//2
            if arr[mid]==x:
                lo = hi = mid
                break
            elif arr[mid]>x:
                if hi==mid: break
                else: hi = mid
            else:
                if lo==mid: break
                else: lo = mid
        if lo<hi:
            if x-arr[lo] > arr[hi]-x: lo += 1
            else: hi -= 1
        while lo>0:
            if arr[lo]==arr[lo-1]: lo -= 1
            else: break
        while hi<arrLen-1:
            if arr[hi]==arr[hi+1]: hi += 1
            else: break
        
        k -= hi-lo+1
        while k>0:
            if lo>0 and hi<arrLen-1:
                if x-arr[lo-1] > arr[hi+1]-x: hi += 1
                else: lo -= 1
            elif lo>0: lo -= 1
            else: hi += 1
            k -= 1
        
        return arr[lo:hi+1]
