# https://leetcode.com/problems/reverse-bits/description/
# put last bit & shift a bit
# Time: O(32), Space(1)
class Solution:
    # @param n, an integer
    # @return an integer
    def reverseBits(self, n):
        ans = 0
        for i in range(32):
            ans <<= 1
            ans += n & 1
            n >>= 1            
        return ans
