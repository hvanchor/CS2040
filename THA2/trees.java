import java.util.HashMap;

public class trees {

    // --- 1. NODE CLASS ---
    // A simple node structure for the Dynamic Segment Tree
    static class Node {
        Node left;      
        Node right;     
        int maxVolume;  

        public Node() {
            this.maxVolume = 0;
        }
    }

    // --- 2. DYNAMIC SEGMENT TREE CLASS ---
    static class DynamicSegmentTree {
        private final long MAX_COORD = 1_000_000_000L;
        private Node root;
        
        // HashMap to store the actual volume at specific positions (P -> V). 
        private HashMap<Long, Integer> positionValues; 

        public DynamicSegmentTree() {
            // Root covers the entire range [1, 10^9]
            this.root = new Node(); 
            this.positionValues = new HashMap<>();
        }

        /**
         * Operation U: Updates the tree volume at position p with new volume v.
         * @return The volume of the uprooted tree (or 0).
         */
        public int update(long p, int v) {
            // 1. Get the old volume (for return value)
            int oldVolume = positionValues.getOrDefault(p, 0);
            
            // 2. Update the HashMap
            positionValues.put(p, v);

            // 3. Update the tree structure
            updateRecursive(root, 1, MAX_COORD, p, v);
            return oldVolume;
        }

        private void updateRecursive(Node curr, long start, long end, long p, int v) {
            // Base case: Reached the leaf node
            if (start == end) {
                curr.maxVolume = v; 
                return;
            }

            long mid = start + (end - start) / 2;
            
            if (p <= mid) {
                if (curr.left == null) curr.left = new Node(); 
                updateRecursive(curr.left, start, mid, p, v);
            } else {
                if (curr.right == null) curr.right = new Node(); 
                updateRecursive(curr.right, mid + 1, end, p, v);
            }

            curr.maxVolume = Math.max(
                (curr.left != null ? curr.left.maxVolume : 0),
                (curr.right != null ? curr.right.maxVolume : 0)
            );
        }

        /**
         * Operation Q: Finds the max volume in the range [l, r].
         * @return The maximum volume in [l, r], or 0.
         */
        public int query(long l, long r) {
            return queryRecursive(root, 1, MAX_COORD, l, r);
        }

        private int queryRecursive(Node curr, long start, long end, long l, long r) {
            // Case 1: Node doesn't exist or segment is fully outside query range
            if (curr == null || start > r || end < l) {
                return 0; 
            }
            
            // Case 2: Current segment is fully inside the query range [l, r]
            if (l <= start && end <= r) {
                return curr.maxVolume;
            }

            // Case 3: Partial overlap (recurse)
            long mid = start + (end - start) / 2;
            
            int maxLeft = queryRecursive(curr.left, start, mid, l, r);
            int maxRight = queryRecursive(curr.right, mid + 1, end, l, r);

            return Math.max(maxLeft, maxRight);
        }
    }

    // --- 3. MAIN EXECUTION ---
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in); 
        
        int Q = io.getInt();
        DynamicSegmentTree tree = new DynamicSegmentTree(); 

        for (int i = 0; i < Q; i++) {
            String operation = io.getWord();
            
            if (operation.equals("u")) {
                // u P V: Update position P with volume V
                long p = io.getLong();
                int v = io.getInt();
                io.println(tree.update(p, v));
            } else if (operation.equals("?")) {
                // ? L R: Query max volume in range [L, R]
                long l = io.getLong();
                long r = io.getLong();
                io.println(tree.query(l, r));
            }
        }
        io.close();
    }
}