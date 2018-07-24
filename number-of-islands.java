// https://leetcode.com/problems/number-of-islands/description/
class Solution {
    // Union Find (using 1D parent array)
    // Count with path compression
    // Time: O(nlogn), Space: O(n)
    public int numIslands(char[][] grid) {
        int l1 = grid.length;
        if(l1==0) return 0;
        int l2 = grid[0].length;
        if(l2==0) return 0;
        
        int cnt = 0;
        int parents[] = new int[l1*l2];
        for(int i=0; i<l1; i++) {
        	for(int j=0; j<l2; j++) {
        		if(grid[i][j]=='1') {
        				parents[i+j*l1] = i+j*l1;
        				cnt++;
        		}
        	}
        }
        //System.out.println(Arrays.toString(parents));
        //System.out.println(cnt);
        for(int i=0; i<l1; i++) {
        	for(int j=0; j<l2; j++) {
        		if(grid[i][j]=='1'){
                    if(i>0 && grid[i-1][j]=='1') cnt -= union(parents, i, j, i-1, j, l1);
                    if(j>0 && grid[i][j-1]=='1') cnt -= union(parents, i, j, i, j-1, l1);
                }
        	}
        }
        //System.out.println(Arrays.toString(parents));
        return cnt;
    }
    private int union(int[] parents, int i, int j, int k, int l, int l1) {
    	int r1 = find(parents, i+j*l1);
    	int r2 = find(parents, k+l*l1);
    	//System.out.println(r1+" "+r2);
        if(r1==r2) return 0;
        else{
            parents[r1] = r2;
            return 1;
        }    	
    }
    private int find(int[] parents, int i) {
        if(parents[i] != i) parents[i] = find(parents, parents[i]);
    	return parents[i];
    }
    
    // Union Find (using 2D parent array)
    // Count by path compression
    // Time: O(nlogn), Space: O(n)
    public int numIslands2(char[][] grid) {
        int l1 = grid.length; if(l1==0) return 0;
		int l2 = grid[0].length; if(l2==0) return 0;
		Node2[][] nodes = new Node2[l1][l2];
		int c = 0;
		for(int i=0; i<l1; i++) {
			for(int j=0; j<l2; j++) {
				if(grid[i][j]=='1') {
					nodes[i][j] = new Node2();
					c++;
				}
			}
		}
		for(int i=0; i<l1; i++) {
			for(int j=0; j<l2; j++) {
				if(grid[i][j]=='1') {
					if(i>0 && grid[i-1][j]=='1') c -= numIslandUnion(nodes[i][j], nodes[i-1][j]);
					if(j>0 && grid[i][j-1]=='1') c -= numIslandUnion(nodes[i][j], nodes[i][j-1]);
				}
			}
		}
		return c;
    }
    
	public int numIslandUnion(Node2 n1, Node2 n2) {
		Node2 p1 = numIslandFind(n1);
		Node2 p2 = numIslandFind(n2);
		if(p1==p2) {
			return 0;
		}
		else if(p1.size >= p2.size) {
			p2.parent = p1;
			p1.size += p2.size;
		}else {
			p1.parent = p2;
			p2.size += p1.size;
		}
        return 1;
	}
	
	public Node2 numIslandFind(Node2 n) {
		if(n.parent!=null) n.parent = numIslandFind(n.parent);
		return n.parent==null ? n : n.parent;
	}
}

class Node2 {
	Node2 parent = null;
	int size = 1;
}
