package enigma.components;

import enigma.model.Letter;

public interface Rotor {

    void setRotorPosition(Letter rotorPosition);

    Letter getRotorPosition();

    void setRingSetting(Letter ringSetting);

    Letter getRingSetting();

    Letter encodeRightToLeft(Letter input);

    Letter encodeLeftToRight(Letter input);

    Letter getNotchPosition();

    void move();

}
