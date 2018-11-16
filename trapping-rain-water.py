# https://leetcode.com/problems/trapping-rain-water/description/
class Solution:
    # two pointers (left, right)
    # Time: O(n), Space: O(1)
    def trap(self, height):
        ans = 0
        l,r = 0,len(height)-1
        maxL,maxR = 0,0
        while l<r:
            if height[l] < height[r]:
                if height[l] >= maxL: maxL = height[l]
                else: ans += maxL - height[l]
                l += 1
            else:
                if height[r] >= maxR: maxR = height[r]
                else: ans += maxR - height[r]
                r -= 1
        return ans
    
    # stack (save decreasing or the same, and pop when increasing)
    # Time: O(n), Space: O(n)
    def trap3(self, height):
        ans = 0
        stk = []
        for i in range(len(height)):
            while stk and height[i]>height[stk[-1]]:
                j = stk.pop()
                if not stk: break
                else: ans += (i-stk[-1]-1)*(min(height[i],height[stk[-1]])-height[j])
            stk.append(i)
        return ans            
    
    # 2-pass: get min(Max from left, Max from right)
    # Time: O(n), Space: O(n)
    def trap2(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        l = len(height)
        waterH = [0 for i in range(l)]
        
        # left -> right
        maxL = 0
        for i in range(l):
            maxL = max(maxL, height[i])
            waterH[i] = maxL
        
        # right -> left
        # and get the ans
        maxR = 0
        ans = 0
        for i in range(l-1,-1,-1):
            maxR = max(maxR, height[i])
            ans += min(maxR, waterH[i]) - height[i]
        
        return ans
