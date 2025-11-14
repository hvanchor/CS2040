import java.io.*;
import java.util.*;

public class longWaitCopy {
    public static void main(String args[]) {
        Kattio ko = new Kattio(System.in);
        int q = ko.getInt();
        int k = ko.getInt();
        StringBuilder result = new StringBuilder();
        
        // Two arrays to simulate the queue
        int[] beforeK = new int[q];
        int[] afterK = new int[q];
        int beforeKCount = 0;
        int afterKCount = 0;
        
        for (int i = 0; i < q; i++) {
            String operation = ko.getWord();
            
            if (operation.equals("member")) {
                int id = ko.getInt();
                
                if (beforeKCount <= k) {
                    // If beforeK array is <= k (means there are at most k ppl in front of member), add member to end of the beforeK queue
                    // beforeKCount ++ to signify addition of member to beforeK queue
                    beforeK[beforeKCount] = id;
                    beforeKCount++;
                } else {
                    // beforeK array is already full, therefore need to push end of beforeK to start of afterK
                    // move each element in afterK one index back
                    // then add member to end of beforeK
                    int lastElement = beforeK[beforeKCount - 1];
                    if (afterKCount > 0) {
                        System.arraycopy(afterK, 0, afterK, 1, afterKCount);
                    }
                    afterK[0] = lastElement;
                    afterKCount++;
                    beforeK[beforeKCount - 1] = id;
                }
            }
            else if (operation.equals("queue")) {
                int id = ko.getInt();
                // Add to end of either beforeK or afterK
                if (beforeKCount <= k) {
                    beforeK[beforeKCount] = id;
                    beforeKCount++;
                } else {
                    afterK[afterKCount] = id;
                    afterKCount++;
                }
            }
            else if (operation.equals("vip")) {
                int id = ko.getInt();
                // Add to front of beforeK
                // if beforeK empty
                if (beforeKCount == 0) {
                    beforeK[0] = id;
                    beforeKCount++;
                // if beforeK not empty
                } else {
                    System.arraycopy(beforeK, 0, beforeK, 1, beforeKCount);
                    beforeK[0] = id;
                    beforeKCount++;
                }
                // if beforeK array is now > k+1 (max size of beforeK) --> move last element of beforeK to start of afterK
                if (beforeKCount > k + 1) {
                    int lastElement = beforeK[beforeKCount - 1];
                    if (afterKCount > 0) {
                        System.arraycopy(afterK, 0, afterK, 1, afterKCount);
                    }
                    afterK[0] = lastElement;
                    afterKCount++;
                    beforeKCount--;
                }
                
            }
            else if (operation.equals("faster")) {
                k = Math.max(1, k - 1);
                // if beforeK array size is now larger than k + 1 (max queue), move last element of beforeK to start of afterK
                if (beforeKCount > k + 1) {
                    int lastElement = beforeK[beforeKCount - 1];
                    System.arraycopy(afterK, 0, afterK, 1, afterKCount);
                    afterK[0] = lastElement;
                    afterKCount++;
                    beforeKCount--;
                }
            }
            else if (operation.equals("slower")) {
                k++;
                // move first element of afterK to beforeK
                if (afterKCount > 0) {
                    int firstElement = afterK[0];
                    // add firstElement of afterK to end of beforeK
                    beforeK[beforeKCount] = firstElement;
                    beforeKCount++;
                    // if there are remaining elements in afterK, shift each element forward
                    if (afterKCount > 1) {
                        System.arraycopy(afterK, 1, afterK, 0, afterKCount - 1);
                    }
                }
            }
            else if (operation.equals("findID")) {
                int pos = ko.getInt(); // 1-indexed position
                
                if (pos > 0 && pos <= beforeKCount) {
                    result.append(beforeK[pos - 1]).append("\n");
                } else if (pos <= afterKCount + beforeKCount) {
                    int afterKPos = pos - beforeKCount;
                    result.append(afterK[afterKPos - 1]).append("\n");
                }
            }
        }
        System.out.print(result);
    }
}

// Kattio class remains the same
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
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}