// https://leetcode.com/problems/maximum-product-subarray/description/
// product = subProduct[i] / subProduct[j] (except 0)
// Keep positive(+) min subProduct & negative(-) max subProduct
// Time: O(n), Space: O(1)
class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length==1) return nums[0];
        int prod = 1, minPosProd = Integer.MAX_VALUE, maxNegProd = Integer.MIN_VALUE, ans = Integer.MIN_VALUE;
        for(int n : nums){
            if(n==0){
                prod = 1;
                ans = Math.max(ans, 0);
                minPosProd = Integer.MAX_VALUE;
                maxNegProd = Integer.MIN_VALUE;
            }else{
                prod *= n;
                if(prod>0){
                    ans = Math.max(ans, Math.max(prod,prod/minPosProd));
                    minPosProd = Math.min(minPosProd, prod);
                }else{
                    ans = Math.max(ans, Math.max(prod,prod/maxNegProd));
                    maxNegProd = Math.max(maxNegProd, prod);
                }
            }
            //System.out.println(prod+","+ans+","+minPosProd+","+maxNegProd);
        }
        return ans;
    }
}
