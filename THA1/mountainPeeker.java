import java.io.*;
import java.util.*;

class Peak {
    int x;
    int y;

    Peak(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class mountainPeeker {
    public static void main(String args[]) {
        Kattio ko = new Kattio(System.in);
        int n = ko.getInt();
        int h = ko.getInt();
        List<Peak> left = new ArrayList<>();
        List<Peak> right = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = ko.getInt();
            int y = ko.getInt();
            if (x > 0) {
                right.add(new Peak(x, y));
            }
            else {
                left.add(new Peak(Math.abs(x), y));
            }
        }
        right.sort(Comparator.comparingInt(p -> p.x));
        left.sort(Comparator.comparingInt(p -> p.x));

        System.out.println(count(right, h) + count(left, h));
    }

    public static int count(List<Peak> list, int h) {
        int count = 0;
        Peak bestPeak = null;
        for (Peak p : list) {
            if (bestPeak == null) {
                count++;
                bestPeak = p;
            } else {
                long a1 = (long) (p.y - h) * bestPeak.x;
                long a2 = (long) (bestPeak.y - h) * p.x;
                if (a1 > a2) {
                    count++;
                    bestPeak = p;
                }
            }
        }
        return count;
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
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
