/*
398. Random Pick Index

정렬돼있다면 binary search라도 할텐데, 아니니 문제다.
결국 같은 숫자끼지 먼저 모아놓고, 그 중에 random으로 돌려주면 메모리는 쓰지만 시간은 줄 것이다.
메모리의 크기는 O(n)이어서 결국 입력 array와 같다.
*/

class Solution {
    Map<Integer, List<Integer>> map;
    Random r;

    public Solution(int[] nums) {
        map = new HashMap<>();
        r = new Random();
        
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        
    }
    
    public int pick(int target) {
        List<Integer> list = map.get(target);
        int i = r.nextInt(list.size());
        return list.get(i);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */