import java.util.*;

public class weakVertices {
    public static void main(String[] args) {  
        Kattio io = new Kattio(System.in);
        int n;
        while ((n = io.getInt()) != -1) {
            if (n > 0) {
                weakV(n, io);
            } else if (n == 0) {
                System.out.println();
            }
        }
        io.close();
    }

    public static void weakV(int n, Kattio io) {
         int[][] adjMatrix = new int[n][n]; // space: O(V^2) pro: dense graph, check if edge present -> O(1) 
        //ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(); // space: O(V+E) pro: sparse graph, efficient to traverse through neighbours
        // ArrayList<IntegerPair> edgeList = new ArrayList<>() -- IntegerPair Class must be defined // space: O(E), inefficient to lookup if edge exists
        ArrayList<Integer> weakV = new ArrayList<>();
        // Initialise the AdjMatrix   
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                adjMatrix[i][h] = io.getInt();
            }
        }

        for (int x = 0; x < n; x++) {
            boolean isWeak = true;
            for (int y = 0; y < n; y++) {
                if (adjMatrix[x][y] == 1) { 
                    for (int z = y + 1; z < n; z++) { 
                        if (adjMatrix[x][z] == 1) {
                            if (adjMatrix[y][z] == 1) {
                                isWeak = false;
                                break;
                            }
                        }
                    }
                }
                if (!isWeak) {
                    break;
                }
            }
            if (isWeak) {
                weakV.add(x);
            }
        }
        for (int i = 0; i < weakV.size(); i++) {
            System.out.print(weakV.get(i) + (i == weakV.size() - 1 ? "" : " "));
        }
        System.out.println();
    }
}

// PROGRAM START
//
// FUNCTION main()
// WHILE (Read vertex count N from input is not -1)
//     IF N > 0 THEN CALL weakV(N)
//     ELSE PRINT newline
// END WHILE
//
// FUNCTION weakV(N)
// DECLARE N x N integer matrix (AdjMatrix) and List for WeakVertices
// READ the N x N AdjMatrix from input
//
// // Iterate over every vertex X and check for a triangle
// FOR each vertex X from 0 to N-1
//     SET isTriangleFound = FALSE
//     // Search all pairs of neighbors (Y, Z) of X
//     FOR each potential neighbor Y
//         IF (X, Y) is an edge THEN
//             FOR each potential neighbor Z (where Z > Y)
//                 // Check for the three edges forming a triangle (X,Y), (X,Z), (Y,Z)
//                 IF (X, Z) is an edge AND (Y, Z) is an edge THEN
//                     SET isTriangleFound = TRUE
//                     BREAK all inner loops for X
//                 END IF
//             END FOR
//         END IF
//     END FOR
//
//     IF isTriangleFound is FALSE THEN
//         ADD X to WeakVertices List
//     END IF
// END FOR
//
// PRINT all vertices in WeakVertices list, space-separated
// END FUNCTION