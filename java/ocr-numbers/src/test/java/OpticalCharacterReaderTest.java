import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public final class OpticalCharacterReaderTest {

    /*
     * See https://github.com/junit-team/junit4/wiki/Rules for information on JUnit Rules in general and
     * ExpectedExceptions in particular.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testReaderRecognizesSingle0() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                "| |",
                "|_|",
                "   "
        ));

        assertEquals("0", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle1() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                "   ",
                "  |",
                "  |",
                "   "
        ));

        assertEquals("1", parsedInput);
    }

    
    @Test
    public void testReaderReturnsQuestionMarkForUnreadableButCorrectlySizedInput() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                "   ",
                "  _",
                "  |",
                "   "
        ));

        assertEquals("?", parsedInput);
    }

    
    @Test
    public void testReaderThrowsExceptionWhenNumberOfInputLinesIsNotAMultipleOf4() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Number of input rows must be a positive multiple of 4");

        new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                "| |",
                "   "
        ));
    }

    
    @Test
    public void testReaderThrowsExceptionWhenNumberOfInputColumnsIsNotAMultipleOf3() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Number of input columns must be a positive multiple of 3");

        new OpticalCharacterReader().parse(Arrays.asList(
                "    ",
                "   |",
                "   |",
                "    "
        ));
    }

    
    @Test
    public void testReaderRecognizesBinarySequence110101100() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                "       _     _        _  _ ",
                "  |  || |  || |  |  || || |",
                "  |  ||_|  ||_|  |  ||_||_|",
                "                           "
        ));

        assertEquals("110101100", parsedInput);
    }

    
    @Test
    public void testReaderReplacesUnreadableDigitsWithQuestionMarksWithinSequence() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                "       _     _           _ ",
                "  |  || |  || |     || || |",
                "  |  | _|  ||_|  |  ||_||_|",
                "                           "
        ));

        assertEquals("11?10?1?0", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle2() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                " _|",
                "|_ ",
                "   "
        ));

        assertEquals("2", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle3() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                " _|",
                " _|",
                "   "
        ));

        assertEquals("3", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle4() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                "   ",
                "|_|",
                "  |",
                "   "
        ));

        assertEquals("4", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle5() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                "|_ ",
                " _|",
                "   "
        ));

        assertEquals("5", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle6() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                "|_ ",
                "|_|",
                "   "
        ));

        assertEquals("6", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle7() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                "  |",
                "  |",
                "   "
        ));

        assertEquals("7", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle8() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                "|_|",
                "|_|",
                "   "
        ));

        assertEquals("8", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSingle9() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                " _ ",
                "|_|",
                " _|",
                "   "
        ));

        assertEquals("9", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesSequence1234567890() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                "    _  _     _  _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|| |",
                "  ||_  _|  | _||_|  ||_| _||_|",
                "                              "
        ));

        assertEquals("1234567890", parsedInput);
    }

    
    @Test
    public void testReaderRecognizesAndCorrectlyFormatsMultiRowInput() {
        String parsedInput = new OpticalCharacterReader().parse(Arrays.asList(
                "    _  _ ",
                "  | _| _|",
                "  ||_  _|",
                "         ",
                "    _  _ ",
                "|_||_ |_ ",
                "  | _||_|",
                "         ",
                " _  _  _ ",
                "  ||_||_|",
                "  ||_| _|",
                "         "
        ));

        assertEquals("123,456,789", parsedInput);
    }

}
