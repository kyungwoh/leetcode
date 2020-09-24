/*
41. First Missing Positive
생각해보면 len(arr) = n 일때, 가능한 숫자는 1...n 밖에 없다.
그러므로 arr을 돌면서 1...n이 있는지 체크를 해놨다가, 다시 1...n을 돌면서 체크가 안되어있는 걸 리턴하면 된다. 모두 체크되어 있다면 n+1.
이러면 Time O(n)에 풀 수 있다.

그런데 여기서 Space O(n) 이고, arr도 n이니까, 그냥 in-place로 표시해도 된다.
어차피 양수만 세기로 했으므로, 음수도 모두 아무 양수로나 만든 다음에, 해당 위치를 음수로 만드는 것으로 표시한다.
음수던가 n보다 크던가 해서 범위를 벗어나는 것은 양수 중에 1...n에 해당하지 않는 아무 숫자(여기서는 n+1)로 표시하고, 그건 제외해서 처리한다.
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 1 || nums[i] > n) nums[i] = n+1;
        }
        for (int i = 0; i < n; i++) {
            int j = nums[i] > 0 ? nums[i] : -nums[i];
            if (j == n+1) continue;
            if (nums[j-1] > 0) nums[j-1] = -nums[j-1];
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i+1;
        }
        return n+1;
    }
}