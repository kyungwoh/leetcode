# https://leetcode.com/problems/partition-labels/description/
# 1. Find starting & ending point per each character
# 2. Merge them
# Time: O(nlogn), Space: O(1) without output
class Solution:
    def partitionLabels(self, S):
        s = [len(S) for x in range(26)]
        e = [-1 for x in range(26)]
        for i in range(len(S)):
            j = ord(S[i])-97
            s[j]=min(s[j],i)
            e[j]=max(e[j],i)
        arr=[]
        for i in range(26):
            if s[i]!=len(S): arr.append((s[i],e[i]))
        arr.sort()
        ans=[]
        curS,curE=0,0
        for a in arr:
            if a[0]>curE:
                ans.append(curE-curS+1)
                curS=a[0]
            curE=max(curE,a[1])
        ans.append(curE-curS+1)
        return ans
