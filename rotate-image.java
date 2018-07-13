// https://leetcode.com/problems/rotate-image/description/
// Cyclic rotation
// Time: O(n^2), Space: O(1)
class Solution {
    public void rotate(int[][] matrix) {
        int l = matrix.length;
        for(int i=0; i<(l/2); i++){
            int i2 = l-1-i;
            for(int j=i; j<i2; j++){
                int j2 = l-1-j;
                //System.out.print(i+","+j+"="+matrix[i][j]+" ");
                //System.out.print(j+","+i2+"="+matrix[j][i2]+" ");
                //System.out.print(i2+","+j2+"="+matrix[i2][j2]+" ");
                //System.out.println(j2+","+i+"="+matrix[j2][i]);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j2][i];
                matrix[j2][i] = matrix[i2][j2];
                matrix[i2][j2] = matrix[j][i2];
                matrix[j][i2] = temp;
            }
        }
    }
}
