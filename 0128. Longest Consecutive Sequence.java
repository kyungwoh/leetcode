/*
128. Longest Consecutive Sequence
일단 Set에 넣어놓고 생각하자. 그래야 Time O(n)이 가능하다.
그 다음엔 Set에서 한 방향으로 쭉 찾아나가면 된다.
그러면 O(n^2)이 될 것 같지만, 반대방향으로 이어지지 않을때만 출발하면 겹치지 않으므로 O(n)이 된다.
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        int maxCnt = 0;
        for (int s : set) {
            if (s > Integer.MIN_VALUE && set.contains(s-1)) continue;
            int cnt = 1, i = s;
            while (i < Integer.MAX_VALUE && set.contains(i+1)) {
                i++;
                cnt++;
            }
            maxCnt = Math.max(maxCnt, cnt);
        }
        return maxCnt;
    }
}
class Solution {
    public int longestConsecutive(int[] nums) {
        List<Integer> cnts = new ArrayList<>();
        List<Integer> minList = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> mins = new HashMap<>();
        Map<Integer, Integer> maxs = new HashMap<>();
        for (int n : nums) {
            if (set.contains(n)) continue;
            set.add(n);
            boolean found = false;
            if (n < Integer.MAX_VALUE && mins.containsKey(n+1)) {
                found = true;
                int i = mins.get(n+1);
                mins.remove(n+1);
                mins.put(n, i);
                cnts.set(i, cnts.get(i) + 1);
                minList.set(i, n);
            }
            if (n > Integer.MIN_VALUE && maxs.containsKey(n-1)) {
                int i = maxs.get(n-1);
                maxs.remove(n-1);
                if (found) {
                    int j = mins.get(n);
                    mins.remove(n);
                    int max = maxList.get(j);
                    maxs.put(max, i);
                    cnts.set(i, cnts.get(i) + cnts.get(j));
                    maxList.set(i, max);
                } else {
                    found = true;
                    maxs.put(n, i);
                    cnts.set(i, cnts.get(i) + 1);
                    maxList.set(i, n);
                }
            }
            if (!found) {
                cnts.add(1);
                minList.add(n);
                maxList.add(n);
                int i = cnts.size() - 1;
                mins.put(n, i);
                maxs.put(n, i);
            }
        }
        int maxCnt = 0;
        for (int c : cnts) maxCnt = Math.max(maxCnt, c);
        return maxCnt;
    }
}