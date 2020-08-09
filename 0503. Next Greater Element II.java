/*
503. Next Greater Element II
순환하는 거니까 2번 도는데 % len을 쓴다. i = 2 *len -1 ...0; ans[i%len]
prev를 찾아보려면 stack을 쓰면 된다. prev를 찾아야하니까 맨 마지막에 항상 push를 한다.
그리고 나보다 큰 걸 찾아야 하니까, while (나보다 작거나 같은 건) pop();
*/
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * len - 1; i >= 0; i--) {
            while (!stack.empty() &&  nums[i % len] >= nums[stack.peek()]) stack.pop();
            ans[i % len] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % len);
        }
        return ans;
    }
}