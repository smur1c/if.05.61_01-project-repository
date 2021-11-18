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
}
