// https://leetcode.com/problems/trapping-rain-water/description/
class Solution {
    // Dynamic Programming:
    // 1. Find the water horizon: save the max numbers right->left, left->right, and the min numbers among them
    // 2. Subtract height - water horizon
    // Time: O(n), Space: O(n)
    public int trap(int[] height) {
        int l = height.length; if(l==0) return 0;
        int[] max = new int[l];
        int t = 0;
        for(int i=(l-1); i>=0; i--){
            t = Math.max(t, height[i]);
            max[i] = t;
        }
        t = 0;
        int ans = 0;
        for(int i=0; i<l; i++){
            t = Math.max(t, height[i]);
            max[i] = Math.min(max[i], t);
            ans += max[i] - height[i];
        }
        return ans;
    }
    // Two pointers (left, right)
    // left, leftMax <------------> right, rightMax
    // increase by height & get water horizon & subtract height - water horizon
    // Time: O(n), Space: O(1)
    public int trap2(int[] height) {
        int lMax = 0, rMax = 0, l = 0, r = height.length-1, ans = 0;
        while(l<r){
            //System.out.println(l+" "+r+" "+lMax+" "+rMax+" "+ans);
            if(height[l]>height[r]){
                rMax = Math.max(rMax, height[r]);
                ans += rMax - height[r];
                r--;
            }else{
                lMax = Math.max(lMax, height[l]);
                ans += lMax - height[l];
                l++;
            }
            //System.out.println(" "+l+" "+r+" "+lMax+" "+rMax+" "+ans);
        }
        return ans;
    }
}
