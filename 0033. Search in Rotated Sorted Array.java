/*
33. Search in Rotated Sorted Array
꺾이는 부분은 어려우므로, 정렬된 부분만 생각한다. 왼쪽이 정렬되었을 경우와 오른쪽이 정렬되었을 경우만 생각한다. 일단 m == target인걸 찾은 다음에, if (l<=m) { l<=target<m이면 왼쪽, 아니면 오른쪽} else {m<target<=r이면 오른쪽, 아니면 왼쪽}
*/
class Solution {
    public int search(int[] nums, int target) {
        for (int l = 0, r = nums.length - 1; l <= r;) {
            int m = l + (r-l)/2;
            if (nums[m] == target) return m;
            
            if (nums[l] <= nums[m]) {
                if (nums[l] <= target && target < nums[m]) r = m-1;
                else l = m+1;
            } else {
                if (nums[m] < target && target <= nums[r]) l = m+1;
                else r = m-1;
            }
        }
        return -1;
    }
}