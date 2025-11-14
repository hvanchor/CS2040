import java.util.*;

public class problemA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.nextLine();
        int c = sc.nextInt();
        int d = sc.nextInt();
        sc.nextLine();
        int t = sc.nextInt();
        System.out.println(canTravel(a, b, c, d, t));
    }

    public static String canTravel(int a, int b, int c, int d, int t) {
        int ac = Math.abs(a - c);
        int bd = Math.abs(b - d);
        int remaining = t - ac - bd;
        if (remaining >= 0 && remaining%2 == 0) {
            return "Y";
        }
        else {
            return "N";
        }
    }
}
