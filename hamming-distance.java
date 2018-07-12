// https://leetcode.com/problems/hamming-distance/description/
class Solution {
    // It's easy using Java API (Integer.bitCount())
    // Or make my own function - Time:O(n), Space:O(1)
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        //return Integer.bitCount(z);
        return bitCount(z);
    }
    private int bitCount(int z){
        int cnt = 0;
        while(z!=0){
            cnt += z%2;
            z /= 2;
        }
        return cnt;
    }
}
