package enigma.components;

import enigma.model.Letter;

public final class EnigmaComponents {

    private EnigmaComponents() {}

    public static final Rotor ROTOR_I = new RotorImpl("EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray(), new Letter('Q'));

    public static final Rotor ROTOR_II = new RotorImpl("AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray(), new Letter('E'));

    public static final Rotor ROTOR_III = new RotorImpl("BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray(), new Letter('V'));

    public static final Rotor ROTOR_IV = new RotorImpl("ESOVPZJAYQUIRHXLNFTGKDCMWB".toCharArray(), new Letter('J'));

    public static final Rotor ROTOR_V = new RotorImpl("VZBRGITYUPSDNHLXAWMJQOFECK".toCharArray(), new Letter('Z'));

    public static final Reflector REFLECTOR_B = new ReflectorImpl("YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray());

}
