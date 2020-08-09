/*
560. Subarray Sum Equals K

two pointers + sliding window로 될 것 같은데 안 된다. 음수, 0 이 있기 때문이다. 즉 줄어드는지 늘어나는지 모른다.

아니면 psum으로 풀 수 있다. 시작, 끝 점을 하나씩 돌면서 O(n^2)로 풀면 된다.

아니면 Map에다가 sum 값을 담아놓고, 내가 찾는 psum의 나머지 값이 있는지 찾으면 O(n)으로 풀 수 있다.
여기에 one-pass로 풀면 찾은 값이 나보다 작은지 체크하지 않아도 되서(index 불필요) 메모리도 아낄 수 있다.
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length, cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0, sum = 0; i < len; i++) {
            sum += nums[i];
            cnt += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}