/*
169. Majority Element
간단히는 map에 cnt하면 된다. cnt가 half 이상이면 바로 return한다. Time O(n), Space O(n)

아니면 Boyer moore majority voting algorithm을 쓰면 된다.
일단 현재 값을 candidate로 지정하고, 그와 같은 값이 나올때마다 cnt++, 다를때마다 cnt-- 한다.
그래서 cnt == 0 이 되면 거기서는 현재 선택된 값이 과반수가 아니라는 얘기고, 동시에 다른 숫자도 과반수를 넘는게 없다는 얘기다.
그럼 거기까지로 자르고, 거기서부터 다시 현재 숫자를 candidate로 선택해서 나머지를 똑같이 찾아나간다.
그럼 나머지 부분에서 과반수를 넘기는게 전체에서 과반수를 넘기는 것과 같은가? 그렇다. Time O(n), Space O(1)
*/
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int half = nums.length/2;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            if (map.get(n) > half) return n;
        }
        return -1;
    }
}
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, candidate = 0;
        for (int n : nums) {
            if (cnt == 0) candidate = n;
            cnt += n == candidate ? 1 : -1;
        }
        return candidate;
    }
}