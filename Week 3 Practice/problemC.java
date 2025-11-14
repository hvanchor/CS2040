import java.util.*;

public class problemC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        int R = sc.nextInt();
        int C = sc.nextInt();
        int Zr = sc.nextInt();
        int Zc = sc.nextInt();
        sc.nextLine();
    
        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < C; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        // Build the enlarged output
        for (int i = 0; i < R; i++) {
            for (int r = 0; r < Zr; r++) {
                for (int j = 0; j < C; j++) {
                    for (int c = 0; c < Zc; c++) {
                        System.out.print(grid[i][j]);
                    }
                }
                System.out.println();
            }
        }
    }
}
