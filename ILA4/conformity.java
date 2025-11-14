import java.util.*;

class conformity {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();
        HashMap<List<Integer>, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int[] arr = new int[5];
            for (int j = 0; j < 5; j++) {
                arr[j] = io.getInt();
            }
            Arrays.sort(arr);
            List<Integer> key = new ArrayList<>();
            for (int num : arr) key.add(num);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int maxValue = 0;
        int count = 0;
        for (int val : map.values()){
            if (val > maxValue) {
                maxValue = val;
                count = maxValue;
            } else if (val == maxValue) {
                count += maxValue;
            }
        }
        System.out.println(count);
    } 
}

// FUNCTION main
//     n ← read integer from input
//     map ← empty hash map  // keys are lists of 5 numbers, values are counts

//     FOR i FROM 0 TO n-1 DO
//         arr ← empty list of size 5
//         FOR j FROM 0 TO 4 DO
//             arr[j] ← read integer from input
//         END FOR

//         SORT arr in ascending order

//         key ← list containing elements of arr
//         // Increment the count for this key in the map
//         IF key exists in map THEN
//             map[key] ← map[key] + 1
//         ELSE
//             map[key] ← 1
//         END IF
//     END FOR

//     maxValue ← 0
//     count ← 0

//     FOR each val IN map.values DO
//         IF val > maxValue THEN
//             maxValue ← val
//             count ← maxValue
//         ELSE IF val == maxValue THEN
//             count ← count + maxValue
//         END IF
//     END FOR

//     PRINT count
// END FUNCTION