/*
297. Serialize and Deserialize Binary Tree
가장 간단한 건 .split(",") 으로 나눠서 순서대로 도는 것이다. 그러면서 null이면 바로 "null"을 return하고, 아니면 전진해서 왼쪽과 오른쪽을 붙인다. global var를 이용하면 편리하다. 아니면 char 단위로 sb.append(c); 하다가 ','를 만나면 지금까지 쌓아왔던 sb를 붙이는 것이다.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    int i;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        i = 0;
        return dfs2(strs);
    }
    TreeNode dfs2(String[] strs) {
        if (i == strs.length || strs[i].equals("null")) {
            i++;
            return null;
        }
        TreeNode c = new TreeNode(Integer.parseInt(strs[i++]));
        c.left = dfs2(strs);
        c.right = dfs2(strs);
        return c;
    }
}

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        LinkedList<TreeNode> q = new LinkedList<>();
        if (root != null) q.addLast(root);
        while (q.size() > 0) {
            int qLen = q.size();
            for (int i = 0; i < qLen; i++) {
                TreeNode c = q.removeFirst();
                if (qLen > 1) sb.append(",");
                if (c == null) {
                    sb.append("null");
                } else {
                    sb.append(Integer.toString(c.val));
                    q.addLast(c.left);
                    q.addLast(c.right);
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String str = data.substring(1, data.length()-1);
        if (str.equals("")) return null;
        String[] strs = str.split(",");
        if (strs.length == 0) return null;
        
        TreeNode root = node(strs[0]);
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        for (int i = 1; i < strs.length;) {
            int qLen = q.size();
            for (int j = 0; j < qLen; j++) {
                TreeNode c = q.removeFirst();
                if (c == null) continue;
                TreeNode l = node(strs[i++]);
                TreeNode r = node(strs[i++]);
                c.left = l;
                c.right = r;
                q.addLast(l);
                q.addLast(r);
            }
        }
        return root;
    }
    TreeNode node(String s) {
        return s != null && s.equals("null") ? null : new TreeNode(Integer.parseInt(s));
    }
}

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (sb.length() > 0) sb.append(',');
            if (node == null) sb.append("null");
            else {
                sb.append(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    String[] arr;
    int i;
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        if (arr.length == 0) return null;
        
        Stack<TreeNode> l = new Stack<>(), r = new Stack<>();
        TreeNode root = null;
        for (String s : arr) {
            TreeNode curr = s.equals("null") ? null : new TreeNode(Integer.parseInt(s));
            if (root == null) {
                root = curr;
                l.push(curr);
                continue;
            }
            
            if (l.size() > 0) {
                TreeNode prev = l.pop();
                prev.left = curr;
                if (curr != null) l.push(curr);
                r.push(prev);
            } else if (r.size() > 0) {
                TreeNode prev = r.pop();
                prev.right = curr;
                if (curr != null) l.push(curr);
            }
        }
        return root;
    }
}

public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) return "null";
        return "[val:" + root.val + ",left:" + serialize(root.left) + ",right:" + serialize(root.right) + "]";
    }
    public TreeNode deserialize(String data) {
        Stack<String> strs = new Stack<>();
        Stack<TreeNode> nodes = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (c != '[' && c != ',' && c != ']') {
                sb.append(c);
                continue;
            }
            strs.push(sb.toString());
            sb.setLength(0);
            strs.push(String.valueOf(c));
            if (c != ']') continue;
            TreeNode left = null, right = null;
            int val = 0;
            String s;
            while (!(s = strs.pop()).equals("[")) {
                if (s.startsWith("val:")) val = Integer.parseInt(s.substring(4));
                if (s.equals("left:")) left = nodes.pop();
                if (s.equals("right:")) right = nodes.pop();
            }
            TreeNode node = new TreeNode(val);
            node.left = left;
            node.right = right;
            nodes.push(node);
        }
        return nodes.empty() ? null : nodes.pop();
	}
}

public class Codec {
    public String serialize(TreeNode root) {
        return root == null ? "null" : root.val + ":[" + serialize(root.left) + "," + serialize(root.right) + "]";
    }
    public TreeNode deserialize(String data) {
        int i = data.indexOf(":");
        if (i == -1) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(data.substring(0, i)));
        String[] children = getChildren(data.substring(i+2, data.length()-1));
        root.left = deserialize(children[0]);
        root.right = deserialize(children[1]);
        return root;
	}
    private String[] getChildren(String data) {
        int i = 0;
        for (int open = 0, close = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == '[') open++;
            if (c == ']') close++;
            if (c == ',' && open == close) break;
        }
        return new String[]{data.substring(0, i), data.substring(i+1)};
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));