// https://leetcode.com/problems/palindromic-substrings/description/
class Solution {
    // Manacher's algorithm = find the longest palindrome on each position
    // 1. add '#'
    // 2. On each position, find & save longest pal (int array)
    // 3. Keep longest radius & center
    // 4. Next turn, if under radius, just get last pal[mirror] (or radius-i)
    // Time: O(n), Space: O(n)
    public int countSubstrings(String s) {
        int l = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<l; i++){
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append('#');
        //System.out.println(sb);
        
        int l2 = sb.length();
        int[] pal = new int[l2];
        int center=0, radius=0;
        for(int i=0; i<l2; i++){
            int mirror = 2*center - i;
            if(i < radius) pal[i] = Math.min(radius-i, pal[mirror]);
            
            while((i-pal[i]-1)>=0 && (i+pal[i]+1)<l2
                  && sb.charAt(i-pal[i]-1)==sb.charAt(i+pal[i]+1))
                pal[i]++;
            
            if(pal[i]+i > radius){
                radius = pal[i]+i;
                center = i;
            }
        }
        //System.out.println(Arrays.toString(pal));
        
        int ans =0;
        for(int p : pal) ans += (p+1)/2;
        return ans;
    }
    // Expand from the center
    // On each position, expand (to left & right) and accumulate
    // + Blank center positions too!
    // Time: O(n^2), Space: O(1)
    public int countSubstrings2(String s) {
        int l = s.length();
        int ans = 0;
        
        // center is 1 character
        for(int i=0; i<l; i++){
            ans++;
            for(int j=1; (i-j)>=0 && (i+j)<l; j++){
                //System.out.println(i+" "+j+" "+s.charAt(i-j)+" "+s.charAt(i+j));
                if(s.charAt(i-j)==s.charAt(i+j)) ans++;
                else break;
            }
        }
        
        // center is blank
        for(int i=1; i<l; i++){
            for(int j=0; (i-1-j)>=0 && (i+j)<l; j++){
                //System.out.println(i+" "+j+" "+s.charAt(i-1-j)+" "+s.charAt(i+j));
                if(s.charAt(i-1-j)==s.charAt(i+j)) ans++;
                else break;
            }
        }
        
        return ans;
    }
    // Brute Force
    // + Using HashMap is even slower!
    // Time: O(n^3), Space: O(1)
    public int countSubstrings3(String s) {
        int l = s.length();
        int ans = 0;
        for(int i=0; i<l; i++){
            for(int j=(i+1); j<=l; j++){
                String ss = s.substring(i,j);
                //System.out.println(i+" "+j+" "+ss);
                if(isPalindrome(ss)) ans++;
            }
        }
        return ans++;
    }
    //HashSet<String> palindromes = new HashSet<String>();
    //HashSet<String> notPalindromes = new HashSet<String>();
    private boolean isPalindrome(String s){
        if(s.length()==1) return true;
        //else if(palindromes.contains(s)) return true;
        //else if(notPalindromes.contains(s)) return false;
        else{
            for(int i=0, j=(s.length()-1); i<j; i++, j--){
                if(s.charAt(i)!=s.charAt(j)){
                    //notPalindromes.add(s);
                    return false;
                }
            }
            //palindromes.add(s);
            return true;
        }
    }
}
