// https://leetcode.com/problems/maximal-rectangle/description/
class Solution {
    // Store recent history (of stack)
    // Time: O(l1*l2), Space: O(l2)
    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int l1 = matrix.length;
        if(l1==0) return ans;
        int l2 = matrix[0].length;
        if(l2==0) return ans;
        
        int[] heights = new int[l2+1];
        for(int i=0; i<l1; i++){
            LinkedList<Integer> stack = new LinkedList<Integer>();
            for(int j=0; j<=l2; j++){
                if(j<l2 && matrix[i][j]=='1') heights[j]++;
                else heights[j]=0;
                
                while(!stack.isEmpty() && heights[stack.peek()] > heights[j]){
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? j : j - stack.peek() - 1;
                    ans = Math.max(ans, height*width);
                    //System.out.println(i+","+j+" "+height+" "+width+" "+ans+" "+stack);
                }
                stack.push(j);
            }
        }
        return ans;
    }
    
    // (Time Limit Exceeded)
    // Store recent history (of all possible left-top points) & add selectively
    // Time: O((l1*l2)^2), Space: O((l1*l2)^2)
    public int maximalRectangle2(char[][] matrix) {
        int ans = 0;
        int l1 = matrix.length;
        if(l1==0) return ans;
        int l2 = matrix[0].length;
        if(l2==0) return ans;
        
        Nodes[][] hist = new Nodes[l1][l2];
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                hist[i][j] = new Nodes();
                if(matrix[i][j]=='1'){
                    hist[i][j].set.add(new Node(i,j));
                    if(i>0 && j>0){
                       if(!hist[i-1][j-1].set.isEmpty()
                           && !hist[i-1][j].set.isEmpty()
                           && !hist[i][j-1].set.isEmpty()){
                           int minI = i, minJ = j;
                           for(Node n : hist[i-1][j].set) minI = Math.min(minI, n.i);
                           for(Node n : hist[i][j-1].set) minJ = Math.min(minJ, n.j);
                           for(Node n : hist[i-1][j-1].set) if(n.i>=minI && n.j>=minJ) hist[i][j].set.add(n);
                           for(Node n : hist[i-1][j].set) if(n.j>=minJ) hist[i][j].set.add(n);
                           for(Node n : hist[i][j-1].set) if(n.i>=minI) hist[i][j].set.add(n);
                           /*
                           System.out.println(i+","+j+" minI="+minI+" minJ="+minJ);
                           System.out.println("left-top:"+hist[i-1][j-1].set);
                           System.out.println("top:"+hist[i-1][j].set);
                           System.out.println("left:"+hist[i][j-1].set);
                           System.out.println("me:"+hist[i][j].set);
                           */
                       }else{
                           if(!hist[i-1][j].set.isEmpty()){
                               for(Node n : hist[i-1][j].set) if(n.j==j) hist[i][j].set.add(n);
                           }
                           if(!hist[i][j-1].set.isEmpty()){
                               for(Node n : hist[i][j-1].set) if(n.i==i) hist[i][j].set.add(n);
                           }
                       }
                    }else if(i>0 && !hist[i-1][j].set.isEmpty()) hist[i][j].set.addAll(hist[i-1][j].set);
                    else if(j>0 && !hist[i][j-1].set.isEmpty()) hist[i][j].set.addAll(hist[i][j-1].set);
                
                    for(Node n : hist[i][j].set) ans = Math.max(ans, (i-n.i+1)*(j-n.j+1));
                }
            }
        }
        //for(Nodes[] nn : hist) System.out.println(Arrays.toString(nn));
        return ans;
    }
}
class Nodes{
    HashSet<Node> set = new HashSet<Node>();
    //public String toString(){ return set.toString(); }
}
class Node{
    int i,j;
    Node(int i, int j){ this.i=i; this.j=j; }
    public int hashCode() { return Objects.hash(i,j); }
    //public String toString(){ return "("+i+","+j+")"; }
}
