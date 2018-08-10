# https://leetcode.com/problems/first-unique-character-in-a-string/description/
# Two paths
# 1. count
# 2. return when count==1
# Time: O(n), Space: O(n)
class Solution:
    def firstUniqChar(self, s):
        dic = {}
        for c in s:
            if c in dic:
                dic[c] += 1
            else:
                dic[c] = 1        
        i = 0
        for c in s:
            if c in dic and dic[c]==1: return i
            i += 1
        return -1
