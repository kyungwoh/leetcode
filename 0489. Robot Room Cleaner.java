/*
489. Robot Room Cleaner
Flood fill로 4방향으로 갈 수 있는 모든 경우를 다 탐색해본다. (backtrack, dfs)
가보는 건 문제가 아닌데, 돌아오는게 문제다. 탐색만 하고 끝나는게 아니라, 로봇 청소기도 원래 위치로 돌아와야 한다.
생각해보면 right, right, move, right, right 하면 돌아올 수 있다.

turnRight 하면서 그 순서대로 dir {-1,0},{0,1},{1,0},{0,-1} 도는 것으로 했는데, 반대 순서로 left로 해도 된다.
dir 배열에 0,1,2,3을 North, East, South, East로 했는데, 이게 0부터 시작하는게 아니라 1,2,3부터 시작하면 4를 넘어가므로 %4를 해준다.

가본데는 visited로 표시해야 한다. 근데 좌표가 안 주어져서 때문에 array로 안되고, Pair에 (i,j)를 넣어서 Set으로 했다.
처음에 (0,0)부터 시작하는데, minus로 갈 수도 있으니 조심해야 한다.
범위가 제한된다면 2D -> 1D로 숫자를 바꿔도 될 것이다. 적당히 1000 정도로 잡아서 범위를 안 벗어났다.
*/

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

 class Solution {
    Set<Integer> visited;
    int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    Robot robot;
    public void cleanRoom(Robot robot) {
        this.visited = new HashSet<>();
        this.robot = robot;
        dfs(0, 0, 0);
    }
    void dfs(int i, int j, int d) {
        robot.clean();
        for (int k = 0; k < 4; k++) {
            int d2 = (d+k)%4;
            int i2 = i+dir[d2][0];
            int j2 = j+dir[d2][1];
            int idx2 = idx(i2, j2);
            if (!visited.contains(idx2) && robot.move()) {
                visited.add(idx2);
                dfs(i2, j2, d2);
                
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
    }
    int idx(int i, int j) { return i*1000 + j; }
}

class Solution {
    Set<Pair> visited;
    int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    Robot robot;
    public void cleanRoom(Robot robot) {
        this.visited = new HashSet<>();
        this.robot = robot;
        dfs(0, 0, 0);
    }
    void dfs(int i, int j, int d) {
        robot.clean();
        for (int k = 0; k < 4; k++) {
            int d2 = (d+k)%4;
            int i2 = i+dir[d2][0];
            int j2 = j+dir[d2][1];
            Pair p2 = new Pair(i2, j2);
            if (!visited.contains(p2) && robot.move()) {
                visited.add(p2);
                dfs(i2, j2, d2);
                
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
    }
}
class Pair {
    int i, j;
    Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
    public int hashCode() { return i*1000+j; }
    public boolean equals(Object o) {
        Pair p = (Pair) o;
        return i == p.i && j == p.j;
    }
}