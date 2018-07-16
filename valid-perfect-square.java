// https://leetcode.com/problems/valid-perfect-square/description/
class Solution {
    // Time Limit Exceeded
    // Naive approach : just check
    // Time: O(sqrt(n)), Space: O(1)
    public boolean isPerfectSquare4(int num) {
        int i=1;
        for(i=1; (i*i)<num; i++){}
        if((i*i)==num) return true;
        i++;
        if((i*i)==num) return true;
        return false;
    }
    // Time Limit Exceeded
    // Prime Factorization
    // Will have even numbers
    // Time: O(nloglogn), Space: O(1)
    public boolean isPerfectSquare3(int num) {
        int cnt = 0, n = num;
        while(n%2==0){
            cnt++;
            n = n/2;
        }
        if(cnt%2!=0) return false;
        //System.out.println(2+" "+n+" "+cnt);
        for(int i=3; (i*i)<=n; i+=2){
            cnt = 0;
            while(n%i==0){
                cnt++;
                n = n/i;
            }
            //System.out.println(i+" "+n+" "+cnt);
            if(cnt%2!=0) return false;
            if(n==1) break;
        }
        return true;
    }
    // Square numbers will 1+3+5+7..
    // 2^2=1+3, 3^2=1+3+5, 4^2=1+3+5+7 ...
    // But comparing two numbers (num==temp) will have more time
    // Instead, just subtract the number and check num>0 (faster)
    // Time: O(sqrt(n)), Space: O(1)
    public boolean isPerfectSquare2(int num) {
        int i = 1;
        while(num>0){
            num -= i;
            i += 2;
        }
        return num==0;
    }
    // Binary search
    // Time: O(logn), Space: O(1)
    public boolean isPerfectSquare(int num) {
        long l = 1, h = num;
        while(l<=h){
            long m = (l+h)/2;
            //System.out.println(num+" "+l+" "+m+" "+h);
            if(m*m==num) return true;
            else if(m*m > num) h=m-1;
            else l=m+1;
        }
        return false;
    }
}
