import java.util.*;

public class problemE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if (possibility(a, b, c)) {
                System.out.println("Possible");
            }
            else {
                System.out.println("Impossible");
            }
        }
    }

    public static boolean possibility(int a, int b, int c) {
        if (a + b == c) {
            return true;
        }
        if (a * b == c) {
            return true;
        }
        if (a * c == b || b * c == a) {
            return true;
        }
        return a - b == c || b - a == c;
    }
}
