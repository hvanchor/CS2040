import java.util.*;

public class problemB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < p; i++) {
            int n = sc.nextInt();
            StringBuilder result = new StringBuilder(Integer.toString(n)).append(" ");
            int a = sc.nextInt();
            int s1 = S1(a);
            int s2 = S2(a);
            int s3 = S3(a);
            sc.nextLine();
            result.append(Integer.toString(s1)).append(" ").append(Integer.toString(s2)).append(" ").append(Integer.toString(s3));
            System.out.println(result);
        }
    }

    public static int S1(int a) {
        int result = 0;
        for (int i = 0; i <= a; i++) {
            result += i;
        }
        return result;
    }

    public static int S2(int a) {
        int result = 0;
        for (int i = 0; i <= a; i++) {
            if (i%2 != 0) {
                result += i;
            }
        }
        return result;
    }

    public static int S3(int a) {
        int result = 0;
        for (int i = 0; i <= a; i++) {
            if (i%2 == 0) {
                result += i;
            }
        }
        return result;
    }
}
