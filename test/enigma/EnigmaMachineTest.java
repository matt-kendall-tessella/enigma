package enigma;

import enigma.components.Plugboard;
import enigma.components.PlugboardImpl;
import enigma.components.Rotor;
import enigma.components.assemblies.RotorAssembly;
import enigma.components.assemblies.RotorAssemblyImpl;
import enigma.model.Letter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static enigma.components.EnigmaComponents.*;
import static org.junit.Assert.assertEquals;


public class EnigmaMachineTest {

    @Before
    public void setUp() {
        for (Rotor rotor : Arrays.asList(ROTOR_I, ROTOR_II, ROTOR_III, ROTOR_IV, ROTOR_V)) {
            rotor.setRingSetting(new Letter('A'));
            rotor.setRotorPosition(new Letter('A'));
        }
    }

    @Test
    public void testSameLetterNoPlugBoard() throws Exception {
        EnigmaMachine enigma = new EnigmaMachine(REFLECTOR_B, new RotorAssemblyImpl(ROTOR_I, ROTOR_II, ROTOR_III), new PlugboardImpl());
        assertEquals(enigma.typeLetter(new Letter('A')), new Letter('B'));
        assertEquals(enigma.typeLetter(new Letter('A')), new Letter('D'));
        assertEquals(enigma.typeLetter(new Letter('A')), new Letter('Z'));
    }

    @Test
    public void testLettersWithPlugboardConnections() {
        Plugboard plugboard = new PlugboardImpl();
        plugboard.addPair(new Letter('M'), new Letter('F'));
        plugboard.addPair(new Letter('K'), new Letter('O'));
        plugboard.addPair(new Letter('E'), new Letter('L'));
        EnigmaMachine enigma = new EnigmaMachine(REFLECTOR_B, new RotorAssemblyImpl(ROTOR_I, ROTOR_II, ROTOR_III), plugboard);

        assertEquals(enigma.typeLetter(new Letter('K')), new Letter('T'));
        assertEquals(enigma.typeLetter(new Letter('F')), new Letter('Y'));
        assertEquals(enigma.typeLetter(new Letter('C')), new Letter('L'));
    }

    @Test
    public void testRealMessage() {
        RotorAssembly rotorAssembly = new RotorAssemblyImpl(ROTOR_II, ROTOR_IV, ROTOR_V);
        ROTOR_II.setRingSetting(new Letter('B'));
        ROTOR_IV.setRingSetting(new Letter('U'));
        ROTOR_V.setRingSetting(new Letter('L'));

        Plugboard plugboard = new PlugboardImpl();
        plugboard.addPair(new Letter('A'), new Letter('V'));
        plugboard.addPair(new Letter('B'), new Letter('S'));
        plugboard.addPair(new Letter('C'), new Letter('G'));
        plugboard.addPair(new Letter('D'), new Letter('L'));
        plugboard.addPair(new Letter('F'), new Letter('U'));
        plugboard.addPair(new Letter('H'), new Letter('Z'));
        plugboard.addPair(new Letter('I'), new Letter('N'));
        plugboard.addPair(new Letter('K'), new Letter('M'));
        plugboard.addPair(new Letter('O'), new Letter('W'));
        plugboard.addPair(new Letter('R'), new Letter('X'));

        EnigmaMachine enigmaMachine = new EnigmaMachine(REFLECTOR_B, rotorAssembly,plugboard);

        ROTOR_II.setRotorPosition(new Letter('W'));
        ROTOR_IV.setRotorPosition(new Letter('X'));
        ROTOR_V.setRotorPosition(new Letter('C'));

        char[] newKey = decrypt(enigmaMachine, "KCH").toCharArray();

        ROTOR_II.setRotorPosition(new Letter(newKey[0]));
        ROTOR_IV.setRotorPosition(new Letter(newKey[1]));
        ROTOR_V.setRotorPosition(new Letter(newKey[2]));

        String message = ("EDPUD NRGYS ZRCXN\n" +
                "UYTPO MRMBO FKTBZ REZKM\n" +
                "LXLVE FGUEY SIOZV EQMIK\n" +
                "UBPMM YLKLT TDEIS MDICA\n" +
                "GYKUA CTCDO MOHWX MUUIA\n" +
                "UBSTS LRNBZ SZWNR FXWFY\n" +
                "SSXJZ VIJHI DISHP RKLKA\n" +
                "YUPAD TXQSP INQMA TLPIF\n" +
                "SVKDA SCTAC DPBOP VHJK").replaceAll(" ", "").replaceAll("\n", "");
        String expected = ("AUFKL XABTE ILUNG XVONX\n" +
                "KURTI NOWAX KURTI NOWAX\n" +
                "NORDW ESTLX SEBEZ XSEBE\n" +
                "ZXUAF FLIEG ERSTR ASZER\n" +
                "IQTUN GXDUB ROWKI XDUBR\n" +
                "OWKIX OPOTS CHKAX OPOTS\n" +
                "CHKAX UMXEI NSAQT DREIN\n" +
                "ULLXU HRANG ETRET ENXAN\n" +
                "GRIFF XINFX RGTX").replaceAll(" ", "").replaceAll("\n", "");

        String decrypted = decrypt(enigmaMachine, message);
        assertEquals(decrypted, expected);
    }

    private String decrypt(EnigmaMachine machine, String message) {
        String decrypted = "";
        for (char character : message.toCharArray()) {
            Letter output = machine.typeLetter(new Letter(character));
            decrypted += output.getCharacter();
        }
        return decrypted;
    }
}