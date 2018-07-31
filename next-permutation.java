// https://leetcode.com/problems/next-permutation/
// 1. Find the first decreasing number (right to left)
// 2. Find just bigger number than 1. (right to left)
// 3. Swap 1. 2.
// 4. Reverse 1.(exclusive) to the end(inclusive)
// Time: O(n), Space: O(1)
class Solution {
    public void nextPermutation(int[] nums) {
        int l = nums.length;
		if(l==0 || l==1) return;
		int last = -1;
        for(int i=(l-1); i>0; i--) {
        	if(nums[i-1]<nums[i]) {
        		last=i-1;
        		break;
        	}
        }
        int nextLast = l;
        if(last!=-1) {
        	for(int i=(l-1); i>last; i--) {
        		if(nums[last]<nums[i]) {
        			nextLast=i;
        			break;
        		}
        	}
        	if(nextLast!=l) {
        		//swap
        		int temp = nums[last];
        		nums[last] = nums[nextLast];
        		nums[nextLast] = temp;
        	}
            System.out.println(last+" "+nextLast);
        }
        //reverse
        for(int i=(l-1),j=(last+1); i>j;i--,j++) {
        	//swap
        	int temp = nums[i];
        	nums[i] = nums[j];
        	nums[j] = temp;
        }
    }
}
