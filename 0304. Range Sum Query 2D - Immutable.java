/*
304. Range Sum Query 2D - Immutable
partial sum을 미리 구해놓으면 O(1)에 구할 수 있다.
다만 2D라서 1D보다 복잡한데, 일단 한줄씩 더한 다음에 윗줄을 더해주면 쉽다.
그냥 더하면 안된다. 순서를 잘 지켜서 더해야 한다.
Time: O(nm) O(1)
Space: O(nm)
*/
class NumMatrix {
    int[][] psum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;
        psum = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                psum[i][j] = matrix[i][j];
                if (j > 0) psum[i][j] += psum[i][j-1];
            }
            if (i > 0) {
                for (int j = 0; j < n; j++) {
                    psum[i][j] += psum[i-1][j];
                }
            }
            
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = psum[row2][col2];
        if (row1 > 0) sum -= psum[row1-1][col2];
        if (col1 > 0) sum -= psum[row2][col1-1];
        if (row1 > 0 && col1 > 0) sum += psum[row1-1][col1-1];
        return sum;
    }
}

class NumMatrix {
    int[][] psum;
    public NumMatrix(int[][] matrix) {
        int iLen = matrix.length;
        int jLen = iLen == 0 ? 0 : matrix[0].length;
        psum = new int[iLen][jLen];
        for(int i = 0; i < iLen; i++) {
            for (int j = 0, jsum = 0; j < jLen; j++) {
                jsum += matrix[i][j];
                psum[i][j] = jsum;
                if (i > 0) psum[i][j] += psum[i-1][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = psum[row2][col2];
        if (row1 >= 1) sum -= psum[row1 - 1][col2];
        if (col1 >= 1) sum -= psum[row2][col1 - 1];
        if (row1 >= 1 && col1 >= 1) sum += psum[row1-1][col1-1];
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */