// https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
class Solution {
    // Divide & Conquer (MergeSort)
    // When sort by ascending order(small-->large), count smaller numers
    // Use index
    // Time: O(nlogn), Space(n)
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> rList = new ArrayList<Integer>();
        int l = nums.length;
        int[] cnt = new int[l];
        int[] idx = new int[l];
        for(int i=0; i<l; i++) idx[i]=i;
        mergeSort(nums, cnt, idx, 0, l-1);
        for(int c : cnt) rList.add(c);
        return rList;
    }
    private void mergeSort(int[] nums, int[] cnt, int[] idx, int s, int e){
        if(s>=e) return;
        int m = (s+e)/2;
        mergeSort(nums, cnt, idx, s, m);
        mergeSort(nums, cnt, idx, m+1, e);
        merge(nums, cnt, idx, s, m, e);
    }
    private void merge(int[] nums, int[] cnt, int[] idx, int s, int m, int e){
        int l = s;
        int r = m+1;
        int rCnt = 0;
        int[] temp = new int[e-s+1];
        int t = 0;
        while(l<=m && r<=e){
            if(nums[idx[l]] > nums[idx[r]]){
                temp[t] = idx[r];
                r++;
                rCnt++;
            }else{
                temp[t] = idx[l];
                cnt[idx[l]] += rCnt;
                l++;
            }
            t++;
        }
        while(l<=m){
            temp[t] = idx[l];
            cnt[idx[l]] += rCnt;
            l++;
            t++;
        }
        while(r<=e){
            temp[t] = idx[r];
            r++;
            t++;
        }
        for(int i=s; i<=e; i++) idx[i] = temp[i-s];
    }
    
    // Binary Search Tree
    // Search right-->left & add a node
    // When building a tree,
    //   1. when go left (smaller), node.sum++;
    //   2. when go right, inherit sum += node.sum + node.cnt
    //   3. when same, 1) cnt++;
                       2) smller # = sum + node.sum (NO node.cnt it's not <=)
    // Without re-balancing tree, Time: O(n^2), Space: O(n)
    // With re-balancing (AVL) tree, Time: O(nlogn), Space: O(n)
    public List<Integer> countSmaller2(int[] nums) {
        int l = nums.length;
        Integer[] r = new Integer[l];
        BSTNode root = null;
        for(int i=(l-1); i>=0; i--) root = insert(nums[i], root, r, i, 0);
        return Arrays.asList(r);
    }
    private BSTNode insert(int num, BSTNode node, Integer[] r, int i, int sum){
        if(node==null){
            node = new BSTNode(num, 0);
            r[i] = sum;
        }else if(node.num == num){
            node.cnt++;
            r[i] = sum + node.sum;
        }else if(node.num > num){
            node.sum++;
            node.left = insert(num, node.left, r, i, sum);
        }else{
            node.right = insert(num, node.right, r, i, sum + node.cnt + node.sum);
        }
        return node;
    }
}

class BSTNode{
    BSTNode left, right;
    int num, sum, cnt;
    BSTNode(int num, int sum){
        this.num = num;
        this.sum = sum;
        this.cnt = 1;
    }
}
