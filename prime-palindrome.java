// https://leetcode.com/problems/prime-palindrome/description/
// 11 is the only palindrome prime number with EVEN digits. (so, skip possible) (or, check twice (with no substring(1))
// Make the palindrome number first, then check whether it is prime.
// Time: 
class Solution {
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
