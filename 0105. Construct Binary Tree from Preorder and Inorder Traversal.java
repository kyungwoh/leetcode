/*
105. Construct Binary Tree from Preorder and Inorder Traversal
pre는 root -> left -> right 이고, in은 left -> root -> right 다.
그런데 트리는 root부터 만들어 내려가니까 pre를 따라간다. 문제는 leaf node를 알 수가 없는 건데, 이걸 in으로 찾는다.
맨 앞의 값을 root라고 하면, in에는 그 root가 중간에 있을 것이다. 그걸 기준으로 left, right를 나눠서 내려보내면 된다.
그러다가 그게 더 이상 내려갈 수 없을때가 leaf node다.

그런데 root가 in에서 어디 있는지 매번 찾으려면 오래 걸리니까, 미리 map에다 넣어놓는다.
그러면 Time O(n), Space O(n)
*/
class Solution {
    int[] pre, in;
    Map<Integer, Integer> map;
    int i;
    public TreeNode buildTree(int[] pre, int[] in) {
        this.pre = pre;
        this.in = in;
        this.map = new HashMap<>();
        this.i = 0;
        for (int i = 0; i < in.length; i++) map.put(in[i], i);
        return traverse(0, in.length - 1);
    }
    TreeNode traverse(int l, int r) {
        if (l > r) return null;
        
        int val = pre[i++];
        int m = map.get(val);
        
        TreeNode root = new TreeNode(val);
        root.left = traverse(l, m-1);
        root.right = traverse(m+1, r);
        return root;
    }
}

# https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
# Time: O(n), Space: O(n^2)
class Solution:
    def buildTree(self, preorder, inorder):
        if inorder:
            curr = TreeNode(preorder.pop(0))
            mid = inorder.index(curr.val)
            curr.left = self.buildTree(preorder, inorder[:mid])
            curr.right = self.buildTree(preorder, inorder[mid+1:])
            return curr
        return None
