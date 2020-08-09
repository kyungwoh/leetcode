/*
31. Next Permutation
다행히도 sorted array다. 이걸 이용하면 O(n)으로 가능하다.

brute force로 한다면, 일단 sort를 한 다음 맨 처음부터 하나씩 만들어가면서,
현재 input값과 동일한 것을 찾으면 그 다음 것을 return한다.

아니면 규칙을 찾아볼 수 있다. 맨 끝에서부터 prev <= curr인 지점을 찾아서, 거기서부터 맨 끝까지 bubble swap해주면 된다.
*/
class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) i--;
        if (i >= 0) {
            int j = len - 1;
            while (j > i && nums[i] >= nums[j]) j--;
            swap(nums, i, j);
        }
        for(int l = i+1, r = len - 1; l < r; l++, r--) swap(nums, l, r);
    }
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}