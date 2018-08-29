// https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
// Sliding window & find max on left->right, right->left, and left+mid+right
// 1. left->right : O(n)
// 2. right->left : O(n)
// 3. leftMax + mid + rightMax : O(n)
// Time: O(n), Space: O(n)
public class Solution {
    public int[] MaxSumOfThreeSubarrays(int[] nums, int k) {
        long[] leftMax = new long[nums.Length];
        int[] leftMaxI = new int[nums.Length];
        long sum = 0, maxSum = 0;
        int cnt = 0, maxI = 0;
        for(int i=0; i<nums.Length; i++){
            sum += (long)nums[i];
            cnt++;
            if(cnt>k) sum -= (long)nums[i-k];
            if(sum>maxSum){
                maxSum = sum;
                maxI = i-k+1;
            }
            leftMax[i] = maxSum;
            leftMaxI[i] = maxI;
            //Console.WriteLine(i+" "+maxSum+" "+maxI);
        }
        long[] rightMax = new long[nums.Length];
        int[] rightMaxI = new int[nums.Length];
        sum = 0; maxSum = 0; cnt = 0; maxI = 0;
        for(int i=nums.Length-1; i>=0; i--){
            sum += (long)nums[i];
            cnt++;
            if(cnt>k) sum -= (long)nums[i+k];
            if(sum>maxSum){
                maxSum = sum;
                maxI = i;
            }
            rightMax[i] = maxSum;
            rightMaxI[i] = maxI;
            //Console.WriteLine(i+" "+maxSum+" "+maxI);
        }
        sum = 0; maxSum = 0; cnt = 0;
        int[] ans = new int[3]{0, k, k*2};
        for(int i=k; i<nums.Length-k; i++){
            sum += (long)nums[i];
            cnt++;
            if(cnt>k) sum -= (long)nums[i-k];
            //Console.WriteLine(i+" "+sum+" "+leftMax[i-k]+" "+rightMax[i+1]+" "+(leftMax[i-k]+sum+rightMax[i+1]));
            if(cnt>=k && leftMax[i-k]+sum+rightMax[i+1]>maxSum){
                maxSum = leftMax[i-k]+sum+rightMax[i+1];
                ans = new int[3]{leftMaxI[i-k], i-k+1, rightMaxI[i+1]};
            }
        }
        return ans;
    }
}
