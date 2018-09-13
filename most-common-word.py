# https://leetcode.com/problems/most-common-word/description/
# Count the words
# Time: O(P+B), Space: O(P+B)
class Solution:
    def mostCommonWord(self, paragraph, banned):
        banSet = set(banned)
        sDic,maxCnt,maxS={},0,""
        for p in paragraph.split():
            s = p.lower().strip("?!'',;.")
            if s not in banSet:
                if s not in sDic: sDic[s]=1
                else: sDic[s]+=1
                if sDic[s]>maxCnt:
                    maxCnt=sDic[s]
                    maxS=s
        return maxS
