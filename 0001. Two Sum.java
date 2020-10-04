/*
1. Two Sum
Leetcode의 총 1499개 문제 중에서 제 1번 문제다. 구글 인터뷰 예제 영상에도 나온다. 코딩 인터뷰 준비하는 사람들은 무조건 풀어볼 문제다. 그냥 모든 경우를 따져볼수도 있고 O(n^2), 정렬한 다음에 two pointers로 leftmost, rightmost에서부터 가운데로 증가 또는 감소시키면서 가면 O(n), 하지만 정렬이 안되어있다면 정렬하는데 O(nlogn), 그리고 complement (target - n)을 저장해놓고 값으로 찾으면 O(n)  (Inverted index인 셈이다), 그러나 그만큼 공간도 O(n)이 필요하다. Two pass는 일단 다 넣은 다음에 다시 돌아가면서 찾는 건데, 자기 자신을 제외하려면 index까지 넣어야 해서 HashMap이 필요하다. One pass는 일단 찾고 넣는건데, 이러면 자기 앞에 것만 찾으니까 자기 자신은 제외해서 HashSet으로도 가능하다.. 조금 더 나아가  메모리 안에 다 넣을 수 없어서 나눠서 풀어야 한다면, 메모리가 허용하는 한도 내에서 각각 푼 다음에 merge해서 reconcile해야 한다.
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) return new int[]{map.get(nums[i]), i};
            map.put((target - nums[i]), i);
        }
        return null;
    }
}


// https://leetcode.com/problems/two-sum/description/
class Solution {
    // Use Hash
    // Time: O(n), Space: O(n)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> set = new HashMap<Integer,Integer>();
        for(int i=0; i<nums.length; i++){            
            int other = target - nums[i];
            if(set.containsKey(other) && set.get(other)!=i){
                return new int[]{set.get(other), i};
            }
            set.put(nums[i],i);
        }
        return null;
    }
    // Brute force
    // Time: O(n^2), Space: O(1)
    public int[] twoSum2(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        for(int i=0; i<length; i++) {
        	for(int j=i; j<length; j++) {
        		if(i==j) continue;
        		else {
        			if(nums[i]+nums[j]==target) {
        				result[0]=i;
        				result[1]=j;
        				break;
        			}
        		}
        	}
        }
        return result;
    }
    // Sort, then use two pointers(left, right)
    // Time: O(nlogn) ~O(n):quick sort, Space: O(1)
    public int[] twoSum3(int[] nums, int target) {
        int len = nums.length;
        int[] nums2 = Arrays.copyOf(nums, len);
        Arrays.sort(nums2);
        int[] result = new int[2];
        int l = 0, r = len-1;
        while(l<r){
            if((nums2[l]+nums2[r])==target){
                break;
            }else if((nums2[l]+nums2[r]) > target) r--;
            else l++;
        }
        for(int i=0; i<len; i++){
            if(nums[i]==nums2[l]) result[0]=i;
        }
        for(int i=0; i<len; i++){
            if(i!=result[0] && nums[i]==nums2[r]) result[1]=i;
        }
        return result;
    }
}

# https://leetcode.com/problems/two-sum/description/
# Using Dictionary (complement of target)
# Time: O(n), Space: O(n)
class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        numsDic = {}
        for i,n in enumerate(nums):
            if target-n in numsDic:
                return [numsDic[target-n], i]
            else:
                numsDic[n] = i
        
