/*
18. 4Sum
2Sum, 3Sum, 4Sum ... kSum의 방법이 있다. 2Sum을 재귀적으로 쓰는 것이다.
1. 중복을 건너뛰기 위해 sort한다. O(nlogn)
2. kSum을 만들어서 k-1 sum을 각 칸마다 호출한다. 예를 들어 0이 target이면 0 - nums[i]을 넣는다. O(n)
   결과가 있으면 그 결과들 전체에 nums[i]를 add한다. 그래서 돌려주면 된다.
2-0. (pruning - optional) 맨 처음에 불가능한 경우에 early return한다. 배열의 범위를 벗어나거나, 또는
     가장 작은 값 * k > target인 경우 --> 나머지 값들이 더 크므로 target을 넘어간다.
     가장 큰 값 * k < targer인 경우 --> 나머지 값들이 더 작으므로 target에 못 미친다.
3. k == 2면 평범한 2Sum을 쓴다. sort했으므로 O(n)
Time O(nlogn) + O(n*n*n) = O(n^3)

또는 double 2Sum으로 푼다.
1. (i+j)의 모든 조합을 map에 집어넣는다. (중복 값들은 list로 달아놓는다) O(n^2)
2. (k+l)의 complement가 map에 있을 경우, 현재 값 add해서 answer에 추가한다. (이때 중복되지 않도록 한다) O(n^2)
   문제는 complement의 (i+j) 값의 list가 최악의 경우 O(n) ==> 만약 count만 필요하다면 O(1)
Time O(n^2) + O(n^2 * n) = O(n^3)
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;

        if (k == 2)
            return twoSum(nums, target, start);

        for (int i = start; i < nums.length; i++) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> list: kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(list);
                }
            }
        }
        return res;
    }
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum < target || (lo > start && nums[lo] == nums[lo - 1]))
                ++lo;
            else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
                --hi;
            else
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
        }
        return res;
    }
}

class Solution:
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        dic = {}
        nLen = len(nums)
        for i in range(nLen):
            for j in range(i+1, nLen):
                ij = nums[i]+nums[j]
                if not ij in dic: dic[ij] = []
                dic[ij].append([i,j])
        rLists = []
        rTuples = set()
        for k in range(nLen):
            for l in range(k+1, nLen):
                kl = target-nums[k]-nums[l]
                if kl in dic:
                    for ij in dic[kl]:
                        if not k in ij and not l in ij:
                            rList = [nums[ij[0]],nums[ij[1]],nums[k],nums[l]]
                            rList.sort()
                            rTuple = tuple(rList)
                            if not rTuple in rTuples:
                                rTuples.add(rTuple)
                                rLists.append(rList)
        return rLists