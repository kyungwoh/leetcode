// https://leetcode.com/problems/minimum-window-substring/discuss/
class Solution {
    // Using 2 HashMaps
    // Time: O(n), Space: O(n)
    // go one by one of begining and ending
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> sMap = new HashMap<Character, Integer>();
        HashMap<Character, Integer> tMap = new HashMap<Character, Integer>();
        for(char c : t.toCharArray()) add(tMap, c);
        int b = 0, e = 0, length = Integer.MAX_VALUE, maxB = 0, maxE = 0;
        while(b<s.length() && e<=s.length()){
            //System.out.println(b+" "+e+" "+maxB+" "+maxE+" "+sMap.toString()+" "+tMap.toString());
            if(!isSatisfied(sMap, tMap)){
                if(e<s.length()) add(sMap, s.charAt(e));
                e++;
            }else{
                if((e-b) < length){
                    maxB = b;
                    maxE = e;
                    length = (e-b);
                }
                sub(sMap, s.charAt(b));
                if(isSatisfied(sMap, tMap)){
                    b++; 
                }else{
                    add(sMap, s.charAt(b));
                    if(e<s.length()) add(sMap, s.charAt(e));
                    e++;
                }
            }
            //System.out.println(" "+b+" "+e+" "+maxB+" "+maxE+" "+sMap.toString()+" "+tMap.toString());
        }
        return s.substring(maxB,maxE);
    }
    
    // Using 1 HashMap
    // Time: O(n), Space: O(n)
    // go ending first, then go begining
    public String minWindow2(String s, String t) {
        HashMap<Character, Integer> tMap = new HashMap<Character, Integer>();
        for(char c : t.toCharArray()) add(tMap, c);
        int b = 0, e = 0, length = s.length()+1, maxB = 0, maxE = 0, cnt = 0;
        while(e<s.length()){
            //System.out.println(b+" "+e+" "+maxB+" "+maxE+" "+cnt+" "+tMap.toString());
            if(tMap.containsKey(s.charAt(e))){
                sub(tMap, s.charAt(e));
                if(tMap.get(s.charAt(e))>=0) cnt++;
                while(cnt==t.length()){
                    if((e-b+1)<length){
                        maxB = b;
                        maxE = e;
                        length = (e-b+1);
                    }
                    if(tMap.containsKey(s.charAt(b))){
                        add(tMap, s.charAt(b));
                        if(tMap.get(s.charAt(b))>0) cnt--;
                    }
                    b++;
                }                
            }
            e++;
            //System.out.println(" "+b+" "+e+" "+maxB+" "+maxE+" "+cnt+" "+tMap.toString());
        }
        return length == s.length()+1 ? "" : s.substring(maxB,maxE+1);
    }
    
    private void add(Map<Character, Integer> map, char c){ map.put(c, map.getOrDefault(c,0)+1); }
    private void sub(Map<Character, Integer> map, char c){ map.put(c, map.get(c)-1); }
    private boolean isSatisfied(Map<Character, Integer> sMap, Map<Character, Integer> tMap){
        for(Map.Entry<Character, Integer> e : tMap.entrySet()){
            if(sMap.getOrDefault(e.getKey(),0) < e.getValue()) return false;
        }
        return true;
    }
}
