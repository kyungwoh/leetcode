// https://leetcode.com/problems/4sum-ii/description/
// 4sum = 2sum + 2sum
// Time: O(n^2), Space: O(n^2)
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int r = 0;
        HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
        for(int a : A) {
        	for(int b : B) {
        		int sum = a+b;
        		m.put(sum, m.getOrDefault(sum, 0)+1);
        	}
        }
        for(int c : C) {
        	for(int d : D) {
        		int sum = c+d;
        		r += m.getOrDefault(-sum,0);
        	}
        }
        return r;
    }
}
