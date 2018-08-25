// https://leetcode.com/problems/frog-jump/description/
// DP with Set
// Time: O(n^2), Space: O(n^2)
public class Solution {
    public bool CanCross(int[] stones) {
        Dictionary<int,HashSet<int>> dic = new Dictionary<int,HashSet<int>>();
        Add(dic, 0, 0);
        int last = stones[stones.Length-1];
        foreach(int i in stones){
            /*Console.Write(i+",");
            foreach(var d in dic){
                Console.Write(d.Key+":");
                foreach(int v in d.Value) Console.Write(v+" ");
            }
            Console.WriteLine();*/
            if(dic.ContainsKey(i)){
                foreach(int j in dic[i]){
                    if(j-1>0) Add(dic, i+j-1, j-1);
                    if(j>0) Add(dic, i+j, j);
                    if(j+1>0) Add(dic, i+j+1, j+1);
                }
            }
            if(dic.ContainsKey(last)) return true;
        }
        return dic.ContainsKey(last);
    }
    private void Add(Dictionary<int,HashSet<int>> dic, int key, int val){
        if(!dic.ContainsKey(key)) dic[key] = new HashSet<int>();
        dic[key].Add(val);
    }
}
