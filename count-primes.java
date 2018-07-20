// https://leetcode.com/problems/count-primes/description/
// Sieve of Eratosthenes
// Time:O(n(log(logn))), Space:O(n)
class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for(int i=2; (i*i)<n; i++){
            if(isPrime[i]){
                for(int j=2; (i*j)<n; j++){
                    isPrime[i*j] = false;
                }
            }
        }
        int ans = 0;
        for(int i=2; i<n; i++){
            if(isPrime[i]) ans++;
        }
        return ans;
    }
}
