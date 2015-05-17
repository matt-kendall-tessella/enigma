package enigma.components.assemblies;

import enigma.components.Rotor;
import enigma.model.Letter;

public class RotorAssemblyImpl implements RotorAssembly {

    private final Rotor slowRotor;
    private final Rotor middleRotor;
    private final Rotor fastRotor;

    public RotorAssemblyImpl(Rotor slow, Rotor middle, Rotor fast) {
        this.slowRotor = slow;
        this.middleRotor = middle;
        this.fastRotor = fast;
    }

    @Override
    public Letter encodeRightToLeft(Letter input) {
        Letter output = fastRotor.encodeRightToLeft(input);
        output = middleRotor.encodeRightToLeft(output);
        return slowRotor.encodeRightToLeft(output);
    }

    @Override
    public Letter encodeLeftToRight(Letter input) {
        Letter output = slowRotor.encodeLeftToRight(input);
        output = middleRotor.encodeLeftToRight(output);
        return fastRotor.encodeLeftToRight(output);
    }

    @Override
    public void rotate() {
        boolean middleAtNotch = middleRotor.getRotorPosition().equals(middleRotor.getNotchPosition());
        boolean fastAtNotch = fastRotor.getRotorPosition().equals(fastRotor.getNotchPosition());
        
        if (middleAtNotch || fastAtNotch) {
            if (middleAtNotch) {
                slowRotor.rotate();
            }
            middleRotor.rotate();
        }
        fastRotor.rotate();
    }
}
