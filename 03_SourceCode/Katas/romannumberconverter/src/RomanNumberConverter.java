public class RomanNumberConverter {
    public static void main(String[] args) {

    }

    public static String toRoman(int arabicNumber) {
        StringBuilder romanNumber = new StringBuilder();

        for(Numerals numeral : Numerals.values()) {
            while (arabicNumber >= numeral.arabic) {
                romanNumber.append(numeral.roman);
                arabicNumber -= numeral.arabic;
            }
        }

        return romanNumber.toString();
    }

    public static int toArabic(String roman) {
        int values = roman.length();
        for(Numerals numeral : Numerals.values()) {
            if(numeral.roman == roman){
                values = numeral.arabic;
            }
        }
        return values;
    }

    enum Numerals {
        TEN(10, "X"),
        NINE(9, "IX"),
        FIVE(5, "V"),
        SIX(6, "VI"),
        FOUR(4, "IV"),
        ONE(1, "I");

        private final int arabic;
        private final String roman;

        Numerals(int arabic, String roman) {
            this.arabic = arabic;
            this.roman = roman;
        }
    }
}
