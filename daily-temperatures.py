# https://leetcode.com/problems/daily-temperatures/description/
# Use stack
# Time: O(n), Space: O(n)
class Solution:
    def dailyTemperatures(self, temperatures):
        st, ans = [], [0 for x in range(len(temperatures))]
        for i in range(len(temperatures)):
            while st and st[-1][0]<temperatures[i]:
                t, i0 = st.pop()
                ans[i0] = i - i0
            st.append([temperatures[i],i])
        return ans
