import java.util.*;

public class lostMap {

    static class Edge {
        int u, v;

        public Edge(int a, int b) {
            this.u = Math.min(a, b);
            this.v = Math.max(a, b);
        }

        @Override
        public String toString() {
            return u + " " + v;
        }
    }
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        
        int n = io.getInt();
        int[][] adjM = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ;j++) {
                adjM[i][j] = io.getInt();
            }
        }
        
        // Data Structures for Prim's
        int[] key = new int[n];
        boolean[] mstSet = new boolean[n];
        int[] parent = new int[n];

        // Initialize all keys to infinity
        Arrays.fill(key, Integer.MAX_VALUE);

        key[0] = 0;      // Start at vertex 0
        parent[0] = -1;  // Vertex 0 is the root

        // Prim's Main Loop: run N times to select N vertices
        for (int count = 0; count < n; count++) {
            
            int min = Integer.MAX_VALUE; 
            int u = -1; 

            // 1. Find the unvisited vertex 'u' with the smallest key
            for (int v = 0; v < n; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }

            // If u == -1, the graph is disconnected (or we are done)
            if (u == -1) break;

            // 2. Add the picked vertex 'u' to the MST set
            mstSet[u] = true;

            // 4. Update key values and parent array for adjacent vertices
            for (int v = 0; v < n; v++) {
                // Check if:
                // a) Edge exists (adjM[u][v] is not 0, assuming distances are > 0)
                // b) Vertex v is NOT in MST (!mstSet[v])
                // c) The new edge (u-v) is cheaper than the existing key[v]
                if (adjM[u][v] != 0 && !mstSet[v] && adjM[u][v] < key[v]) {
                    key[v] = adjM[u][v];
                    parent[v] = u;
                }
            }
        }

        ArrayList<Edge> mstEdges = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            int v1 = parent[i] + 1;
            int v2 = i + 1;
            mstEdges.add(new Edge(v1, v2));
        }

        Collections.sort(mstEdges, new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                if (a.u != b.u) {
                    return Integer.compare(a.u, b.u);
                }
                return Integer.compare(a.v, b.v);
            }
        });

        for (Edge edge : mstEdges) {
            io.println(edge);
        }

        io.close();
    }
}
