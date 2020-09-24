/*
1304. Find N Unique Integers Sum up to Zero
짝수의 경우는 -1,1, -2,2 ... 홀수는 0을 추가한다.
*/
class Solution {
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        int i = 0, j = 1;
        if (n%2 == 1) arr[i++] = 0;
        while (i < n) {
            arr[i++] = j;
            arr[i++] = -j;
            j++;
        }
        return arr;
    }
}