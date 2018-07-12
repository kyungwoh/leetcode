// https://leetcode.com/problems/largest-rectangle-in-histogram/description/
class Solution {
    // Store recent history lower than me (Using stack)
    // Because lower heights can be larger sometime. so don't calculate now and just store them.
    // But higher or the same heights cannot be larger sometime. so calculate now and forget them.
    // --> How to calculate: (lower than the last one)<--------->(current position)
    // Time: O(n), Space: O(n)
    public int largestRectangleArea(int[] heights) {
        int l = heights.length;
        if(l==0) return 0;
        
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int ans = 0;
        for(int i=0; i<=l; i++){
            int curHeight = i==l ? 0 : heights[i];
            //System.out.println(i+" "+curHeight+" "+stack);
            while(!stack.isEmpty() && heights[stack.peek()] >= curHeight){
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, height*width);
            }
            //System.out.println(" "+ans+" "+stack);
            stack.push(i);
        }
        return ans;
    }
    // Find each position's leftmost & rightmost indices
    // When find, use path compression (to speed up)
    // Time : O(n) <-- path compression works! it doesn't go the same path twice.
    // Space: O(n)
    public int largestRectangleArea2(int[] heights) {
        int l = heights.length;
        if(l==0) return 0;
        
        int left[] = new int[l];
        left[0] = -1;
        for(int i=1; i<l; i++){
            int p = i-1;
            while(p>=0 && heights[p]>=heights[i]) p = left[p];
            left[i] = p;
        }
        //System.out.println(Arrays.toString(left));
        int right[] = new int[l];
        right[l-1] = l;
        for(int i=l-2; i>=0; i--){
            int p = i+1;
            while(p<l && heights[i]<=heights[p]) p = right[p];
            right[i] = p;
        }
        //System.out.println(Arrays.toString(right));
        int ans = 0;
        for(int i=0; i<l; i++){
            ans = Math.max(ans, heights[i]*(right[i]-left[i]-1));
        }
        return ans;
    }
}
