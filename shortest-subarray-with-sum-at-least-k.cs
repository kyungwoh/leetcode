// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
// Two pointers
// Time: O(n), Space: O(n)
public class Solution {
    public int ShortestSubarray(int[] A, int K) {
        if(A.Length>0){
            long[] P = new long[A.Length+1];
            P[0] = 0;
            for(int i=0; i<A.Length; i++) P[i+1] = P[i] + (long)A[i];
            LinkedList<int> q = new LinkedList<int>();
            int ans = A.Length+1;
            for(int i=0; i<P.Length; i++){
                while(q.Count>0 && P[i]<=P[q.Last.Value]) q.RemoveLast();
                while(q.Count>0 && P[i]-P[q.First.Value]>=K){
                    ans = Math.Min(ans, i - q.First.Value);
                    q.RemoveFirst();
                }
                q.AddLast(i);
            }
            return ans==A.Length+1 ? -1 : ans;
        }else return 0;
    }
}
