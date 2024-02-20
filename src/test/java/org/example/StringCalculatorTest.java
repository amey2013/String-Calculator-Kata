package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringCalculatorTest {
    String ans = "";
    StringCalculator stringCalculator = new StringCalculator();

    @BeforeEach
    void startup() {
        ans = "";
    }

    @Test
    void isEmptyString() {
        ans = stringCalculator.add("");
        assertEquals("0", ans);
    }

    @Test
    void addWithCommas() {
        ans = stringCalculator.add("1,2,3");
        assertEquals("6", ans);
    }

    @Test
    void addWithCustomDelimiterSemicolon() {
        ans = stringCalculator.add("//;\n1;2");
        assertEquals("3", ans);
    }

    @Test
    void addWithCustomDelimiterPipe() {
        ans = stringCalculator.add("//|\n1|2|3");
        assertEquals("6", ans);
    }

    @Test
    void addWithCustomDelimiterSep() {
        ans = stringCalculator.add("//sep\n2sep3");
        assertEquals("5", ans);
    }

    @Test
    void addWithMixedDelimiter() {
        ans = stringCalculator.add("//|\n1|2,3");
        assertEquals("'|' expected but ',' found at position 3.", ans);
    }

    @Test
    void addSingleNumber() {
        ans = stringCalculator.add("1");
        assertEquals("1", ans);
    }

    @Test
    void addDecimalNumbers() {
        ans = stringCalculator.add("1.1,2.2");
        assertEquals("3.3", ans);
    }

    @Test
    void addWithNewLineDelimiter() {
        ans = stringCalculator.add("1\n2,3");
        assertEquals("6", ans);
    }

    @Test
    void addWithEndingComma() {
        ans = stringCalculator.add("175.2,\n35");
        assertEquals("Number expected but '\\n' found at position 6.", ans);
    }

    @Test
    void addWithTrailingComma() {
        ans = stringCalculator.add("1,3,");
        assertEquals("Number expected but EOF found.", ans);
    }

    @Test
    void addWithNegativeNumbers() {
        ans = stringCalculator.add("-1,2");
        assertEquals("Negative not allowed : -1.0", ans);
    }

    @Test
    void addWithMultipleNegativeNumbers() {
        ans = stringCalculator.add("2,-4,-5");
        assertEquals("Negative not allowed : -4.0 ,-5.0", ans);
    }

    @Test
    void checkTheMultipleError() {
        boolean result = stringCalculator.multipleErrorChecking("-1,,-2");
        assertTrue(result);
    }


}
