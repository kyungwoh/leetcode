/*
403. Frog Jump

(0, 0) 부터 가능한 모든 길을 dfs()로 풀면 된다. 다만 이러면 3갈래이므로 O(3^n)이 되니까,
memo를 해서 O(n^3) 으로 줄인다. 이것은 memo = O(n^2) * for(j < stones.length) O(n) 이다.
여기서 stones의 diff 찾는 걸 미리 set에 넣어놓으면 O(n^2)로 더 줄일 수 있다.
top-down이 가능하니 bottom-up으로 올라갈수도 있다. 이러면 set을 map으로 바꿔서 step들을 넣어놓는다. 겹칠 수 있으니 set으로.
*/

class Solution {
    int[] stones;
    int[][] memo;
    public boolean canCross(int[] stones) {
        this.stones = stones;
        this.memo = new int[stones.length][stones.length];
        return dfs(0, 0) == 2;
    }
    private int dfs(int i, int k){
        if (memo[i][k] != 0) return memo[i][k];
        for (int j = i + 1; j < stones.length; j++) {
            int diff = stones[j] - stones[i];
            if (diff == k-1 || diff == k || diff == k+1) {
                if (dfs(j, diff) == 2) {
                    memo[j][diff] = 2;
                    return 2;
                }
            }
        }
        memo[i][k] = i == stones.length - 1 ? 2 : 1;
        return memo[i][k];
    }
}

class Solution {
    int[] stones;
    int[][] memo;
    Map<Integer, Integer> map;
    public boolean canCross(int[] stones) {
        this.stones = stones;
        this.memo = new int[stones.length][stones.length];
        this.map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) map.put(stones[i], i);
        return dfs(0, 0) == 2;
    }
    private int dfs(int i, int k){
        if (memo[i][k] != 0) return memo[i][k];
        for (int step = k - 1; step <= k + 1; step++) {
            int j = stones[i] + step;
            if (step > 0 && map.containsKey(j)) {
                if (dfs(map.get(j), step) == 2) {
                    memo[map.get(j)][step] = 2;
                    return 2;
                }
            }
        }
        memo[i][k] = i == stones.length - 1 ? 2 : 1;
        return memo[i][k];
    }
}

class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int s : stones) map.put(s, new HashSet<>());
        map.get(0).add(0);
        for (int s : stones) {
            for (int k : map.get(s)) {
                for (int step = k-1; step <= k+1; step++) {
                    if (step > 0 && map.containsKey(s + step)) {
                        map.get(s + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}