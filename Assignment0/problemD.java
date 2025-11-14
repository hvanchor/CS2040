import java.util.*;

public class problemD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int factorial = fac(n);
            System.out.println(factorial);
        }
    }

    public static Integer fac(int x) {
        if (x == 0) {
            return 1;
        }
        else {
            int factorial = 1;
            for (int i = x; i > 0; i--) {
                factorial *= i;
            }
            while (factorial > 9) {
                factorial %= 10;
            }
            return factorial;
        }
    }
}
