package enigma.components;

import enigma.model.Letter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RotorImplTest {

    private Rotor rotor;

    @Before
    public void setUp() {
        rotor = new RotorImpl("EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray(), new Letter('Q'));
    }

    @Test
    public void testEncode() throws Exception {
        // Test that for setting A and ringsetting 1 the correct letters received:
        assertEquals(rotor.encodeRightToLeft(new Letter('A')), new Letter('E'));
        assertEquals(rotor.encodeRightToLeft(new Letter('B')), new Letter('K'));
        assertEquals(rotor.encodeRightToLeft(new Letter('C')), new Letter('M'));
        assertEquals(rotor.encodeRightToLeft(new Letter('X')), new Letter('R'));
        assertEquals(rotor.encodeRightToLeft(new Letter('Y')), new Letter('C'));
        assertEquals(rotor.encodeRightToLeft(new Letter('Z')), new Letter('J'));
    }

    @Test
    public void testEncodeAfterMove() throws Exception {
        rotor.move();
        assertEquals(rotor.encodeRightToLeft(new Letter('A')), new Letter('J'));
        assertEquals(rotor.encodeRightToLeft(new Letter('C')), new Letter('E'));
        assertEquals(rotor.encodeRightToLeft(new Letter('Z')), new Letter('D'));
    }

    @Test
    public void testEncodeForRotorPositionAndRingSetting() {
        rotor.setRingSetting(new Letter('B'));
        rotor.setRotorPosition(new Letter('A'));

        assertEquals(rotor.encodeRightToLeft(new Letter('A')), new Letter('K'));

        rotor.setRingSetting(new Letter('F'));
        rotor.setRotorPosition(new Letter('Y'));
        assertEquals(rotor.encodeRightToLeft(new Letter('A')), new Letter('W'));
    }

    @Test
    public void testReverseEncode() {
        assertEquals(rotor.encodeLeftToRight(new Letter('L')), new Letter('E'));
    }

    @Test
    public void testReverseEncodeAfterMove() {
        rotor.move();
        assertEquals(rotor.encodeLeftToRight(new Letter('J')), new Letter('A'));
    }

    @Test
    public void testReverseEncodeForRotorPositionAndRingSetting() {
        rotor.setRingSetting(new Letter('B'));
        rotor.setRotorPosition(new Letter('A'));

        assertEquals(rotor.encodeLeftToRight(new Letter('K')), new Letter('A'));

        rotor.setRingSetting(new Letter('F'));
        rotor.setRotorPosition(new Letter('Y'));
        assertEquals(rotor.encodeLeftToRight(new Letter('W')), new Letter('A'));
    }
}