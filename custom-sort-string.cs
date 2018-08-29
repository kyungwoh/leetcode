// https://leetcode.com/problems/custom-sort-string/description/
// Just save sequence & count them
// Time: O(S+T), Space: O(1)
public class Solution {
    public string CustomSortString(string S, string T) {
        int[] seq = new int[26];
        bool[] used = new bool[26];
        int usedCnt = 0;
        for(int i=0; i<S.Length; i++){
            seq[i] = S[i]-97;
            used[seq[i]] = true;
            usedCnt++;
        }
        for(int i=0; i<26; i++){
            if(!used[i]) seq[usedCnt++]=i;
        }
        int[] cnt = new int[26];
        for(int i=0; i<T.Length; i++) cnt[T[i]-97]++;
        
        //for(int i=0; i<26; i++) Console.WriteLine(seq[i]+" "+cnt[i]);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<26; i++){
            for(int j=0; j<cnt[seq[i]]; j++){
                sb.Append((char)('a'+seq[i]));
            }
        }
        return sb.ToString();
    }
}
