package enigma.model;

public class Letter {

    private final Integer characterValue;

    public Letter(Character character) {
        Character upperCase = Character.toUpperCase(character);
        this.characterValue = (int) upperCase - (int) 'A' + 1;
    }

    public Letter(Integer integer) {
        if (integer < 1 || integer > 26) {
            throw new IllegalArgumentException("Integer must be between 1 and 26");
        }
        this.characterValue = integer;
    }

    public Character getCharacter() {
        return (char) (characterValue -1 + (int) 'A');
    }

    public Integer getCharacterValue() {
        return characterValue;
    }

    public Letter add(Integer integer) {
        integer = characterValue + integer;
        while (integer < 1) {
            integer = integer + 26;
        }
        while (integer > 26) {
            integer = integer - 26;
        }
        return new Letter(integer);
    }

    @Override
    public String toString() {
        return "Letter{" + getCharacter() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter = (Letter) o;

        return characterValue.equals(letter.characterValue);
    }

    @Override
    public int hashCode() {
        return characterValue.hashCode();
    }
}
