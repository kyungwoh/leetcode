# https://leetcode.com/problems/maximum-subarray/

class Solution:    
    # divide and conquer
    # T(n) = 1/2T(n/2) + Theta(1) --> Time: O(nlogn)
    def maxSubArray(self, nums):
        def dfs(nums):
            l = len(nums)
            
            if not nums or l==0: return (-sys.maxsize-1, 0, 0, -sys.maxsize-1)
            elif l==1: return (nums[0], nums[0], nums[0], nums[0])
            else:
                m = l//2
                
                (l_sMax, l_sMaxL, l_sMaxR, l_s) = dfs(nums[:m])
                (r_sMax, r_sMaxL, r_sMaxR, r_s) = dfs(nums[m:])
                
                sMax = max(l_sMax, r_sMax, l_sMaxR+r_sMaxL)
                sMaxL = max(l_sMaxL, l_s+r_sMaxL)
                sMaxR = max(r_sMaxR, l_sMaxR+r_s)
                s = l_s + r_s
                
                return (sMax, sMaxL, sMaxR, s)
            
        (sMax, sMaxL, sMaxR, s) = dfs(nums)
        return sMax
    
    # divide and conquer
    # T(n) = 1/2T(n/2) + Theta(n) --> Time: O(nlogn)
    def maxSubArray4(self, nums):
        def dfs(nums):
            l = len(nums)
            
            if not nums or l==0: return -sys.maxsize-1
            elif l==1: return nums[0]
            else:
                m = l//2
                
                s, sMaxL = 0, -sys.maxsize-1
                for i in range(m-1,-1,-1):
                    s += nums[i]
                    sMaxL = max(sMaxL, s)
                
                s, sMaxR = 0, -sys.maxsize-1
                for i in range(m, l):
                    s += nums[i]
                    sMaxR = max(sMaxR, s)
                    
                return max(dfs(nums[:m]), dfs(nums[m:]), sMaxL+sMaxR)
        
        return dfs(nums)
    
    # Kadane Algorithm
    # Time: O(n), Space: O(1)
    def maxSubArray3(self, nums):
        l = len(nums)
        if l==0: return 0
        else:
            maxS = maxS2 = nums[0]
            for i in range(1,l):
                maxS = max(nums[i], maxS+nums[i])
                maxS2 = max(maxS2, maxS)
            return maxS2

    # Min Sum & Max Sum
    # Time: O(n), Space: O(1)
    def maxSubArray2(self, nums):
        s, maxS, minS = 0, -sys.maxsize-1, 0
        for n in nums:
            s += n
            maxS = max(maxS, s-minS)
            minS = min(minS, s)
        return maxS
