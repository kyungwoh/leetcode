// https://leetcode.com/problems/course-schedule/description/
// Kahn's indegree algorithm
// V = # of courses
// E = # of prerequisites
// Time: O(V+E), Space: O(VE)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<numCourses; i++) out.add(new ArrayList<Integer>());
        for(int[] p : prerequisites){
            in[p[0]]++;
            out.get(p[1]).add(p[0]);
        }
        
        ArrayDeque<Integer> que = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[numCourses];
        while(true){
            for(int i=0; i<numCourses; i++){
                if(!visited[i] && in[i]==0){
                    que.offer(i);
                    visited[i] = true;
                }
            }
            
            int len = que.size();
            if(len==0) break;
            for(int i=0; i<len; i++){
                int j = que.poll();
                ArrayList<Integer> k = out.get(j);
                if(k.isEmpty()) break;
                else{
                    for(int kk : k) in[kk]--;
                }
            }
        }
        for(int i : in) if(i!=0) return false;
        return true;
    }
}
