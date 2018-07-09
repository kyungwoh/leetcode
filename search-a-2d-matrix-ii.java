// https://leetcode.com/problems/search-a-2d-matrix-ii/description/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length; if(m==0) return false;
        int n = matrix[0].length; if(n==0) return false;
        boolean isVisited[][] = new boolean[m][n];
        ArrayDeque<SearchNode> que = new ArrayDeque<SearchNode>();
        que.offer(new SearchNode(m/2, 0, m-1, n/2, 0, n-1));
        boolean result = false;
        while(!que.isEmpty()){
            SearchNode s = que.poll();
            //System.out.println(s+" "+matrix[s.i][s.j]);
            if(!isVisited[s.i][s.j]){
                isVisited[s.i][s.j] = true;
                if(matrix[s.i][s.j]==target){
                    result = true;
                    break;
                }else if(matrix[s.i][s.j]<target){
                    //System.out.println(matrix[s.i][s.j]+"<"+target);
                    //System.out.println("< "+s.i+" "+s.iMax+" "+s.j+" "+s.jMax);
                    if((s.i+1)<=s.iMax) que.offer(new SearchNode((s.iMax+s.i+1)/2, s.i+1, s.iMax, s.j, s.jMin, s.jMax));
                    if((s.j+1)<=s.jMax) que.offer(new SearchNode(s.i, s.iMin, s.iMax, (s.jMax+s.j+1)/2, s.j+1, s.jMax));
                }else{
                    //System.out.println(matrix[s.i][s.j]+">"+target);
                    //System.out.println("> "+s.iMin+" "+s.i+" "+s.jMin+" "+s.j);
                    if(s.iMin<=(s.i-1)) que.offer(new SearchNode((s.iMin+s.i-1)/2, s.iMin, s.i-1, s.j, s.jMin, s.jMax));
                    if(s.jMin<=(s.j-1)) que.offer(new SearchNode(s.i, s.iMin, s.iMax, (s.jMin+s.j-1)/2, s.jMin, s.j-1));
                }
            }
            
        }
        return result;
    }
}
class SearchNode{
    int i, iMin, iMax, j, jMin, jMax;
    SearchNode(int i, int iMin, int iMax, int j, int jMin, int jMax){
        this.i = i;
        this.iMin = iMin;
        this.iMax = iMax;
        this.j = j;
        this.jMin = jMin;
        this.jMax = jMax;
    }
    //public String toString(){ return "("+i+","+iMin+","+iMax+")("+j+","+jMin+","+jMax+")"; }
}
