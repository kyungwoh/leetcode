// https://leetcode.com/problems/prime-palindrome/description/
class Solution {
    // 11 is the only palindrome prime number with EVEN digits. (so, skip possible)
    // Make the palindrome number first, then check whether it is prime.
    // Time: O(root N), Space: O(logN) used by String
    public int primePalindrome(int N) {
        if(N>=8 && N<=11) return 11;
        for(int i=1; i<=100000; i++){
            String s = Integer.toString(i);
            String r = new StringBuilder(s).reverse().toString().substring(1);
            int j = Integer.parseInt(s+r);
            if(j>=N && isPrime(j)) return j;
        }
        return 0;
    }
    // Version 2: check even digits too
    public int primePalindrome2(int N) {
        if(N>=8 && N<=11) return 11;
        int ans = 1000000001;
        //Odd digits
        for(int i=1; i<=100000; i++){
            String s = Integer.toString(i);
            String r = new StringBuilder(s).reverse().toString();
            int j = Integer.parseInt(s+r.substring(1));
            if(j>=N && isPrime(j)){
                ans = j;
                break;
            }
        }
        //Even digits
        if(ans==1000000001){
            for(int i=1; i<=100000; i++){
                String s = Integer.toString(i);
                String r = new StringBuilder(s).reverse().toString();
                try{
                    int j = Integer.parseInt(s+r);
                    if(j>=N && isPrime(j)){
                        ans = j;
                        break;
                    }
                }catch(NumberFormatException e){}
            }
        }
        return ans;
    }
    private boolean isPrime(int n){
        if(n<2 || n%2==0) return n==2;
        for(int i=3; i*i<=n; i+=2){
            if(n%i==0) return false;
        }
        return true;
    }    
/*
    private boolean isPalindrome(int n){
        int m = 0;
        while(n>m){
            m = m*10 + n%10;
            n /= 10;
        }
        return m==n || n==m/10;
    }
*/
}
