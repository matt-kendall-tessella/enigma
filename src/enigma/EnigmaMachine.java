package enigma;

import enigma.components.Plugboard;
import enigma.components.Reflector;
import enigma.components.assemblies.RotorAssembly;
import enigma.model.Letter;

public class EnigmaMachine {

    private final Reflector reflector;
    private final RotorAssembly rotorAssembly;
    private final Plugboard plugboard;

    public EnigmaMachine(Reflector reflector, RotorAssembly rotorAssembly, Plugboard plugboard) {
        this.reflector = reflector;
        this.rotorAssembly = rotorAssembly;
        this.plugboard = plugboard;
    }

    public Letter typeLetter(Letter input) {
        rotorAssembly.rotate();
        Letter fromPlugboard = plugboard.encode(input);
        Letter toReflector = rotorAssembly.encodeRightToLeft(fromPlugboard);
        Letter fromReflector = reflector.reflect(toReflector);
        Letter toPlugBoard = rotorAssembly.encodeLeftToRight(fromReflector);
        return plugboard.encode(toPlugBoard);
    }

}
