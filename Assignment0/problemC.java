import java.util.*;

public class problemC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            String s = "Abracadabra";
            for (int i = 1; i <= n; i++) {
                System.out.println(i + " " + s);
            }
        }
    }
}
