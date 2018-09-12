# https://leetcode.com/problems/sort-characters-by-frequency/description/
# Using sort
# Time: O(nlogn), Space: O(n)
class Solution:
    def frequencySort(self, s):
        wDic = {}
        for c in s:
            if c not in wDic: wDic[c]=1
            else: wDic[c]+=1
        arr = []
        for k,v in wDic.items(): arr.append((-v,k))
        arr.sort()
        ans = ""
        for a in arr:
            cnt,c = -a[0],a[1]
            for i in range(cnt): ans+=c
        return ans

# Using bucket of counts
# Time: O(n), Space: O(n)
class Solution:
    def frequencySort(self, s):
        cntDic,cntMax = {},0
        for c in s:
            if c not in cntDic: cntDic[c]=1
            else: cntDic[c]+=1
            cntMax=max(cntMax,cntDic[c])
        bkt = [[] for x in range(cntMax+1)]
        for k,v in cntDic.items():
            bkt[v].append(k)
        ans = ""
        for i in range(len(bkt)-1,-1,-1):
            for c in bkt[i]:
                for j in range(i): ans+=c
        return ans
