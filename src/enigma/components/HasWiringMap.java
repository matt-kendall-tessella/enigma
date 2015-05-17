package enigma.components;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import enigma.model.Letter;

public abstract class HasWiringMap {

    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    protected BiMap<Letter, Letter> wiringMap;

    protected void makeWiringMap(char[] wiringOutputs) {
        wiringMap = HashBiMap.create();
        for (int idx = 0; idx < ALPHABET.length; idx++) {
            wiringMap.put(new Letter(ALPHABET[idx]), new Letter(wiringOutputs[idx]));
        }
    }
}
