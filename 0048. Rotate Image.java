/*
48. Rotate Image
일단 새로운 배열에 값을 채운다고 생각해보자. 그러면 i = 0 ~ len , j = 0 ~ len으로 돌 수도 있고, 스네이크로 뱅글뱅글 돌 수도 있다. 그랬을 때 (i, j) -> (j, len - i)로 바뀐다. I와 j가 뒤집히는데, 그냥 뒤집히는게 아니고 i가 iEnd - i 로 거울처럼 뒤집힌다. 여기서 좀 더 생각해보면, 이걸 다시 대입하면 (j, len - i) -> (len - i, len - j)로 바뀌고, 이게 또 (len - i, len -j) -> (len - j, len - (len - i) = i) -> (i, len - (len - j) = j) 로 한 바퀴 돌아온다. 그러므로 이것을 swap하면 한 번에 바꿀 수 있다. 여기서 도는 순서를 잘 생각해야 하는게, (i,j) 자리에 무엇이 올지를 생각해야 한다. (i,j) <- (len - j, i)가 오느냐 (j, len - i) 가 오느냐에 따라 시계방향인지 반시계방향인지가 결정된다.
그럼 loop를 어떻게 돌아야 안 겹치게 딱 한번씩만 도느냐가 문젠데, 4개가 스왑하므로 총 개수 len^2 / 4 만큼 돌면 된다. N = 3이면 9/4=2, 4면 16/4=4, 5면 25/4=6개만 돌면 된다. 홀수일때 하나씩 남는 건 가운데라서 안 돌려도 되는데 돌려도 상관 없다. 그럼 간단히 i, j가 n/2까지 돌면 될 것 같은데, 이러면 홀수일때 하나씩 빠지는 경우가 생기니 i<(len+1)/2 해서 하나를 더 돌게 하자. 그러면 적용되는 모양이 골든 하베스트 로고처럼 가운데를 뺀 4개 직사각형이 나올 것이다.
*/
class Solution {
    public void rotate(int[][] matrix) {
        int k = matrix.length - 1;
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < (matrix.length + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[k-j][i];
                matrix[k-j][i] = matrix[k-i][k-j];
                matrix[k-i][k-j] = matrix[j][k-i];
                matrix[j][k-i] = temp;
            }
        }
    }
}