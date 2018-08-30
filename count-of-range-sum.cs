// https://leetcode.com/problems/count-of-range-sum/description/
// 1. rangeSum
// 2. Sort by value (rangeSum)
// 3. Find lower & upper bound, then get distance
// Time: O(n^2), Space: O(n)
public class Solution {
    public int CountRangeSum(int[] nums, int lower, int upper) {
        if(nums.Length>0){
            Node[] rSum = new Node[nums.Length+1];
            rSum[0] = new Node(0, 0);
            for(int i=0; i<nums.Length; i++) rSum[i+1] = new Node(rSum[i].val + nums[i], i+1);
            //Console.WriteLine(string.Join(",", Array.ConvertAll<Node, String>(rSum, Convert.ToString)));
            Array.Sort(rSum);
            //Console.WriteLine(string.Join(",", Array.ConvertAll<Node, String>(rSum, Convert.ToString)));
            int cnt = 0;
            long prev = long.MinValue;
            for(int i=0; i<=nums.Length; i++){
                int l = Lower(rSum, (long)lower+rSum[i].val);
                int u = Upper(rSum, (long)upper+rSum[i].val);
                //Console.WriteLine("("+((long)lower+rSum[i].val)+","+((long)upper+rSum[i].val)+") ("+l+","+u+")");
                while(l<=u){
                    if(rSum[l].i>rSum[i].i) cnt++;
                    l++;
                }
                //Console.WriteLine("cnt="+cnt);
            }
            return cnt;
        }else return 0;
    }
    private int Lower(Node[] rSum, long val){
        int lo=0, hi=rSum.Length-1;
        if(rSum[lo].val<=val){
            while(lo<=hi){
                int m=(hi-lo)/2+lo;
                //Console.WriteLine("Lower lo="+lo+" m="+m+" hi="+hi);
                if(rSum[m].val==val &&(m==0 || rSum[m-1].val<val)) return m;
                else if(rSum[m].val<val) lo=m+1;
                else hi=m-1;
            }
        }
        return lo;
    }
    private int Upper(Node[] rSum, long val){
        int lo=0, hi=rSum.Length-1;
        if(rSum[hi].val>=val){
            while(lo<=hi){
                int m=(hi-lo)/2+lo;
                //Console.WriteLine("Upper lo="+lo+" m="+m+" hi="+hi);
                if(rSum[m].val==val && (m==rSum.Length-1 || rSum[m+1].val>val)) return m;
                else if(rSum[m].val>val) hi=m-1;
                else lo=m+1;
            }
        }
        return hi;
    }
}
class Node:IComparable<Node>{
    public long val;
    public int i;
    public Node(long val, int i){
        this.val = val;
        this.i = i;
    }
    public int CompareTo(Node o){ return val==o.val ? i.CompareTo(o.i) : val.CompareTo(o.val); }
    //public override string ToString(){ return "("+val+","+i+")"; }
}
