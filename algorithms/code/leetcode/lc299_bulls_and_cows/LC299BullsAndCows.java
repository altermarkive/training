package leetcode.lc299_bulls_and_cows;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/bulls-and-cows/
 * #medium
 */
public final class LC299BullsAndCows {
    public String getHint(final String secret, final String guess) {
        HashMap<Character, Integer> countKnown = new HashMap<>();
        HashMap<Character, Integer> countAsked = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        int count;
        for (int i = 0; i < Math.min(secret.length(), guess.length()); i++) {
            // Count characters of each type
            char known = secret.charAt(i);
            char asked = guess.charAt(i);
            count = 0;
            if (countKnown.containsKey(known)) {
                count = countKnown.get(known);
            }
            countKnown.put(known, count + 1);
            count = 0;
            if (countAsked.containsKey(asked)) {
                count = countAsked.get(asked);
            }
            countAsked.put(asked, count + 1);
            // Check for a bull
            if (known == asked) {
                bulls++;
            }
        }
        // Count the cows
        for (char asked : countAsked.keySet()) {
            if (countKnown.containsKey(asked)) {
                cows += Math.min(countKnown.get(asked), countAsked.get(asked));
            }
        }
        // Remove the bulls from the cows
        cows -= bulls;
        return "" + bulls + "A" + cows + "B";
        // It would have been faster to have one lookup table and update cows up or down
        // accordingly
    }
}