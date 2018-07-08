// https://leetcode.com/problems/transpose-matrix/description/
class Solution {
    public int[][] transpose(int[][] A) {
        int l1 = A.length;
        if(l1==0) return null;
        int l2 = A[0].length;
        if(l2==0) return null;
        
        int[][] B = new int[l2][l1];
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                B[j][i] = A[i][j];
            }
        }
        return B;
    }
}
