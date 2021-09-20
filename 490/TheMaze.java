public class Solution {
    //用全局变量，因为dfs也要用
    int m, n;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//向左向右向下向上移动
  
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze.length == 0 || maze[0].length == 0) return false; //迷宫为空有两种情况
        if(start[0] == destination[0] && start[1] == destination[1]) return true; //起点等于终点的情况
         
        m = maze.length; n = maze[0].length;//m，n是迷宫的高/宽，m的求法重点注意
        boolean[][] visited = new boolean[m][n];//visit最好用bool，因为有自动初始化false
        return dfs(maze, start, destination, visited);
    }
    
    private boolean dfs(int[][] maze, int[] cur, int[] dest, boolean[][] visited) {
        // already visited
        if(visited[cur[0]][cur[1]]) return false;//上来先return条件，false是目前的点已经走过
        // reach destination
        if(Arrays.equals(cur, dest)) return true;//判断过有没有找过后，再来true是目前的点等于目标
         
        visited[cur[0]][cur[1]] = true;
        for(int[] dir : dirs) {//遍历所有移动方向
            int nx = cur[0], ny = cur[1];
            while(notWall(nx + dir[0], ny + dir[1]) && maze[nx+dir[0]][ny+dir[1]] != 1) {//不撞边界墙，且不撞1
                nx += dir[0]; ny += dir[1];//始终朝这个方向移动，直到撞墙结束循环
            }
            if(dfs(maze, new int[] {nx, ny}, dest, visited)) return true;//用新的nx，ny替代cur，进行递归
        }
        return false;
    }
     
  /**
  * 判断是否在边界内的help method
  */
  private boolean notWall(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
