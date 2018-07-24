// https://leetcode.com/problems/find-median-from-data-stream/description/
class MedianFinder {
  // Using 2 heaps
  // Time: add O(logn), find O(1), Space: add O(n)
  PriorityQueue<Integer> lowerPQ = new PriorityQueue<Integer>((a,b)->b-a);
	PriorityQueue<Integer> upperPQ = new PriorityQueue<Integer>((a,b)->a-b);
    
    public void addNum(int num) {
    	//System.out.println(lowerPQ.toString()+','+upperPQ.toString());
    	lowerPQ.add(num);
    	if(lowerPQ.size() > upperPQ.size()+1) upperPQ.add(lowerPQ.poll());
    }
    
    public double findMedian() {
    	//System.out.println(lowerPQ.toString()+','+upperPQ.toString());
    	if(lowerPQ.size()==upperPQ.size()) return (double)(lowerPQ.peek()+upperPQ.peek())/2;
    	else return (double)lowerPQ.peek();
    }
  
  // Using AVL Tree
   // Time: add O(logn), find O(1), Space: add O(n) 
	TreeNode root, mid;
	int n = 0;
    
    public void addNum2(int num) {
    	n++;
    	if(n==1) {
    		root = new TreeNode(num, null);
    		mid = root;
    	}else {
    		root.add(num);
    		if(n%2==1) {
    			if(mid.val<=num) mid=mid.next();
    		}
    		else {
    			if(mid.val>num) mid=mid.prev();
    		}
    	}
    }
    
    public double findMedian() {
    	if(n%2==1) return mid.val;
    	else return (mid.val + mid.next().val)/2.0;
    }
}

class TreeNode {
    int val;
    TreeNode parent, left, right;
    TreeNode(int val, TreeNode parent) {
    	this.val = val;
    	this.parent = parent;
    }
    void add(int num) {
    	if(num>=val) {
    		if(right==null) right = new TreeNode(num, this);
    		else right.add(num);
    	}else {
    		if(left==null) left = new TreeNode(num, this);
    		else left.add(num);
    	}
    }
    TreeNode next() {
    	TreeNode r;
    	if(right!=null) {
    		r=right;
    		while(r.left!=null) r=r.left;
    	}else {
    		r=this;
    		while(r.parent.right==r) r=r.parent;
    		r=r.parent;
    	}
    	return r;
    }
    TreeNode prev() {
    	TreeNode r;
    	if(left!=null) {
    		r=left;
    		while(r.right!=null) r=r.right;
    	}else {
    		r=this;
    		while(r.parent.left==r) r=r.parent;
    		r=r.parent;
    	}
    	return r;
    }
}
