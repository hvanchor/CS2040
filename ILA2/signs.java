import java.util.*;

public class signs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextLine().trim());
        }

        Collections.sort(list, new Comparator<String>() {
            public int compare(String a, String b) {
                String keyA = getMiddleKey(a);
                String keyB = getMiddleKey(b);
                return keyA.compareTo(keyB);
            }
        });

        for (String word : list) {
            System.out.println(word);
        }
    }

    private static String getMiddleKey(String s) {
        int len = s.length();
        if (len % 2 == 0) {
            return s.substring(len/2 - 1, len/2 + 1);
        } else {
            return s.substring(len/2, len/2 + 1);
        }
    }
}

//BEGIN
    //READ integer n from input
    //CREATE an empty list called stringList
    
    //FOR i from 0 to n-1:
        //READ a string from input and trim whitespace
        //ADD the string to stringList
    
    //SORT stringList using custom comparator:
        //COMPARE_FUNCTION(a, b):
            //keyA = GET_MIDDLE_KEY(a)
            //keyB = GET_MIDDLE_KEY(b)
            //RETURN comparison of keyA and keyB (lexicographical order)
    
    //FOR EACH word in stringList:
        //PRINT word
//END

//FUNCTION GET_MIDDLE_KEY(string s):
    //len = LENGTH(s)
    //IF len is even:
        //RETURN substring from (len/2 - 1) to (len/2 + 1)
    //ELSE:
        //RETURN substring from (len/2) to (len/2 + 1)       
//END FUNCTION