package enigma.components;

import com.google.common.collect.BiMap;
import enigma.model.Letter;

public class RotorImpl extends HasWiringMap implements Rotor {

    private final Letter notchPosition;
    private Letter rotorPosition = new Letter('A');
    private Letter ringSetting = new Letter('A');

    public RotorImpl(char[] wiringOutputs, Letter notchPosition) {
        this.notchPosition = notchPosition;
        makeWiringMap(wiringOutputs);
    }

    @Override
    public Letter getNotchPosition() {
        return notchPosition;
    }

    @Override
    public Letter getRotorPosition() {
        return rotorPosition;
    }

    @Override
    public void setRotorPosition(Letter rotorPosition) {
        this.rotorPosition = rotorPosition;
    }

    @Override
    public Letter getRingSetting() {
        return ringSetting;
    }

    @Override
    public void setRingSetting(Letter ringSetting) {
        this.ringSetting = ringSetting;
    }

    @Override
    public Letter encodeRightToLeft(Letter input) {
        return encode(input, false);
    }

    @Override
    public Letter encodeLeftToRight(Letter input) {
        return encode(input, true);
    }

    private Letter encode(Letter input, boolean reverse) {
        int rotorOffset = getRotorOffset();
        int ringOffset = getRingOffset();


        BiMap<Letter, Letter> wiringMapToUse = wiringMap;
        if (reverse) {
            wiringMapToUse = wiringMap.inverse();
        }

        Letter ontoRotor = input.add(rotorOffset - ringOffset);
        Letter fromRotor = wiringMapToUse.get(ontoRotor);
        return fromRotor.add(ringOffset - rotorOffset);
    }

    private int getRingOffset() {
        return ringSetting.getCharacterValue() - new Letter('A').getCharacterValue();
    }

    private int getRotorOffset() {
        return rotorPosition.getCharacterValue() - new Letter('A').getCharacterValue();
    }

    @Override
    public void rotate() {
        rotorPosition = rotorPosition.add(1);
    }

}
