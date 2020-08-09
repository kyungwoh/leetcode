/*
114. Flatten Binary Tree to Linked List
stack. morris traversal

root -> left -> right 순서로 붙이면 된다.
inorder traversal을 하면서 prev.right = root를 해주면 된다.

(주의) 재귀할 때 left, right가 바뀔 수 있으므로,
1. left, right를 저장해놓고
2. left = null, right = null로 끊어놓은 다음에
3. (prev = root로 저장하고) left, right 재귀로 내려간다.
*/

class Solution {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (prev != null) prev.right = root;
        TreeNode left = root.left, right = root.right;
        root.left = null;
        root.right = null;
        prev = root;
        flatten(left);
        flatten(right);
    }
}

class Solution:
    def flatten(self, root):
        if root:
            stack = []
            stack.append(root)
            prev = None
            while stack:
                curr = stack.pop()
                if prev:
                    prev.left = None
                    prev.right = curr
                prev = curr

                if curr.right: stack.append(curr.right)
                if curr.left: stack.append(curr.left)