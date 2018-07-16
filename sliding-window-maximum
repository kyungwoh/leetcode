// https://leetcode.com/problems/sliding-window-maximum/description/
class Solution {
    // Use queue
    // Time: O(kn), Space: O(max(k,n-k)) = approx O(n) 
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
        for(int i=0; i<k; i++) deq.offer(nums[i]);
        int l = nums.length;
        if(l==0) return nums;
        int[] ans = new int[l-k+1];
        for(int i=k, j=0; i<=l; i++, j++){
            int max = Integer.MIN_VALUE;
            for(int d : deq) max = Math.max(max, d);
            ans[j] = max;
            if(i<l){
                deq.poll();
                deq.offer(nums[i]);
            }
        }
        return ans;
    }
}
