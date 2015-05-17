package enigma.components;

import enigma.model.Letter;

public interface Plugboard {

    void addPair(Letter letter1, Letter letter2);

    void clearPairs();

    Letter encode(Letter input);

}
