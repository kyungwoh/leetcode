# https://leetcode.com/problems/add-binary/description/
# Reverse & just carry on
# Time: O(n), Space: O(n)
class Solution:
    def addBinary(self, a, b):
        ar = a[::-1]
        br = b[::-1]
        cr, i, c = "", 0, 0
        while i<len(ar) or i<len(br):
            aa = 0 if i>=len(ar) else int(ar[i])
            bb = 0 if i>=len(br) else int(br[i])
            c += aa + bb
            cr += str(c%2)
            c //= 2
            i += 1
        while c>0:
            cr += str(c%2)
            c //= 2
        return cr[::-1]
                
