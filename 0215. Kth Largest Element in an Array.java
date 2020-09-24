/*
215. Kth Largest Element in an Array
PQ에 넣어서 size() 넘을때마다 poll()하면 마지막에 k개만 남는다. time O(nlogk), space O(k).

아니면 quick select로 pivot으로 왼쪽, 오른쪽 나눠서 갯수로 찾아가도 된다.
pivot == k면 pivot이 적중한거고, pivot < k면 오른쪽, >k면 왼쪽으로 가면서 찾는다.
quick sort와 마찬가지로 worst O(n^2) 이지만, 특정 k번째만 찾으므로 avg O(n)까지 떨어진다.
pivot을 시작점, 끝점, 중간점, 랜덤 등으로 다양하게 시도할 수 있다.
*/
class Solution {
    int[] nums;
    int k;
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        this.k = nums.length - k;
        return helper(0, nums.length - 1);
    }
    int helper(int l, int r) {
        if (l == r) return nums[l];
        
        int m = l + (r-l)/2;
        int pivot = nums[m];
        swap(m, r);
        int i, j;
        for(i = l, j = l; j < r; j++) {
            if (nums[j] < pivot) swap(j, i++);
        }
        swap(i, r);
        
        return i == k ? nums[i] : (i > k ? helper(l, i-1) : helper(i+1, r));
    }
    void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
class Solution {
    int[] nums;
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}