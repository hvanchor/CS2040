
public class longWait {
    public static void main(String args[]) {
        Kattio ko = new Kattio(System.in);
        int q = ko.getInt();
        int k = ko.getInt();
        StringBuilder result = new StringBuilder();
        
        int maxSize = 5000000;
        int[] beforeK = new int[maxSize];
        int[] afterK = new int[maxSize];
        int beforeKHead = maxSize/2, beforeKTail = maxSize/2;
        int afterKHead = maxSize/2, afterKTail = maxSize/2;
        
        for (int i = 0; i < q; i++) {
            String operation = ko.getWord();
            int beforeKQueue = beforeKTail - beforeKHead;
            int afterKQueue = afterKTail - afterKHead;
            
            if (operation.equals("member")) {
                int id = ko.getInt();
                if (beforeKQueue < k) {
                    beforeK[beforeKTail] = id;
                    beforeKTail++;
                } else {
                    afterKHead--;
                    afterK[afterKHead] = id;
                }
            }
            else if (operation.equals("queue")) {
                int id = ko.getInt();
                if (beforeKQueue < k) {
                    beforeK[beforeKTail] = id;
                    beforeKTail++;
                } else {
                    afterK[afterKTail] = id;
                    afterKTail++;
                }
            }
            else if (operation.equals("vip")) {
                int id = ko.getInt();
                if (beforeKQueue < k) {
                    beforeKHead--;
                    beforeK[beforeKHead] = id;
                } else {
                    beforeKHead--;
                    beforeK[beforeKHead] = id;
                    afterKHead--;
                    afterK[afterKHead] = beforeK[beforeKTail - 1];
                    beforeKTail--;
                }
            }
            else if (operation.equals("faster")) {
                if (k > 0) {
                    k--;
                    if (beforeKQueue > k) {
                        afterKHead--;
                        afterK[afterKHead] = beforeK[beforeKTail - 1];
                        beforeKTail--;
                    }
                }
            }
            else if (operation.equals("slower")) {
                k++;
                if (beforeKQueue < k && afterKQueue> 0) {
                    beforeK[beforeKTail] = afterK[afterKHead];
                    beforeKTail++;
                    afterKHead++;
                }
            }
            else if (operation.equals("findID")) {
                int pos = ko.getInt();
                
                if (pos > 0 && pos <= beforeKQueue) {
                    result.append(beforeK[beforeKHead + pos - 1]).append("\n");
                } else if (pos <= afterKQueue + (beforeKQueue)){
                    pos -= (beforeKQueue);
                    result.append(afterK[afterKHead + pos - 1]).append("\n");
                }
            }
        }
        System.out.print(result);
    }
}