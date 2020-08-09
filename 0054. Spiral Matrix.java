/*
54. Spiral Matrix
edge case들이 많아서 한 번에 짜기가 어려우면, 방향을 돌려가면서 계속 앞으로 가 보는 simulation 방법을 쓸 수 있다.
범위를 벗어나거나 visited일때마다 돌리면서 총 노드의 갯수만큼 돌면 된다.

아니면 규칙을 찾아서 풀면 된다.
1. go right
2. go down
3. go left
4. go up
이 4개를 되풀이하면 되니까, 각각의 범위를 정해놓고 (iLeft~iRight, jLeft~jRight) 줄여가면서 돌리면 된다.
여기서 겹치는 노드가 생기면 안되니까 잘 나눠야 한다.
1. left to right
2. left+1 to right
3. right-1 to left+1 (if possible)
4. right to left+1 (if possible)
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int iLen = matrix.length;
        if (iLen == 0) return ans;
        int jLen = matrix[0].length;
        if (jLen == 0) return ans;
        
        boolean[][] visited = new boolean[iLen][jLen];
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int d = 0, i = 0, j = 0;
        for (int k = 0; k < iLen*jLen; k++) {
            visited[i][j] = true;
            ans.add(matrix[i][j]);
            int i2 = i + dir[d][0];
            int j2 = j + dir[d][1];
            if (i2 == -1 || i2 == iLen || j2 == -1 || j2 == jLen || visited[i2][j2]) {
                d = (d+1)%4;
                i2 = i + dir[d][0];
                j2 = j + dir[d][1];
            }
            i = i2;
            j = j2;
        }
        return ans;
    }
}
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int iLen = matrix.length;
        if (iLen == 0) return ans;
        int jLen = matrix[0].length;
        if (jLen == 0) return ans;
        
        int iLeft = 0, iRight = iLen-1, jLeft = 0, jRight = jLen-1;
        while (iLeft <= iRight && jLeft <= jRight) {
            for (int j = jLeft; j <= jRight; j++) ans.add(matrix[iLeft][j]);
            for (int i = iLeft+1; i <= iRight; i++) ans.add(matrix[i][jRight]);
            if (iLeft < iRight) {
                for (int j = jRight-1; j > jLeft; j--) ans.add(matrix[iRight][j]);
            }
            if (jLeft < jRight) {
                for (int i = iRight; i > iLeft; i--) ans.add(matrix[i][jLeft]);
            }
            iLeft++;
            iRight--;
            jLeft++;
            jRight--;
        }
        
        return ans;
    }
}