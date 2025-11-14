import java.util.*;

public class islands {
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int r, c;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        r = io.getInt();
        c = io.getInt();
        surface[][] grid = new surface[r][c];

        for (int i = 0; i < r; i++) {
            String line = io.getWord();
            for (int j = 0; j < c; j++) {
                grid[i][j] = new surface(i, j, line.charAt(j));
            }
        }

        boolean[][] visited = new boolean[r][c];
        int minIslandCount = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && !grid[i][j].isWater()) {
                    boolean hasOriginalLand = runBFS(grid, visited, i, j);
                    if (hasOriginalLand) {
                        minIslandCount++;
                    }
                }
            }
        }

        io.println(minIslandCount);
        io.close();
    }

    private static boolean runBFS(surface[][] surfaceGrid, boolean[][] visited, int startR, int startC) {
        Queue<surface> queue = new LinkedList<>();
        queue.add(surfaceGrid[startR][startC]);
        visited[startR][startC] = true;
        
        boolean foundLand = surfaceGrid[startR][startC].isLand();

        while (!queue.isEmpty()) {
            surface current = queue.poll();
            int rc = current.getR();
            int cc = current.getC();

            for (int i = 0; i < 4; i++) {
                int nr = rc + dr[i];
                int nc = cc + dc[i];

                if (nr >= 0 && nr < r && nc >= 0 && nc < c) {
                    surface neighbor = surfaceGrid[nr][nc];

                    if (!visited[nr][nc] && !neighbor.isWater()) {
                        
                        visited[nr][nc] = true;
                        queue.add(neighbor);
                        
                        if (neighbor.isLand()) {
                            foundLand = true;
                        }
                    }
                }
            }
        }
        
        return foundLand;
    }
}

class surface {
    private final int r;
    private final int c;
    private char type;

    public surface(int r, int c, char type) {
        this.r = r;
        this.c = c;
        this.type = type;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public boolean isLand() {
        return this.type == 'L';
    }

    public boolean isWater() {
        return this.type == 'W';
    }
}

// // FUNCTION MAIN: Minimum Island Counter
// FUNCTION main:
//     // READ R, C
//     READ R, C
//     // grid = INITIALIZE Surface_Grid[R][C] from input
//     grid = INITIALIZE Surface_Grid[R][C] from input
//     // visited = INITIALIZE Visited_Boolean_Array[R][C]
//     visited = INITIALIZE Visited_Boolean_Array[R][C]
//     // minIslandCount = 0
//     minIslandCount = 0
//
//     // Iterate through all cells
//     FOR each (r, c) in grid:
//         // current = grid[r][c]
//         current = grid[r][c]
//         
//         // Start BFS only if unvisited AND is NOT Water
//         IF NOT visited[r][c] AND NOT current.isWater():
//             // Traverse component and check if it contains original Land ('L')
//             hasLand = runBFS(grid, visited, r, c)
//             
//             // Count as a minimum island only if 'L' is present (Cloud-only components are ignored)
//             IF hasLand IS TRUE:
//                 minIslandCount = minIslandCount + 1
//             END IF
//         END IF
//     END FOR
//
//     // OUTPUT minIslandCount
//     OUTPUT minIslandCount
// END FUNCTION
//
// ----------------------------------------------------
//
// // FUNCTION runBFS: Traverses connected 'L' and 'C' cells
// FUNCTION runBFS(surfaceGrid, visited, startR, startC):
//     // queue = new Queue<Surface>()
//     queue = new Queue<Surface>()
//     // queue.add(surfaceGrid[startR][startC])
//     queue.add(surfaceGrid[startR][startC])
//     // visited[startR][startC] = TRUE
//     visited[startR][startC] = TRUE
//     // foundLand = surfaceGrid[startR][startC].isLand()
//     foundLand = surfaceGrid[startR][startC].isLand()
//
//     // WHILE queue IS NOT EMPTY:
//     WHILE queue IS NOT EMPTY:
//         // current = queue.poll()
//         current = queue.poll()
//         // rc = current.getR()
//         rc = current.getR()
//         // cc =