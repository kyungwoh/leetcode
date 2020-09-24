/*
163. Missing Ranges
하나씩 따라가면서 있는 그대로 풀면 된다. 0,1,2,3,4... 이렇게만 안 풀면 된다. O(n)보다 더 좋을 순 없다.
일단 lower보다는 높아야 하고, 그 다음부터는 앞의 것을 저장해야 되는데 그냥 lower를 덮어썼다.
문제는 lower+1 할때 max_val을 벗어날 수 있는건데, 그냥 매번 확인해서 flag를 세우던가, long으로 한다.
맨 마지막에 upper 처리도 해줘야 한다.
*/
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        boolean hitMax = false;
        for (int n : nums) {
            if (n < lower) continue;
            if (n > lower) {
                String s = Integer.toString(lower);
                if (n-1 > lower) s += "->" + Integer.toString(n-1);
                ans.add(s);
            }
            if (n == Integer.MAX_VALUE) {
                lower = n;
                hitMax = true;
            } else {
                lower = n+1;
            }
        }
        if (!hitMax) {
            if (lower == upper) ans.add("" + upper);
            if (lower < upper) ans.add(lower + "->" + upper);
        }
        return ans;
    }
}