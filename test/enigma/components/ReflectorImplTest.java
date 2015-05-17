package enigma.components;

import enigma.model.Letter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ReflectorImplTest {

    private Reflector reflector;

    @Before
    public void setUp() {
        reflector = new ReflectorImpl("YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray());
    }

    @Test
    public void testReflect() throws Exception {
        assertEquals(reflector.reflect(new Letter('A')), new Letter('Y'));
        assertEquals(reflector.reflect(new Letter('G')), new Letter('L'));
        assertEquals(reflector.reflect(new Letter('Z')), new Letter('T'));
    }

    @Test
    public void testReflectSymmetry() {
        for (char character : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            Letter input = new Letter(character);
            Letter output = reflector.reflect(input);
            assertEquals(reflector.reflect(output), input);
        }
    }
}