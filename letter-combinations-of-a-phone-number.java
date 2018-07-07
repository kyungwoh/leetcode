// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
// Accumulate char to the previous list (BFS)
// Time: O(4^n), Space: O(4^n)
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if(digits.length()==0) return list;
        list.add("");
        List<List<Character>> cList = new ArrayList<List<Character>>();
        cList.add(Arrays.asList('a','b','c'));
        cList.add(Arrays.asList('d','e','f'));
        cList.add(Arrays.asList('g','h','i'));
        cList.add(Arrays.asList('j','k','l'));
        cList.add(Arrays.asList('m','n','o'));
        cList.add(Arrays.asList('p','q','r','s'));
        cList.add(Arrays.asList('t','u','v'));
        cList.add(Arrays.asList('w','x','y','z'));
        for(char n : digits.toCharArray()){
            List<String> newlist = new ArrayList<String>();
            int i = Character.getNumericValue(n) - 2;
            List<Character> cur = cList.get(i);
            int l = list.size();
            for(int j=0; j<l; j++){
                String s = list.get(j);
                for(char c : cur){
                    newlist.add(s + c);
                }
            }
            list = newlist;
        }
        return list;
    }
}
