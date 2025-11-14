import java.util.*;

public class problemB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        for (int i = 1; i < c + 1; i++) {
            if (i%a == 0 && i%b == 0) {
                System.out.println("FizzBuzz");
            }
            else if (i%a == 0) {
                System.out.println("Fizz");
            }
            else if (i%b == 0) {
                System.out.println("Buzz");
            }
            else {
                System.out.println(i);
            }
        }
    } 
}
