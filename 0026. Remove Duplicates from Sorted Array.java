/*
26. Remove Duplicates from Sorted Array
sorted array이기 때문에 쉽게 풀 수 있다. duplicated number들이 인접해있기 때문이다.
한 칸씩 전진하면서 같은 숫자인지 판단해서, 같으면 넘어가고 다르면 앞에다가 덮어쓴다. 그렇게 끝까지 가면 맨 앞에 unique number들만 있을 것이다.
이때 앞에다가 쓰는 포인터를 따로 두고, 쓸 때마다 하나씩 증가해야 한다. 갯수가 필요하면 이 인덱스를 리턴하면 된다.
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] != nums[i]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}