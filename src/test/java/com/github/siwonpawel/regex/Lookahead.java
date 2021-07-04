package com.github.siwonpawel.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class Lookahead {

    /*
    (?=criteria)

    Asserts that what immediately follows the current position in the string is equal to criteria.
    */

    @Test
    public void positiveLookahead() {
        var pattern = Pattern.compile("import (?=static).+");

        var matcher = pattern.matcher("import static org.junit.jupiter.api.Assertions.assertEquals;");

        assertTrue(matcher.find());
        assertEquals("import static org.junit.jupiter.api.Assertions.assertEquals;", matcher.group());
        assertFalse(pattern.matcher("import java.util.regex.Matcher;").find());
    }

    /*

    (?!criteria)

    Asserts that what immediately follows the current position in the string is not criteria.
     */

    @Test
    public void negativeLookahead() {
        var pattern = Pattern.compile("import (?!static).+");


        var matcher = pattern.matcher("import java.util.regex.Matcher;");

        assertTrue(matcher.find());
        assertEquals("import java.util.regex.Matcher;", matcher.group());

        assertFalse(pattern.matcher("import static org.junit.jupiter.api.Assertions.assertEquals;").find());
    }

}
