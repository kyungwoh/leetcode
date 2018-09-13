// https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
// 1. Sort by numbers
// 2. BFS
// Time: O(n^2), Space: O(n)
class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int l1 = forest.size();
        if(l1==0) return 0;
        int l2 = forest.get(0).size();
        if(l2==0) return 0;
        
        TreeMap<Integer,Integer> pMap = new TreeMap<Integer,Integer>();
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                if(forest.get(i).get(j)>1){
                    pMap.put(forest.get(i).get(j), i*l2+j);
                }
            }
        }
        
        int ans=0, pre=0;
        if(forest.get(0).get(0)==0) return -1;
        boolean visit[][] = new boolean[l1][l2];
        LinkedList<Integer> q = new LinkedList<Integer>();
        for(Map.Entry<Integer,Integer> e : pMap.entrySet()){
            int cur = e.getValue();
            int ei=cur/l2, ej=cur%l2;
            if(forest.get(ei).get(ej)==0) return -1;
            q.clear();
            q.add(pre);
            for(boolean[] v : visit) Arrays.fill(v,false);
            int cnt=0,curCnt=-1;
            while(!q.isEmpty()){
                int size=q.size();
                //System.out.println(size);
                for(int k=0; k<size; k++){
                    int p = q.poll();
                    int i=p/l2, j=p%l2;
                    //System.out.println(i+","+j);
                    if(i==ei && j==ej){
                        curCnt=cnt;
                        break;
                    }else{
                        visit[i][j]=true;
                        if(i>0 && forest.get(i-1).get(j)!=0 && !visit[i-1][j]){ q.add((i-1)*l2+j); visit[i-1][j]=true; }
                        if(j>0 && forest.get(i).get(j-1)!=0 && !visit[i][j-1]){ q.add(i*l2+j-1); visit[i][j-1]=true; }
                        if(i<l1-1 && forest.get(i+1).get(j)!=0 && !visit[i+1][j]){ q.add((i+1)*l2+j); visit[i+1][j]=true; }
                        if(j<l2-1 && forest.get(i).get(j+1)!=0 && !visit[i][j+1]){ q.add(i*l2+j+1); visit[i][j+1]=true; }
                    }
                }
                if(curCnt>-1) break;
                cnt++;
            }
            if(curCnt==-1) return -1;
            ans+=curCnt;
            pre=cur;
        }
        
        return ans;
    }
}
