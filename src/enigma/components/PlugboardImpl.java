package enigma.components;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import enigma.model.Letter;

public class PlugboardImpl implements Plugboard {

    BiMap<Letter, Letter> wiringMap;

    public PlugboardImpl() {
        wiringMap = HashBiMap.create();
    }

    @Override
    public void addPair(Letter letter1, Letter letter2) {
        // Check that letter1 is not being used already
        if (wiringMap.inverse().containsKey(letter1)) {
            wiringMap.inverse().remove(letter1);
        }
        wiringMap.put(letter1, letter2);

    }

    @Override
    public void clearPairs() {
        wiringMap = HashBiMap.create();
    }

    @Override
    public Letter encode(Letter input) {
        if (wiringMap.containsKey(input)) {
            return wiringMap.get(input);
        } else if (wiringMap.inverse().containsKey(input)) {
            return wiringMap.inverse().get(input);
        } else {
            return input;
        }
    }
}
