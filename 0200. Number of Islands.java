/*
200. Number of Islands
섬을 찾았을때 인접한 섬들을 DFS나 BFS로 찾아나가면서 visited로 채워버리는 flood fill을 쓰거나,
각각의 섬을 별도로 만든 후 인접한 섬들을 합치면서 cnt를 줄여가는 union-find를 쓸 수 있다.
어떤 경우던 time, space O(MN) 이다.

Substring finding algorithm

Java function .indexOf()
O(n^2) - i, j loop + early break
KMP, Boyer-moore O(n+m)
KMP pre[0]=0; while j=pre[j-1];
Rabin-Karp (continuous hash) O(n)

배열의 크기는 어떻게 되나? iLen, jLen의 크기는? (만약 integer max까지라면 범위 0<=i<=max 까지 확인해야 함)
iLen*jLen<integer max인가? (아니라면 배열로 풀어야 함)
추가 메모리를 사용할 수 있나? 없다면 in-place 해야하나?
모든 노드를 다 돌아야 하나? 시작점이 주어지나? (섬이니까 당연히 모든 노드를 돌아야 할 것)
그렇다면 모든 노드를 어떻게 순회하나?
1. 특정 점에서 시작한 섬들을 따라가며 찾기. Visited 해놓으면 겹치지 않음)
2. 하나씩 돌면서 인접한 4개와 합치기 (잘 합쳐야 함)
(주의)
2차원 배열을 1차원으로 바꿀 경우, 꼭 범위를 물어볼 것!
모든 변수는 범위를 물어볼 것! 연산 후에도 범위를 벗어나는지 확인할 것!
메모리를 얼마까지 쓸 수 있을지 물어볼 것!
두 가지 푸는 방법을 먼저 생각하고, 어떻게 풀지 물어볼 것! (마음속으로 더 쉽고 자신있는 쪽을 정하고)
특이한 케이스를 먼저 물어볼 것! 최대 예외 케이스를 가지고 생각할 것!
너무 설명을 많이하지 말고, 코드를 만들 것.
코드를 빨리 먼저 적은 다음 천천히 확인할 것!
테스트는 적당히 할 것!
펑션을 만들어 코드를 절약할 것!
(주의2)
많이 쓰는 펑션은 외울것! Union-find
find(i, par) { if (i==-1 || i==par[i]) return i; return par[i] = find(par[i], par); }
union(i, j) { pi=find(i); pj=find(j); if(pi==-1||pj==-1||pi==pj) return 0; if (pi>pj) par[pi]=pj; else par[pj]=pi; return 1; }

binarySearch, mergeSort 등
Array에서 4방향일지 2방향일지? Snake 경우를 고려할 것!
*/
class Solution {
    public int numIslands(char[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j <grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    traverse(i, j, grid);
                }
            }
        }
        return cnt;
    }
    void traverse(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') return;
        grid[i][j] = '2';
        traverse(i+1, j, grid);
        traverse(i, j+1, grid);
        traverse(i-1, j, grid);
        traverse(i, j-1, grid);
    }
}
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int cnt = 0;
        int[] p = new int[grid.length * grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j <grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    int k = index(i, j, grid[i].length);
                    p[k] = k;
                    if (i > 0 && grid[i-1][j] == '1') cnt -= union(p, k, index(i-1,j, grid[i].length));
                    if (j > 0 && grid[i][j-1] == '1') cnt -= union(p, k, index(i, j-1, grid[i].length));
                }
            }
        }
        return cnt;
    }
    int find(int[] p, int i) {
        if (i == p[i]) return i;
        return p[i] = find(p, p[i]);
    }
    int union(int[] p, int i, int j) {
        int pi = find(p, i);
        int pj = find(p, j);
        if (pi == pj) return 0;
        if (pi > pj) p[pi] = pj;
        else p[pj] = pi;
        return 1;
    }
    int index(int i, int j, int jLen) {
        return i*jLen + j;
    }
}