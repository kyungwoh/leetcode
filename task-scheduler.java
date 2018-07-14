// https://leetcode.com/problems/task-scheduler/description/
class Solution {
    // 
    public int leastInterval(char[] tasks, int n) {
        int[] tCnts = new int[26];
        int l = tasks.length;
        for(char t : tasks){
            int i = (int)(t - 'A');
            tCnts[i]++;
        }
        PriorityQueue<Task> pq = new PriorityQueue<Task>();
        for(int i=0; i<26; i++) if(tCnts[i]>0) pq.add(new Task(tCnts[i]));
        int ans = 0;
        while(l>0){
            ArrayList<Task> nextT = new ArrayList<Task>();
            int nRemain = n+1;
            while(!pq.isEmpty() && nRemain>0){
                Task t = pq.poll();
                t.cnt--;                
                if(t.cnt>0) nextT.add(t);
                ans++;
                l--;
                nRemain--;
                //System.out.println(t+" "+nRemain+" "+l+" "+ans);
            }
            if(!nextT.isEmpty()){
                ans += nRemain;
                pq.addAll(nextT);
            }
        }
        return ans;
    }
    public int leastInterval2(char[] tasks, int n) {
        int[] tCnts = new int[26];
        int l = tasks.length;
        for(char t : tasks){
            int i = (int)(t - 'A');
            tCnts[i]++;
        }
        int ans = 0;
        int[] tLasts = new int[26];
        while(l>0){
            int pick = -1, maxCnt = 0;
            for(int i=0; i<26; i++){
                if(tLasts[i]==0 && tCnts[i]>maxCnt){
                    maxCnt = tCnts[i];
                    pick = i;
                }
            }
            //System.out.println(pick);
            //System.out.println(Arrays.toString(tLasts));
            //System.out.println(Arrays.toString(tCnts));
            if(pick > -1){
                tCnts[pick]--;
                tLasts[pick] = n+1;
                l--;
            }
            for(int i=0; i<26; i++) if(tLasts[i]>0) tLasts[i]--;
            ans++;
        }
        return ans;
    }
}
class Task implements Comparable<Task>{
    //int i;
    int cnt;
    //Task(int i, int cnt){ this.i = i; this.cnt = cnt; }
    Task(int cnt){ this.cnt = cnt; }
    public int compareTo(Task o){ return o.cnt - cnt; }
    //public String toString() { return "("+i+","+cnt+")"; }
}
