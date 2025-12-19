package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CheckoutSolution {

    private static final Map<Character, Integer> PRICE = Map.ofEntries (
        Map.entry('A', 50),
        Map.entry('B', 30),
        Map.entry('C', 20),
        Map.entry('D', 15),
        Map.entry('E', 40),
        Map.entry('F', 10),
        Map.entry('G', 20),
        Map.entry('H', 10),
        Map.entry('I', 35),
        Map.entry('J', 60),
        Map.entry('K', 80),
        Map.entry('L', 90),
        Map.entry('M', 15),
        Map.entry('N', 40),
        Map.entry('O', 10),
        Map.entry('P', 50),
        Map.entry('Q', 30),
        Map.entry('R', 50),
        Map.entry('S', 30),
        Map.entry('T', 20),
        Map.entry('U', 40),
        Map.entry('V', 50),
        Map.entry('W', 20),
        Map.entry('X', 90),
        Map.entry('Y', 10),
        Map.entry('Z', 50)
    );

    private static final Map<Character, NavigableMap<Integer, Integer>> OFFER = 
    Map.of(
        'A', new TreeMap<>(Map.of(
            5, 200,
            3, 130
        )),
        
        'B', new TreeMap<>(Map.of(
            2, 45
        )),

        'F', new TreeMap<>(Map.of(
            3, 20
        )),

        'H', new TreeMap<>(Map.of(
            10, 80,
            5, 45
        )),

        'K', new TreeMap<>(Map.of(
            2, 150
        )),

        'P', new TreeMap<>(Map.of(
            5, 200
        )),

        'Q', new TreeMap<>(Map.of(
            3, 80
        )),

        'U', new TreeMap<>(Map.of(
            4, 120
        )),

        'V', new TreeMap<>(Map.of(
            3, 130,
            2, 90
        ))

    );

    private static final Map<Character, int[]> FREE_ITEM = Map.of(
        'E', new int[] {2, 'B'},
        'N', new int[] {3, 'M'},
        'R', new int[] {3, 'Q'}
    );

    public Integer checkout(String skus) {

        if (skus == null) {
            return -1;
        }

        Map<Character, Integer> itemCount = new HashMap<>();

        // check for invalid items and count occurrences
        for (int i=0; i<skus.length(); i++) {
            char item = skus.charAt(i);
            if (!PRICE.containsKey(item)) {
                return -1;
            }
            itemCount.put(item, itemCount.getOrDefault(item, 0) + 1);
        }

        // apply free item offers
        for (var entry : FREE_ITEM.entrySet()){
            char triggerItem = entry.getKey();
            int[] rule = entry.getValue();

            int triggerQty = rule[0];
            char freeItem = (char) rule[1];

            int triggerCount = itemCount.getOrDefault(triggerItem, 0);
            int freeQty = triggerCount / triggerQty;

            if (freeQty > 0) {
                int current = itemCount.getOrDefault(freeItem, 0);
                itemCount.put(freeItem, Math.max(0, current - freeQty));
            }
        }

        int total = 0;

        // calculate price and offer
        for (var pair : itemCount.entrySet()){
            char item = pair.getKey();
            int count = pair.getValue();

            if (OFFER.containsKey(item)){
                NavigableMap<Integer, Integer> offers = OFFER.get(item);
                
                for (var entry : offers.descendingMap().entrySet()){
                    int offerQty = entry.getKey();
                    int offerPrice = entry.getValue();
                    total += (count / offerQty) * offerPrice;
                    count %= offerQty;
                }

                total += count * PRICE.get(item);

            } else {
                total += count * PRICE.get(item);
            }
        }

        return total;
    }
}



