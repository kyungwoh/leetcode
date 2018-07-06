class Solution {
    public int maxArea(int[] height) {
        int l = height.length;
        if(l==0) return 0;
        int left=0, right=l-1, maxArea=0;
        while(left<right){
            maxArea = Math.max(maxArea, (right-left)*(Math.min(height[left],height[right])));
            if(height[left]>height[right]) right--;
            else left++;
        }
        return maxArea;
    }
}
