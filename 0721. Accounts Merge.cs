/*
721. Accounts Merge
*/
// https://leetcode.com/problems/accounts-merge/description/
// n = length of all email addresses
// Time: O(nlogn) because of Union Find & List<>.Sort()
// Space: O(n)
public class Solution {
    private int findParent(int i, int[] accP){
        if(accP[i]!=i) accP[i] = findParent(accP[i], accP);
        return accP[i];
    }
    private void union(int i, int j, int[] accP, int[] accCnt){
        int iP = findParent(i, accP);
        int jP = findParent(j, accP);
        if(iP!=jP){
            if(accCnt[iP]>=accCnt[jP]){
                accP[jP] = iP;
                accCnt[iP] += accCnt[jP];
                accCnt[jP] = 0;
            }else{
                accP[iP] = jP;
                accCnt[jP] += accCnt[iP];
                accCnt[iP] = 0;
            }
        }
    }
    public IList<IList<string>> AccountsMerge(IList<IList<string>> accounts) {
        Dictionary<string,int> dic = new Dictionary<string,int>();
        string[] acc = new string[accounts.Count];
        int[] accCnt = new int[accounts.Count];
        int[] accP = new int[accounts.Count];
        int mergeCnt = 0;
        for(int i=0; i<accounts.Count; i++){
            acc[i] = accounts[i][0];
            accCnt[i] = accounts[i].Count-1;
            accP[i] = i;
            int accI = i;
            for(int j=1; j<accounts[i].Count; j++){
                if(dic.ContainsKey(accounts[i][j])){
                    union(dic[accounts[i][j]], accI, accP, accCnt);
                }else dic.Add(accounts[i][j], accI);
            }
        }
        int[] accMap = new int[acc.Length];
        int mapCnt = 0;
        IList<IList<string>> ansList = new List<IList<string>>();
        for(int i=0; i<accCnt.Length; i++){
            if(accCnt[i]>0){
                accMap[i] = mapCnt;
                ansList.Add(new List<string>());
                ansList[mapCnt].Add(acc[i]);
                mapCnt++;
            }
        }
        foreach(var kv in dic) ansList[accMap[findParent(kv.Value,accP)]].Add(kv.Key);
        for(int i=0; i<ansList.Count; i++){
            List<string> list = (List<string>)ansList[i];
            list.Sort(1, ansList[i].Count-1, StringComparer.Ordinal);
            ansList[i] = list;
        }        
        return ansList;
    }
}
