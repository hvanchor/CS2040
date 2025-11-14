import java.util.*;

// Every vertex in this BST is a Java Class
class BSTVertex {
    // MODIFIED: Constructor now takes long key and tree volume
    BSTVertex(long p, int v) { 
        key = p; 
        volume = v; // New field for tree volume
        maxVolumeInSubtree = v; // New field for augmentation
        parent = left = right = null; 
        height = 0; 
    }

    public BSTVertex parent, left, right;
    public long key; // MODIFIED: Changed from int to long
    public int volume; // NEW: Stores the tree's volume (V)
    public int maxVolumeInSubtree; // NEW: Stores the max volume in this subtree
    public int height; 
}

// This is just a sample implementation
class BST {
    protected BSTVertex root;
    // NEW: Map to track position -> volume for O(1) return value lookup
    protected HashMap<Long, Integer> positionMap; 

    // Helper for augmentation (Max volume in subtree, returns 0 if null)
    protected int getMaxVolume(BSTVertex T) {
        return T == null ? 0 : T.maxVolumeInSubtree;
    }

    // Helper to update the augmentation property after structural changes
    protected void updateAugmentation(BSTVertex T) {
        if (T == null) return;
        T.maxVolumeInSubtree = Math.max(T.volume, 
            Math.max(getMaxVolume(T.left), getMaxVolume(T.right))
        );
    }
    
    // MODIFIED: key parameter is now long
    protected BSTVertex search(BSTVertex T, long v) {
        if (T == null) return T;
        else if (T.key == v) return T;
        else if (T.key < v) return search(T.right, v);
        else return search(T.left, v);
    }

    // MODIFIED: key parameter is now long, volume is passed
    protected BSTVertex insert(BSTVertex T, long p, int v) {
        if (T == null) return new BSTVertex(p, v); // Uses new constructor

        if (T.key < p) {
            T.right = insert(T.right, p, v);
            T.right.parent = T;
        }
        else {
            T.left = insert(T.left, p, v);
            T.left.parent = T;
        }
        
        // NEW: Update augmentation on the way up (for non-AVL, simple BST)
        updateAugmentation(T); 
        
        return T;
    }

    protected void inorder(BSTVertex T) {
        if (T == null) return;
        inorder(T.left);
        System.out.printf(" %d", T.key);
        inorder(T.right);
    }

    // MODIFIED: returns long
    protected long findMin(BSTVertex T) {
        if (T == null) throw new NoSuchElementException("BST is empty, no minimum");
        else if (T.left == null) return T.key;
        else return findMin(T.left);
    }

    // MODIFIED: returns long
    protected long findMax(BSTVertex T) {
        if (T == null) throw new NoSuchElementException("BST is empty, no maximum");
        else if (T.right == null) return T.key;
        else return findMax(T.right);
    }

    // MODIFIED: returns long
    protected long successor(BSTVertex T) {
        if (T.right != null) return findMin(T.right);
        else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            while ((par != null) && (cur == par.right)) {
                cur = par;
                par = cur.parent;
            }
            // MODIFIED: returns -1 as a long
            return par == null ? -1L : par.key; 
        }
    }

    // MODIFIED: returns long
    protected long predecessor(BSTVertex T) {
        if (T.left != null) return findMax(T.left);
        else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            while ((par != null) && (cur == par.left)) {
                cur = par;
                par = cur.parent;
            }
            // MODIFIED: returns -1 as a long
            return par == null ? -1L : par.key; 
        }
    }

    // MODIFIED: key parameter is now long
    protected BSTVertex delete(BSTVertex T, long v) {
        if (T == null) return T;

        if (T.key == v) {
            if (T.left == null && T.right == null) T = null;
            else if (T.left == null && T.right != null) {
                T.right.parent = T.parent;
                T = T.right;
            }
            else if (T.left != null && T.right == null) {
                T.left.parent = T.parent;
                T = T.left;
            }
            else {
                // MODIFIED: successorV is now long
                long successorV = successor(v); 
                T.key = successorV;
                T.right = delete(T.right, successorV);
            }
        }
        else if (T.key < v) T.right = delete(T.right, v);
        else T.left = delete(T.left, v);
        
        // NEW: Update augmentation after deletion
        updateAugmentation(T); 
        
        return T;
    }

    public BST() { root = null; positionMap = new HashMap<>(); }

    // MODIFIED: public methods use long keys
    public long search(long v) {
        BSTVertex res = search(root, v);
        return res == null ? -1L : res.key;
    }

    // MODIFIED: public methods use long keys, new signature for problem
    public void insert(long p, int v) { root = insert(root, p, v); } 
    
    public void inorder() { inorder(root); System.out.println(); }
    
    // MODIFIED: return long
    public long findMin() { return findMin(root); }
    public long findMax() { return findMax(root); }

    // MODIFIED: use long keys
    public long successor(long v) {
        BSTVertex vPos = search(root, v);
        return vPos == null ? -1L : successor(vPos);
    }

    // MODIFIED: use long keys
    public long predecessor(long v) {
        BSTVertex vPos = search(root, v);
        return vPos == null ? -1L : predecessor(vPos);
    }

    // MODIFIED: use long keys
    public void delete(long v) { root = delete(root, v); }

    protected int getHeight(BSTVertex T) {
        if (T == null) return -1;
        else return Math.max(getHeight(T.left), getHeight(T.right)) + 1;
    }

    public int getHeight() { return getHeight(root); }
}

class AVL extends BST { 
    public AVL() { root = null; }

    private int h(BSTVertex T) { return T == null ? -1 : T.height; }

    // Helper to replace the existing getHeight(BSTVertex T) from the provided code
    private void updateHeight(BSTVertex T) {
        if (T != null) T.height = Math.max(h(T.left), h(T.right)) + 1;
    }

    // MODIFIED: Added augmentation update
    protected BSTVertex rotateLeft(BSTVertex T) {
        BSTVertex w = T.right;
        w.parent = T.parent;
        T.parent = w;
        T.right = w.left;
        if (w.left != null) w.left.parent = T;
        w.left = T;

        updateHeight(T); 
        updateHeight(w);
        updateAugmentation(T); // NEW: Augmentation update
        updateAugmentation(w); // NEW: Augmentation update
        
        return w;
    }

    // MODIFIED: Added augmentation update
    protected BSTVertex rotateRight(BSTVertex T) {
        BSTVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if (w.right != null) w.right.parent = T;
        w.right = T;

        updateHeight(T);
        updateHeight(w);
        updateAugmentation(T); // NEW: Augmentation update
        updateAugmentation(w); // NEW: Augmentation update
        
        return w;
    }

    // MODIFIED: Added augmentation update
    protected BSTVertex rebalance(BSTVertex T) {
        int balance = h(T.left) - h(T.right);
        if (balance == 2) { 
            int balance2 = h(T.left.left) - h(T.left.right);
            if (balance2 >= 0) {
                T = rotateRight(T);
            } else {
                T.left = rotateLeft(T.left);
                T = rotateRight(T);
            }
        } else if (balance == -2) { 
            int balance2 = h(T.right.left) - h(T.right.right);
            if (balance2 <= 0) {
                T = rotateLeft(T);
            } else { 
                T.right = rotateRight(T.right);
                T = rotateLeft(T);
            }
        }

        updateHeight(T);
        updateAugmentation(T); // NEW: Augmentation update
        
        return T;
    }

    // MODIFIED: key parameter is now long, volume is passed
    protected BSTVertex insert(BSTVertex T, long p, int v) {
        if (T == null) return new BSTVertex(p, v); 

        if (T.key < p) {
            T.right = insert(T.right, p, v);
            T.right.parent = T;
        } else {
            T.left = insert(T.left, p, v);
            T.left.parent = T;
        }

        T = rebalance(T);
        
        // Ensure final augmentation is correct
        updateAugmentation(T); 
        
        return T;
    }
    
    // NEW: Wrapper for update/insert logic
    public void update(long p, int v) {
        int oldVolume = positionMap.getOrDefault(p, 0);
        positionMap.put(p, v);

        if (oldVolume == 0) {
            // New tree: insert a new position
            root = insert(root, p, v); 
        } else {
            // Existing tree: update volume and re-augment ancestors
            updateExistingRecursive(root, p, v);
        }
    }
    
    // NEW: Recursive update function to find the existing node and fix augmentation path
    private void updateExistingRecursive(BSTVertex node, long p, int v) {
        if (node == null) return;
        
        if (p < node.key) {
            updateExistingRecursive(node.left, p, v);
        } else if (p > node.key) {
            updateExistingRecursive(node.right, p, v);
        } else {
            node.volume = v; // Found the node, update its volume
        }
        // Propagate the augmentation upwards
        updateAugmentation(node);
    }
    
    // Overridden delete to use long key
    protected BSTVertex delete(BSTVertex T, long v) {
        if (T == null) return T;

        if (T.key == v) {
            if (T.left == null && T.right == null) T = null;
            else if (T.left == null && T.right != null) {
                BSTVertex temp = T;
                T.right.parent = T.parent;
                T = T.right;
                temp = null;
            }
            else if (T.left != null && T.right == null) {
                BSTVertex temp = T;
                T.left.parent = T.parent;
                T = T.left;
                temp = null;
            }
            else {
                long successorV = successor(v);
                T.key = successorV;
                T.right = delete(T.right, successorV);
            }
        }
        else if (T.key < v) T.right = delete(T.right, v);
        else T.left = delete(T.left, v);

        if (T != null) {
            T = rebalance(T);
        }
        
        // NEW: Augmentation update after deletion
        updateAugmentation(T);

        return T;
    }


    // NEW: Range Maximum Query (Q) - O(log N) due to augmentation
    public int rangeMaxQuery(long L, long R) {
        return queryRecursive(root, L, R);
    }

    private int queryRecursive(BSTVertex node, long L, long R) {
        if (node == null) return 0;
        
        // Pruning checks based on key to guide traversal
        if (node.key > R) { return queryRecursive(node.left, L, R); }
        if (node.key < L) { return queryRecursive(node.right, L, R); }

        int max = 0;

        // 1. If current node's key is within the range, include its volume
        if (node.key >= L && node.key <= R) {
            max = node.volume;
        }

        // 2. Traversal
        // We must check both children because this node is either a split node or fully contained.
        max = Math.max(max, queryRecursive(node.left, L, R));
        max = Math.max(max, queryRecursive(node.right, L, R));
        
        return max;
    }
}