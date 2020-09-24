/*
240. Search a 2D Matrix II
의외로 binary search가 O(logn * logm)이 될 것 같은데 안 된다.
O(n * logm), O(m * logn), O(logn! + logm!) 밖에 안 된다.

차선책으로 한 칸씩 움직이는 O(m+n)는 가능하다.
i를 큰 것부터 작은 것으로 움직이고, j를 작은 것부터 큰 것으로 움직이면 겹치지 않고 모든 경우를 탐색할 수 있다.
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int iLen = matrix.length;
        if (iLen == 0) return false;
        int jLen = matrix[0].length;
        if (jLen == 0) return false;
        
        for (int i = iLen-1, j = 0; i >= 0 && j < jLen;) {
            int curr = matrix[i][j];
            if (curr == target) return true;
            else if (curr > target) i--;
            else j++;
        }
        return false;
    }
}