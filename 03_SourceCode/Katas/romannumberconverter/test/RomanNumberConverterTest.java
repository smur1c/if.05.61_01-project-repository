import org.junit.Assert;
import org.junit.Test;

public class RomanNumberConverterTest {
    @Test
    public void itShouldReturnI_Given1() {
        Assert.assertEquals("I", RomanNumberConverter.toRoman(1));
    }

    @Test
    public void itShouldReturnII_Given2() {
        Assert.assertEquals("II", RomanNumberConverter.toRoman(2));
    }

    @Test
    public void itShouldReturnIII_Given3() {
        Assert.assertEquals("III", RomanNumberConverter.toRoman(3));
    }

    @Test
    public void itShouldReturnIV_Given4() {
        Assert.assertEquals("IV", RomanNumberConverter.toRoman(4));
    }

    @Test
    public void itShouldReturnV_Given5() {
        Assert.assertEquals("V", RomanNumberConverter.toRoman(5));
    }

    @Test
    public void itShouldReturnVI_Given6() {
        Assert.assertEquals("VI", RomanNumberConverter.toRoman(6));
    }

    @Test
    public void itShouldReturnVII_Given7() {
        Assert.assertEquals("VII", RomanNumberConverter.toRoman(7));
    }

    @Test
    public void itShouldReturnVIII_Given8() {
        Assert.assertEquals("VIII", RomanNumberConverter.toRoman(8));
    }

    @Test
    public void itShouldReturnIX_Given9() {
        Assert.assertEquals("IX", RomanNumberConverter.toRoman(9));
    }

    @Test
    public void itShouldReturnX_Given10() {
        Assert.assertEquals("X", RomanNumberConverter.toRoman(10));
    }

    @Test
    public void itShouldReturnXX_Given20() {
        Assert.assertEquals("XX", RomanNumberConverter.toRoman(20));
    }

    @Test
    public void itShouldReturn1GivenI() {
        Assert.assertEquals(1, RomanNumberConverter.toArabic("I"));
    }

    @Test
    public void itShouldReturn2GivenII() {
        Assert.assertEquals(2, RomanNumberConverter.toArabic("II"));
    }

    @Test
    public void itShouldReturn3GivenIII() {
        Assert.assertEquals(3, RomanNumberConverter.toArabic("III"));
    }
    @Test
    public void itShouldReturn4GivenIV() {
        Assert.assertEquals(4, RomanNumberConverter.toArabic("IV"));
    }
    @Test
    public void itShouldReturn5GivenV() {
        Assert.assertEquals(5, RomanNumberConverter.toArabic("V"));
    }
    @Test
    public void itShouldReturn6GivenVI() {
        Assert.assertEquals(6, RomanNumberConverter.toArabic("VI"));
    }
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
=======
>>>>>>> Stashed changes
    @Test
    public void itShouldReturn7GivenVII() {
        Assert.assertEquals(7, RomanNumberConverter.toArabic("VII"));
    }
    @Test
    public void itShouldReturn8GivenVIII() {
        Assert.assertEquals(8, RomanNumberConverter.toArabic("VIII"));
    }
    @Test
    public void itShouldReturn9GivenIX() {
        Assert.assertEquals(9, RomanNumberConverter.toArabic("IX"));
    }
    @Test
    public void itShouldReturn10GivenX() {
        Assert.assertEquals(10, RomanNumberConverter.toArabic("X"));
    }
    @Test
    public void itShouldReturn20GivenXX() {
        Assert.assertEquals(20, RomanNumberConverter.toArabic("XX"));
    }

>>>>>>> 38597d8a0d72dcf0e6e0140697a9ef95d21f9e90
}
