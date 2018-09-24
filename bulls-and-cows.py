# https://leetcode.com/problems/bulls-and-cows/description/
# 1. Count bulls
# 2. If not bulls, save to dictionaries -> then count them later
# Time: O(n), Space: O(n)
class Solution:
    def getHint(self, secret, guess):
        sDic,gDic={},{}
        aCnt,bCnt=0,0
        for i in range(len(secret)):
            if secret[i]==guess[i]: aCnt+=1
            else:
                if secret[i] not in sDic: sDic[secret[i]]=0
                sDic[secret[i]]+=1
                if guess[i] not in gDic: gDic[guess[i]]=0
                gDic[guess[i]]+=1
        for k,v in sDic.items():
            if k in gDic: bCnt += min(v, gDic[k])
        return str(aCnt)+'A'+str(bCnt)+'B'
