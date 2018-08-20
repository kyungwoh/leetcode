// https://leetcode.com/problems/backspace-string-compare/description/
// end -> start
// when meet #, backspace
// Time: O(n), Space: O(1)
public class Solution {
    public bool BackspaceCompare(string S, string T) {
        int i = S.Length-1, ib = 0;
        int j = T.Length-1, jb = 0;
        while(i>=0 || j>=0){
            while(i>=0 && (S[i]=='#' || ib>0)){
                if(S[i]=='#') ib++;
                else if(ib>0) ib--;
                i--;
            }
            
            while(j>=0 && (T[j]=='#' || jb>0)){
                if(T[j]=='#') jb++;
                else if(jb>0) jb--;
                j--;
            }
            
            if(i>=0 && j>=0 && S[i]!=T[j]) return false;
            else{ i--; j--; }
        }
        return i==j ? true : false;
    }
}
