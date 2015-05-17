package enigma.components.assemblies;

import enigma.model.Letter;

public interface RotorAssembly {

    Letter encodeRightToLeft(Letter input);

    Letter encodeLeftToRight(Letter input);

    void rotate();

}
