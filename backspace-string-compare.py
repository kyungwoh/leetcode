# https://leetcode.com/problems/backspace-string-compare/description/
# Two pointer
# Time: O(S+T), Space: O(1)
class Solution:
    def backspaceCompare(self, S, T):
        i,j,cntS,cntT=len(S)-1,len(T)-1,0,0
        while i>=0 or j>=0:
            while i>=0:
                if S[i]=='#': cntS+=1
                elif cntS>0: cntS-=1
                else: break
                i-=1
            while j>=0:
                if T[j]=='#': cntT+=1
                elif cntT>0: cntT-=1
                else: break
                j-=1
            #print(i,j)
            if i<0 or ==-1 and j==-1: return True
            elif i==-1 or j==-1: return False
            elif S[i]!=T[j]: return False
            else:
                i-=1
                j-=1
        return True if i==-1 and j==-1 else False
            
