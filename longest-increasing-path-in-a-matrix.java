// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
class Solution {
    // Brute Force
    // 1. Connect all
    // 2. Count lengths
    // 3. Find the longest one
    // Time: O(n^2), Space: O(n^2)
    public int longestIncreasingPath(int[][] matrix) {
        int l1=matrix.length;
        if(l1==0) return 0;
        int l2=matrix[0].length;
        if(l2==0) return 0;
        
        Node[][] n = new Node[l1][l2];
        for(int i=0; i<l1; i++) {
        	for(int j=0; j<l2; j++) {
        		n[i][j] = new Node(matrix[i][j]);
        	}
        }
        for(int i=0; i<l1; i++) {
        	for(int j=0; j<l2; j++) {
        		if(i>0 && matrix[i][j] < matrix[i-1][j]) n[i][j].add(n[i-1][j]);
        		if(j>0 && matrix[i][j] < matrix[i][j-1]) n[i][j].add(n[i][j-1]);
        		if(i<(l1-1) && matrix[i][j] < matrix[i+1][j]) n[i][j].add(n[i+1][j]);
        		if(j<(l2-1) && matrix[i][j] < matrix[i][j+1]) n[i][j].add(n[i][j+1]);
        	}
        }
        ArrayList<Node> remainingList = new ArrayList<Node>();
        
        int r=0;
        for(int i=0; i<l1; i++) {
        	for(int j=0; j<l2; j++) {
        		if(n[i][j].isUsed) remainingList.add(n[i][j]);
        	}
        }
        if(remainingList.isEmpty()) return 1;
        //System.out.println("before sort: "+remainingList.toString());
        Collections.sort(remainingList);
        //System.out.println("after sort: "+remainingList.toString());
        
        for(Node cur : remainingList) {
        	for(Node next : cur.nextNodes) {
        		next.cost = Math.max(next.cost, cur.cost+1);
        		r = Math.max(r, next.cost);
        	}
        }
        //System.out.println("after for: "+remainingList.toString());
        
        return r;
    }
    
    // 1. Count out degrees
    // 2. Pick zero out degrees (possible in nodes)
    // 3. Subtract out degrees (to in nodes) -> repeat 2.
    // 4. count 2.-3. repeats = longest
    public int longestIncreasingPath2(int[][] matrix) {
    	int l1 = matrix.length; if(l1==0) return 0;
    	int l2 = matrix[0].length; if(l2==0) return 0;
    	int pad[][] = new int[l1+2][l2+2];
    	for(int i=0; i<l1; i++) {
    		for(int j=0; j<l2; j++) {
    			pad[i+1][j+1] = matrix[i][j];
    		}
    	}
    	int dir[][] = new int[][] {{-1,0},{0,-1},{1,0},{0,1}};
    	int out[][] = new int[l1+2][l2+2];
    	for(int i=1; i<=l1; i++) {
    		for(int j=1; j<=l2; j++) {
    			for(int[] d : dir) {
    				if(pad[i][j] < pad[i+d[0]][j+d[1]]) {
    					out[i][j]++;
    				}
    			}
    		}
    	}
    	//for(int[] o : out) System.out.println(Arrays.toString(o));
    	LinkedList<int[]> q = new LinkedList<int[]>();
    	for(int i=1; i<=l1; i++) {
    		for(int j=1; j<=l2; j++) {
    			if(out[i][j]==0) q.add(new int[] {i,j});
    		}
    	}
    	//for(int[] i : q) System.out.println(Arrays.toString(i));
    	int epoch = 0;
    	while(!q.isEmpty()) {
    		epoch++;
    		
    		int qSize = q.size();
    		for(int i=0; i<qSize; i++) {
    			int c[] = q.pollFirst();
                int c0 = c[0], c1 = c[1];
    			for(int[] d : dir) {    				
    				int d0 = c0+d[0], d1 = c1+d[1];
    				if(pad[c0][c1] > pad[d0][d1]) {
    					//System.out.println(epoch+"  "+c0+","+c1+" "+d0+","+d1);
    					out[d0][d1]--;
    					//for(int[] o : out) System.out.println(Arrays.toString(o));
    					if(out[d0][d1]==0) q.addLast(new int[] {d0,d1});
    				}    					
    			}
    		}
    	}
    	
    	return epoch;
    }
}

class Node implements Comparable<Node>{
	boolean isUsed = false;
	int cost = 1;
	int n;
	HashSet<Node> nextNodes = new HashSet<Node>();
	void add(Node n) {
		isUsed = true;
		n.isUsed = true;
		nextNodes.add(n);
	}
	public Node(int n) {
		this.n=n;
	}
	public String toString() {
		if(nextNodes.isEmpty()) return n+"-"+cost;
		else return n+"-"+cost+nextNodes.toString();
	}
	@Override
	public int compareTo(Node o) {
		return n-o.n;
	}
}
