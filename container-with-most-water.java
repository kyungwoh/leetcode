// https://leetcode.com/problems/container-with-most-water/description/
class Solution {
    // Time: O(n), Space: O(1)
    // distance(right-left) is always decreasing, so we only have to find a taller height.
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
    
    // Time: O(nlogn), Space: 0(n)
    // Sort by height first, then decrease height one by one, so we only have to find a longer distance(right-left).
    public int maxArea2(int[] height) {
        int l = height.length;
        if(l==0) return 0;
        Point[] pList = new Point[l];
        for(int i=0; i<l; i++) pList[i] = new Point(height[i], i);
        Arrays.sort(pList);
        //System.out.println(Arrays.toString(pList));
        int left=l, right=-1, maxArea=0;
        for(Point p : pList){
            left = Math.min(left, p.index);
            right = Math.max(right, p.index);
            maxArea = Math.max(maxArea, p.height * (right-left));
        }
        return maxArea;
    }
}

class Point implements Comparable<Point>{
    int height, index;
    Point(int height, int index){
        this.height = height;
        this.index = index;
    }
    public int compareTo(Point o){ return Integer.compare(o.height, height); }
    public String toString(){ return "("+height+","+index+")"; }
}
