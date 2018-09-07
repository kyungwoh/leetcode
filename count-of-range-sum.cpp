// https://leetcode.com/problems/count-of-range-sum/description/
// Partial Sum + MultiSet + Distance between lower_bound() and upper_bound()
// Time: O(n^2) because std::distance consumes O(n) in the worst case
// Space: O(n)
class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        multiset<long long> pSum;
        int res = 0,  i; 
        long long left, right, sum=0;
        for(i=0,pSum.insert(0); i<nums.size(); ++i)
        {
            sum +=nums[i];
            res += std::distance(pSum.lower_bound(sum-upper), pSum.upper_bound(sum-lower));
            //printf("i=%d dis=%d lower=%p upper=%p begin=%p %ld\n", i, std::distance(pSum.lower_bound(sum-upper), pSum.upper_bound(sum-lower)), pSum.lower_bound(sum-upper), pSum.upper_bound(sum-lower), pSum.begin(), *pSum.begin());
            pSum.insert(sum);
        }
        return res;
    }
};
