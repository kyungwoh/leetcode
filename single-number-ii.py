# https://leetcode.com/problems/single-number-ii/description/
# Bit manipulation
# 1. XOR: A^0=A, A^A=0
# 2. AND NOT: A&~0=A, A&~A=0
# ones=first A, second 0, third 0
# twos=first 0, second A, third 0
# Time: O(n), Space: O(1)
class Solution:
    def singleNumber(self, nums):
        ones,twos=0,0
        for n in nums:
            ones = (ones^n)&~twos
            twos = (twos^n)&~ones
        return ones
