// https://leetcode.com/problems/palindrome-pairs/description/
// 1. Put words to HashMap (to get index)
// 2. For every word, cut to 2 pieces (s1, s2)
// 2-1. s0(left)-s1(center)-s2(right) case: s1=palindrome & s0=reverse(s2) exists on HashMap
// 2-2. s1(left)-s2(center)-s3(right) case: s2=palindrome & s3=reverse(s1) exists on HashMap
// # of words = n, max(length of words) = k
// Time: O(kn), Space: O(kn)
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> rList = new ArrayList<List<Integer>>();
        int l = words.length;
        if(l==0) return rList;
        
        HashMap<String,Integer> wordMap = new HashMap<String,Integer>();
        for(int i=0; i<l; i++) wordMap.put(words[i], i);
        
        for(int i=0; i<l; i++){
            for(int j=0; j<=words[i].length(); j++){
                String s1 = words[i].substring(0,j);
                String s2 = words[i].substring(j);
                
                // s0(left)-s1(center)-s2(right) case
                if(isPalindrome(s1)){
                    String s0 = reverse(s2);
                    if(wordMap.containsKey(s0)){
                        int k = wordMap.get(s0);
                        if(i!=k) rList.add(Arrays.asList(k,i));
                    }
                }
                // s1(left)-s2(center)-s3(right) case
                if(s2.length()!=0 && isPalindrome(s2)){
                    String s3 = reverse(s1);
                    if(wordMap.containsKey(s3)){
                        int k = wordMap.get(s3);
                        if(i!=k) rList.add(Arrays.asList(i,k));
                    }
                }
            }
        }
        return rList;
    }
    private boolean isPalindrome(String s){
        for(int i=0, j=(s.length()-1); i<j; i++, j--){
            if(s.charAt(i)!=s.charAt(j)) return false;
        }
        return true;
    }
    private String reverse(String s){
        return new StringBuilder(s).reverse().toString();
    }
}
