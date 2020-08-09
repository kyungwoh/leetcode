/*
167. Two Sum II - Input array is sorted
two pointer로 푼다. sum할때 overflow 고려할지? 못 찾으면 뭘 리턴할지?
*/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int l=0, r=numbers.length-1; l<r;) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) return new int[]{l+1,r+1};
            else if (sum > target) r--;
            else l++;
        }
        return new int[]{};
    }
}