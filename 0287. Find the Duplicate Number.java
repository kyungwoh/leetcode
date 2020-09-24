/*
287. Find the Duplicate Number
sort해서 중복을 찾아도 되고, Set으로 중복을 찾아도 되지만, 이건 n까지의 index니까 linked list의 cycle을 찾아도 된다.
cycle detection은 slow / fast runner를 쓰니까 Time O(n), Space O(1)
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}