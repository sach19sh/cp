/*
https://leetcode.com/problems/shortest-path-in-binary-matrix
- BFS approach using Queue and visited boolean matrix to store true/false
- Important to note that another optimization is possible using A* algo
- In this algo at each loc we will try to calculate optimal distance from here to reach to end
- this will be our weight to decide which path to proceed first



*/
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        
        if(grid[0][0] == 1 || grid[grid.length-1][grid[0].length-1] == 1) return -1;
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> que = new LinkedList<int[]>();
        que.add(new int[] {0,0});
        visited[0][0] = true;
        int dist = 0;
        
        int[][] directions = new int[][] {{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}};
        while(!que.isEmpty()) {
            int size = que.size();
            
            for(int k=0;k<size;k++) {
                int[] item = que.poll();
                
                if(item[0]== grid.length -1 && item[1] == grid[0].length -1) {
                        return dist+1;
                }
                for(int[] dir : directions) {
                    int i = item[0]+dir[0];
                    int j = item[1]+dir[1];
                    
                    if(i>=0 && i<grid.length && j>=0 && j<grid[0].length && 
                       grid[i][j] == 0 && !visited[i][j]) {
                        que.add(new int[]{i,j});
                        visited[i][j] = true;
                    }
                    
                    
                }
            }
            dist++;
        }
        return -1;
    } 
}
