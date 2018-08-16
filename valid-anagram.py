# https://leetcode.com/problems/valid-anagram/description/
# Count alphabets & Compare
# Time: O(s+t), Space: O(1)
class Solution:
    def isAnagram(self, s, t):
        cnt = [0 for x in range(26)]
        for c in s:
            i = ord(c)-97
            cnt[i] += 1
        for c in t:
            i = ord(c)-97
            cnt[i] -= 1
            if cnt[i]<0: return False
        for i in cnt:
            if i!=0: return False
        return True
