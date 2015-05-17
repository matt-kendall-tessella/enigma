package enigma.components.assemblies;

import enigma.model.Letter;
import org.junit.Before;
import org.junit.Test;

import static enigma.components.EnigmaComponents.*;
import static org.junit.Assert.assertEquals;


public class RotorAssemblyImplTest {

    private RotorAssembly rotorAssembly;

    @Before
    public void setUp() {
        defaultSettings();
        rotorAssembly = new RotorAssemblyImpl(ROTOR_I, ROTOR_II, ROTOR_III);
    }

    private void defaultSettings() {
        ROTOR_I.setRotorPosition(new Letter('A'));
        ROTOR_I.setRingSetting(new Letter('A'));
        ROTOR_II.setRotorPosition(new Letter('A'));
        ROTOR_II.setRingSetting(new Letter('A'));
        ROTOR_III.setRotorPosition(new Letter('A'));
        ROTOR_III.setRingSetting(new Letter('A'));
    }

    private void customSettings() {
        ROTOR_I.setRingSetting(new Letter('X'));
        ROTOR_I.setRotorPosition(new Letter('G'));
        ROTOR_II.setRingSetting(new Letter('Y'));
        ROTOR_II.setRotorPosition(new Letter('H'));
        ROTOR_III.setRingSetting(new Letter('Z'));
        ROTOR_III.setRotorPosition(new Letter('I'));
    }

    @Test
    public void testEncodeRightToLeft() {
        assertEquals(rotorAssembly.encodeRightToLeft(new Letter('A')), new Letter('Z'));
    }

    @Test
    public void testEncodeLeftToRight() {
        assertEquals(rotorAssembly.encodeLeftToRight(new Letter('T')), new Letter('U'));
    }

    @Test
    public void testEncodeRightToLeftWithRingSettings() {
        customSettings();
        assertEquals(rotorAssembly.encodeRightToLeft(new Letter('U')), new Letter('R'));
    }

    @Test
    public void testEncodeLeftToRightWithRingSettings() {
        customSettings();
        assertEquals(rotorAssembly.encodeLeftToRight(new Letter('B')), new Letter('V'));
    }

    @Test
    public void testRotateMovesFastRotor() {
        rotorAssembly.rotate();

        assertEquals(ROTOR_III.getRotorPosition(), new Letter('B'));
    }

    @Test
    public void testFastRotorKicksMiddleRotor() {
        Letter fastRotatePosition = ROTOR_III.getNotchPosition();
        ROTOR_III.setRotorPosition(fastRotatePosition);
        assertEquals(ROTOR_II.getRotorPosition(), new Letter('A'));

        rotorAssembly.rotate();

        assertEquals(ROTOR_II.getRotorPosition(), new Letter('B'));
        assertEquals(ROTOR_III.getRotorPosition(), fastRotatePosition.add(1));
    }

    @Test
    public void testMiddleRotorDoubleStep() {
        Letter middleRotatePosition = ROTOR_II.getNotchPosition();
        ROTOR_II.setRotorPosition(middleRotatePosition);
        assertEquals(ROTOR_III.getRotorPosition(), new Letter('A'));

        rotorAssembly.rotate();

        assertEquals(ROTOR_II.getRotorPosition(), middleRotatePosition.add(1));
        assertEquals(ROTOR_III.getRotorPosition(), new Letter('B'));
    }

    @Test
    public void testMiddleRotorKicksSlowRotor() {
        Letter middleRotatePosition = ROTOR_II.getNotchPosition();
        ROTOR_II.setRotorPosition(middleRotatePosition);
        assertEquals(ROTOR_I.getRotorPosition(), new Letter('A'));

        rotorAssembly.rotate();

        assertEquals(ROTOR_I.getRotorPosition(), new Letter('B'));
    }

    @Test
    public void testWhenFastAndMiddleAtNotchMiddleOnlyRotatesOnce() {
        Letter middleRotatePosition = ROTOR_II.getNotchPosition();
        ROTOR_II.setRotorPosition(middleRotatePosition);
        Letter fastRotatePosition = ROTOR_III.getNotchPosition();
        ROTOR_III.setRotorPosition(fastRotatePosition);

        rotorAssembly.rotate();

        assertEquals(ROTOR_III.getRotorPosition(), fastRotatePosition.add(1));
        assertEquals(ROTOR_II.getRotorPosition(), middleRotatePosition.add(1));
        assertEquals(ROTOR_I.getRotorPosition(), new Letter('B'));

    }


    @Test
    public void testRotateSequence() {
        // Example from https://math.dartmouth.edu/~jvoight/Fa2012-295/EnigmaSimManual.pdf
        rotorAssembly = new RotorAssemblyImpl(ROTOR_III, ROTOR_II, ROTOR_I);

        String[] sequence = new String[] {"KDO", "KDP", "KDQ", "KER", "LFS", "LFT", "LFU"};

        ROTOR_III.setRotorPosition(new Letter('K'));
        ROTOR_II.setRotorPosition(new Letter('D'));
        ROTOR_I.setRotorPosition(new Letter('O'));

        for (String chars : sequence) {
            assertEquals(ROTOR_III.getRotorPosition(), new Letter(chars.toCharArray()[0]));
            assertEquals(ROTOR_II.getRotorPosition(), new Letter(chars.toCharArray()[1]));
            assertEquals(ROTOR_I.getRotorPosition(), new Letter(chars.toCharArray()[2]));
            rotorAssembly.rotate();
        }
    }
}