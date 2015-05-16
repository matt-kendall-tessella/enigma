package enigma.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

public class LetterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void canCreateLetterFromValidInteger() {
        new Letter(1);
        new Letter(26);
    }

    @Test
    public void cantCreateLetterFromIntegerLessThanOne() {
        exception.expect(IllegalArgumentException.class);
        new Letter(0);
    }

    @Test
    public void cantCreateLetterFromIntegerGreaterThan26() {
        exception.expect(IllegalArgumentException.class);
        new Letter(27);
    }

    @Test
    public void canCreateLetterFromUppercaseCharacter() {
        new Letter('Y');
    }

    @Test
    public void canCreateCharacterFromLowercaseCharacter() {
        assertEquals(new Letter('k'), new Letter('K'));
    }

    @Test
    public void testLetterEquals() {
        assertEquals(new Letter('A'), new Letter(1));
    }

    @Test
    public void testCanGetCharacterValue() {
        Letter a = new Letter('A');
        assertEquals(a.getCharacterValue(), new Integer(1));
        Letter z = new Letter('Z');
        assertEquals(z.getCharacterValue(), new Integer(26));
    }

    @Test
    public void testCanGetCharacter() {
        Letter a = new Letter(2);
        assertEquals(a.getCharacter(), new Character('B'));
        Letter y = new Letter(25);
        assertEquals(y.getCharacter(), new Character('Y'));
        Letter z = new Letter(26);
        assertEquals(z.getCharacter(), new Character('Z'));

    }

    @Test
    public void canAdd() {
        Letter a = new Letter('L');
        assertEquals(a.add(3), new Letter('O'));
    }


    @Test
    public void canAddWithPositiveRollOver() {
        Letter y = new Letter('Y');
        assertEquals(y.add(5), new Letter('D'));
        assertEquals(y.add(1), new Letter('Z'));

        Letter g = new Letter('G');
        assertEquals(g.add(62), new Letter('Q'));
    }

    @Test
    public void canAddWithNegativeRollOver() {
        Letter y = new Letter('Y');
        assertEquals(y.add(-2), new Letter('W'));

        Letter b = new Letter('B');
        assertEquals(b.add(-30), new Letter('X'));
    }

    @Test
    public void testToString() {
        assertEquals(new Letter('D').toString(), "Letter{D}");
    }
}