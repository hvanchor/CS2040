import java.util.*;

public class problemB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n%2 != 0) {
                System.out.print("Alice");
            }
            else {
                System.out.print("Bob");
            }
        }
    }
}
