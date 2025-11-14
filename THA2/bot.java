import java.io.*;
import java.util.*;

public class bot{

    // Adjacency list for the graph
    private List<List<Integer>> adj;
    // All edges, to check connections between SCCs
    private List<int[]> allEdges;

    private int[] disc; 
    private int[] low;  
    private int[] sccId; 
    private int[] sccSize; 
    private boolean[] onStack;
    private Deque<Integer> stack;

    private int time;
    private int sccCount;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        bot problem = new bot();
        problem.solve(io);
        io.close();
    }

    public void solve(Kattio io) {
        int n = io.getInt(); 
        int m = io.getInt(); 

        adj = new ArrayList<>();
        allEdges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        disc = new int[n];
        low = new int[n];
        sccId = new int[n];
        sccSize = new int[n]; 
        onStack = new boolean[n];
        stack = new ArrayDeque<>();
        Arrays.fill(disc, -1); 

        time = 0;
        sccCount = 0;

        for (int i = 0; i < m; i++) {
            int u = io.getInt();
            int v = io.getInt();
            if (u == v) continue; 
            
            adj.get(u).add(v);
            allEdges.add(new int[]{u, v});
        }
        
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i);
            }
        }

        sccSize = new int[sccCount];
        for(int id : sccId) {
            sccSize[id]++;
        }

        boolean[] isSourceSCC = new boolean[sccCount];
        Arrays.fill(isSourceSCC, true);

        for (int[] edge : allEdges) {
            int u = edge[0];
            int v = edge[1];
            if (sccId[u] != sccId[v]) {
                isSourceSCC[sccId[v]] = false;
            }
        }

        int solobotStarts = 0;
        int botnetStarts = 0;

        for (int id = 0; id < sccCount; id++) {
            if (isSourceSCC[id]) {
                if (sccSize[id] == 1) {
                    solobotStarts++;
                } else { 
                    botnetStarts++;
                }
            }
        }

        io.println(solobotStarts + " " + botnetStarts);
    }

    private void dfs(int u) {
        disc[u] = low[u] = time++;
        stack.push(u);
        onStack[u] = true;

        for (int v : adj.get(u)) {
            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            int currentSCCId = sccCount++;
            while (true) {
                int v = stack.pop();
                onStack[v] = false;
                sccId[v] = currentSCCId;
                if (v == u) {
                    break;
                }
            }
        }
    }
}


class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}