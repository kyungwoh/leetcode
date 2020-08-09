/*
73. Set Matrix Zeroes
어차피 모든 array를 돌아야 하기 때문에 Time O(MN) 보다 빨라질 수 없다.
문제는 같은 Time O(MN) 이라도 for문을 덜 돌고, Space를 덜 쓰는 것이다.

Space O(M+N) 각 i, j 마다 0인 여부를 저장해놓는다.
그냥 배열로 해도 되고, 조금 더 절약하자면 Set으르 쓰면 되는데 최악의 경우는 같다.

Space O(1) in-place로 array에 어차피 0으로 덮어쓸 곳에 i, j가 0인 여부를 저장해놓고 쓴다.
문제는 덮어쓰는 곳이 영향을 받는다는 것이다. 0번째를 덮어쓴다면 0번째만 따로 해야 한다.

1. 0번째만 iZero, jZero로 계산해놓는다. Space O(1)
2. 1번째부터 i, j 돌아서 0번째에 저장해놓고, 다시 1번째부터 i, j 돌아서 0을 쓴다.
3. 아까 저장해놨던 iZero, jZero를 보고 0번째에 0을 쓴다.
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        int iLen = matrix.length;
        if (iLen == 0) return;
        int jLen = matrix[0].length;
        if (jLen == 0) return;
        
        boolean iZero = false;
        for (int j = 0; j < jLen; j++) {
            if (matrix[0][j] == 0) {
                iZero = true;
                break;
            }
        }
        
        boolean jZero = false;
        for (int i = 0; i < iLen; i++) {
            if (matrix[i][0] == 0) {
                jZero = true;
                break;
            }
        }
        
        for (int i = 1; i < iLen; i++) {
            for (int j = 1; j < jLen; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < iLen; i++) {
            for (int j = 1; j < jLen; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (iZero) {
            for (int j = 0; j < jLen; j++) matrix[0][j] = 0;
        }
        if (jZero) {
            for (int i = 0; i < iLen; i++) matrix[i][0] = 0;
        }
    }
}

class Solution {
    public void setZeroes(int[][] matrix) {
        int iLen = matrix.length;
        if (iLen == 0) return;
        int jLen = matrix[0].length;
        if (jLen == 0) return;
        
        boolean iZero = false, jZero = false;
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) iZero = true;
                    if (j == 0) jZero = true;
                    if (i != 0 && j != 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        for (int i = 1; i < iLen; i++) {
            for (int j = 1; j < jLen; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (iZero) {
            for (int j = 0; j < jLen; j++) matrix[0][j] = 0;
        }
        if (jZero) {
            for (int i = 0; i < iLen; i++) matrix[i][0] = 0;
        }
    }
}
class Solution {
    public void setZeroes(int[][] matrix) {
        int iLen = matrix.length;
        if (iLen == 0) return;
        int jLen = matrix[0].length;
        if (jLen == 0) return;
        
        boolean iZero = false;
        for (int i = 0; i < iLen; i++) {
            if (matrix[i][0] == 0) iZero = true;
            
            for (int j = 1; j < jLen; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < iLen; i++) {
            for (int j = 1; j < jLen; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (matrix[0][0] == 0) { // iZero + jZero
            for (int j = 0; j < jLen; j++) {
                matrix[0][j] = 0;
            }
        }
        if (iZero) {
            for (int i = 0; i < iLen; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}