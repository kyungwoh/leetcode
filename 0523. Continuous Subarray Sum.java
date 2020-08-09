/*
523. Continuous Subarray Sum

k로 나눈 값을 찾는 거라, 증가해야 할지 감소해야 할지 모르기 때문에 sliding window를 못 쓴다.
그러면 남은 것은 Map을 쓰는 거다. 찾고자 하는 값을 Key에 넣고 찾으면 된다.
그냥 앞의 값만 찾으면 one-pass로 set이나 array를 써도 되는데, 이게 길이가 2 이상이어야 되서 인덱스를 넣는다.

근데 그러면 맨 처음부터 길이가 2까지인걸 찾을수가 없으니 가상의 -1 인덱스의 값이 0이라고 넣고 시작하자.
*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) return false;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum %= k;
            Integer j = map.get(sum);
            if (j != null && i-j >= 2) return true;
            if (j == null) map.put(sum, i);
        }
        return false;
    }
}