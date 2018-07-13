// https://leetcode.com/problems/jewels-and-stones/description/
// Time: O(J+S), Space: O(J)
class Solution {
    public int numJewelsInStones(String J, String S) {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(char c : S.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        int ans = 0;
        for(char c : J.toCharArray()){
            ans += map.getOrDefault(c,0);
        }
        return ans;
    }
}
