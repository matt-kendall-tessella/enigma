package enigma.components;

import enigma.model.Letter;

public class ReflectorImpl extends HasWiringMap implements Reflector {

    public ReflectorImpl(char[] wiringOutputs) {
        makeWiringMap(wiringOutputs);
    }

    @Override
    public Letter reflect(Letter input) {
        return wiringMap.get(input);
    }
}
