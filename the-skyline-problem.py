# https://leetcode.com/problems/the-skyline-problem/description/
# Plane sweeping (left->right)
# 1. Save starting & ending point with its heights
# 2. Travel points left to right, and keep heights adding & removing
# 3. Get the maximum height on each point
# Time: O(nlogn) to sort keys & find the max key
# Space: O(n)
class Solution:
    def getSkyline(self, buildings):
        dic = {}
        for b in buildings:
            if b[0] not in dic: dic[b[0]] = []
            dic[b[0]].append(b[2])
            if b[1] not in dic: dic[b[1]] = []
            dic[b[1]].append(-b[2])
        ans, hDic, lastH = [], {}, 0
        for k in sorted(dic.keys()):
            for v in dic[k]:
                if v>0:
                    if v not in hDic: hDic[v] = 1
                    else: hDic[v] += 1
                else:
                    hDic[-v] -= 1
                    if hDic[-v]==0: hDic.pop(-v)
            currH = max(hDic) if hDic else 0
            if lastH!=currH: ans.append([k, currH])
            lastH = currH
        return ans
