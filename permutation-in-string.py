# https://leetcode.com/problems/permutation-in-string/description/
# Sliding window + HashMap(max length = 26)
# Time: O(len(s1)+len(s2)), Space: O(1)
class Solution:
    def checkInclusion(self, s1, s2):
        dic1, dic2 = {}, {}
        for c in s1:
            if c in dic1: dic1[c] += 1
            else: dic1[c] = 1

        i, j = 0, -len(s1)
        while i<len(s2):
            c = s2[i]
            dic2[c] = dic2[c]+1 if c in dic2 else 1

            if j>=0:
                c = s2[j]
                dic2[c] -= 1
                if dic2[c]==0: del dic2[c]

            #print(i,j,dic1,dic2)
            if dic1==dic2: return True
            
            i += 1
            j += 1

        return True if dic1==dic2 else False
