package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private static final Map<Character, Integer> PRICE = Map.of (
        'A', 50,
        'B', 30,
        'C', 20,
        'D', 15,
        'E', 40
    );

    private static final Map<Character, int[]> OFFER = Map.of(
        // Item : [quantity for offer, offer price]
        'A', new int[] {3, 130},
        'B', new int[] {2, 45}
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

        int total = 0;

        // calculate price and offer
        for (var pair : itemCount.entrySet()){
            char item = pair.getKey();
            int count = pair.getValue();

            if (OFFER.containsKey(item)){
                int[] offer = OFFER.get(item);
                int offerQty = offer[0];
                int offerPrice = offer[1];

                total += (count / offerQty) * offerPrice;
                total += (count % offerQty) * PRICE.get(item);
            } else {
                total += count * PRICE.get(item);
            }
        }

        return total;
    }
}

