/*
308. Range Sum Query 2D - Mutable
update()와 sumRegion()이 1:1로 일어난다면, 둘 다 합쳤을때의 시간복잡도를 최소화해야 한다.
1. update()를 constructor와 마찬가지로 O(nm)이 걸리게 하고, sumRegion()을 O(1)으로 하는 대신에,
2. update() O(m), sumRegion() O(n) 으로 하는 편이 더 빠르다.
*/
class NumMatrix {
    int n, m;
    int[][] rsum;
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;
        if (n == 0) return;
        m = matrix[0].length;
        if (m == 0) return;
        rsum = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rsum[i][j] = j == 0 ? matrix[i][j] : rsum[i][j-1] + matrix[i][j];
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int i = row;
        matrix[i][col] = val;
        for (int j = col; j < m; j++) {
            rsum[i][j] = j == 0 ? matrix[i][j] : rsum[i][j-1] + matrix[i][j];
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += col1 == 0 ? rsum[i][col2] : rsum[i][col2] - rsum[i][col1 - 1];
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */