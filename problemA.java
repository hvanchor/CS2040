import java.util.*;

class problemA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String word = sc.nextLine();
            String result = keyboard(word);
            String caseStr = "Case #" + (i + 1) + ": " + result;
            sb.append(caseStr).append("\n");
        }
        System.out.print(sb.toString());
    }

// function keyboard
// take String input and return Integer, which will be combined into a String
// return keyboard strokes for each character and combine it into one String output

    public static String keyboard(String word) {
        Integer[] dict = {2, 22, 222, 3, 33, 333, 4, 44, 444, 5, 55, 555, 6, 66, 666, 7, 77, 777, 7777, 8, 88, 888, 9, 99, 999, 9999};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == ' ') {
                if (sb.length() == 0) {
                    sb.append("0");
                }
                else if (sb.charAt(sb.length() - 1) == '0')
                    sb.append(" ").append("0");
                else {
                    sb.append("0");
                }
            }
            else {
                int key = c - 'a';
                String input = dict[key].toString();
                if (sb.length() == 0) {
                    sb.append(input);
                }
                else {
                    if (sb.charAt(sb.length() - 1) == input.charAt(0)) {
                        sb.append(" ").append(input);
                    }
                    else {
                        sb.append(input);
                    }
                }
            }
        }
        return sb.toString();
    }
}