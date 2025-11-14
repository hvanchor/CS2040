import java.util.*;

public class stockPrices {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();

        for (int i = 0; i < n; i++){
            int n2 = io.getInt();
            PriorityQueue<Integer> buyHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> sellHeap = new PriorityQueue<>();
            HashMap<Integer, Integer> buyMap = new HashMap<>();
            HashMap<Integer, Integer> sellMap = new HashMap<>();
            Integer lastDeal = null;
            for (int j = 0; j < n2; j++) {
                String order = io.getWord(); // buy || sell
                int quantity = Integer.parseInt(io.getWord()); // number of shares
                io.getWord(); // "shares"
                io.getWord(); // "at"
                int price = Integer.parseInt(io.getWord()); // price of shares

                if (order.equals("buy")) {
                    buyHeap.add(price);
                    buyMap.put(price, buyMap.getOrDefault(price, 0) + quantity);
                } else {
                    sellHeap.add(price);
                    sellMap.put(price, sellMap.getOrDefault(price, 0) + quantity);
                }

                 while (!buyHeap.isEmpty() && !sellHeap.isEmpty()) {
                    // ensure heap tops are still present in maps
                    while (!buyHeap.isEmpty() && !buyMap.containsKey(buyHeap.peek())) buyHeap.poll();
                    while (!sellHeap.isEmpty() && !sellMap.containsKey(sellHeap.peek())) sellHeap.poll();
                    if (buyHeap.isEmpty() || sellHeap.isEmpty()) break;

                    int buyP = buyHeap.peek();
                    int sellP = sellHeap.peek();
                    if (buyP < sellP) break; // no match

                    int buyQ = buyMap.get(buyP);
                    int sellQ = sellMap.get(sellP);
                    int traded = Math.min(buyQ, sellQ);

                    // deal established at ask price (seller's price)
                    lastDeal = sellP;

                    int newBuyQ = buyQ - traded;
                    int newSellQ = sellQ - traded;

                    if (newBuyQ == 0) {
                        buyMap.remove(buyP);
                        buyHeap.poll();
                    } else {
                        buyMap.put(buyP, newBuyQ);
                    }

                    if (newSellQ == 0) {
                        sellMap.remove(sellP);
                        sellHeap.poll();
                    } else {
                        sellMap.put(sellP, newSellQ);
                    }
                }
                String ask = sellHeap.isEmpty() ? "-" : String.valueOf(sellHeap.peek());
                String bid = buyHeap.isEmpty() ? "-" : String.valueOf(buyHeap.peek());
                String stock = (lastDeal == null) ? "-" : String.valueOf(lastDeal);
                io.println(ask + " " + bid + " " + stock);
            }
        }
        io.flush();
        io.close();
    }
}

// FUNCTION main()
//     // Read the number of test cases
//     READ n_test_cases
//
//     // Loop through each test case
//     FOR i FROM 1 TO n_test_cases DO
//         // Read the number of orders in the current test case
//         READ n_orders
//
//         // Initialize Data Structures for Order Book
//         // Max-Heap: Stores BUY prices, highest price is at the top (Best Bid)
//         buyHeap = new PriorityQueue (Max-Heap order)
//
//         // Min-Heap: Stores SELL prices, lowest price is at the top (Best Ask)
//         sellHeap = new PriorityQueue (Min-Heap order)
//
//         // Map: Stores total quantity available for each BUY price
//         buyMap = new HashMap (Price -> Quantity)
//
//         // Map: Stores total quantity available for each SELL price
//         sellMap = new HashMap (Price -> Quantity)
//
//         // Track the price of the last executed trade
//         lastDeal = NULL
//
//         // Process each incoming order
//         FOR j FROM 1 TO n_orders DO
//             // Read incoming order details
//             READ order_type, quantity, SKIP "shares", SKIP "at", price
//
//             // --- 1. PLACE NEW ORDER ---
//             IF order_type is "buy" THEN
//                 buyHeap.ADD(price)
//                 buyMap.PUT(price, buyMap.GET_OR_DEFAULT(price, 0) + quantity)
//             ELSE // order_type is "sell"
//                 sellHeap.ADD(price)
//                 sellMap.PUT(price, sellMap.GET_OR_DEFAULT(price, 0) + quantity)
//             END IF
//
//             // --- 2. EXECUTE TRADES (MATCHING) ---
//             WHILE buyHeap IS NOT EMPTY AND sellHeap IS NOT EMPTY DO
//                 // Clean up heaps: Remove prices from heap tops that have
//                 // already had their quantities exhausted in the maps.
//                 WHILE buyHeap IS NOT EMPTY AND buyMap.DOES_NOT_CONTAIN_KEY(buyHeap.PEEK()) DO
//                     buyHeap.POLL()
//                 END WHILE
//                 WHILE sellHeap IS NOT EMPTY AND sellMap.DOES_NOT_CONTAIN_KEY(sellHeap.PEEK()) DO
//                     sellHeap.POLL()
//                 END WHILE
//
//                 // If cleanup emptied a heap, stop matching
//                 IF buyHeap IS EMPTY OR sellHeap IS EMPTY THEN
//                     BREAK
//                 END IF
//
//                 // Get Best Bid (Highest Buy Price) and Best Ask (Lowest Sell Price)
//                 best_bid_P = buyHeap.PEEK()
//                 best_ask_P = sellHeap.PEEK()
//
//                 // Check for a trade condition: Bid >= Ask
//                 IF best_bid_P < best_ask_P THEN
//                     BREAK // No match possible
//                 END IF
//
//                 // Determine Trade Quantity
//                 best_bid_Q = buyMap.GET(best_bid_P)
//                 best_ask_Q = sellMap.GET(best_ask_P)
//                 traded_quantity = MIN(best_bid_Q, best_ask_Q)
//
//                 // The deal is executed at the Best Ask price
//                 lastDeal = best_ask_P
//
//                 // --- Update Quantities and Maps ---
//                 new_buy_Q = best_bid_Q - traded_quantity
//                 new_sell_Q = best_ask_Q - traded_quantity
//
//                 // Update Buy Side
//                 IF new_buy_Q == 0 THEN
//                     buyMap.REMOVE(best_bid_P)
//                     buyHeap.POLL() // Remove price from heap
//                 ELSE
//