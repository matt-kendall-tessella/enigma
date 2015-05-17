package enigma.components;

import enigma.model.Letter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PlugboardImplTest {

    private Plugboard plugboard;

    @Before
    public void setUp() {
        plugboard = new PlugboardImpl();
    }

    @Test
    public void testUnMappedLetterEncodesToSelf() throws Exception {
        assertEquals(plugboard.encode(new Letter('A')), new Letter('A'));
    }

    @Test
    public void testMappedLettersMatchEachOther() {
        plugboard.addPair(new Letter('M'), new Letter('X'));

        assertEquals(plugboard.encode(new Letter('M')), new Letter('X'));
        assertEquals(plugboard.encode(new Letter('X')), new Letter('M'));
    }

    @Test
    public void testCanReplaceExistingPairMapping() {
        plugboard.addPair(new Letter('M'), new Letter('X'));
        plugboard.addPair(new Letter('M'), new Letter('Y'));
        // New mapping works
        assertEquals(plugboard.encode(new Letter('M')), new Letter('Y'));
        assertEquals(plugboard.encode(new Letter('Y')), new Letter('M'));
        // Original mapping broken
        assertEquals(plugboard.encode(new Letter('X')), new Letter('X'));

        plugboard.addPair(new Letter('Y'), new Letter('F'));
        // New mapping works
        assertEquals(plugboard.encode(new Letter('F')), new Letter('Y'));
        assertEquals(plugboard.encode(new Letter('Y')), new Letter('F'));

        assertEquals(plugboard.encode(new Letter('M')), new Letter('M'));
    }

    @Test
    public void testCanClear() {
        plugboard.addPair(new Letter('A'), new Letter('B'));
        plugboard.clearPairs();

        assertEquals(plugboard.encode(new Letter('A')), new Letter('A'));
        assertEquals(plugboard.encode(new Letter('B')), new Letter('B'));

    }
}