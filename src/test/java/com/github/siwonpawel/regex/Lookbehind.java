package com.github.siwonpawel.regex;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class Lookbehind {

    /*
        (?<=criteria)

        Asserts that what immediately precedes the current position in the string is criteria.
     */
    @Test
    public void positiveLookbehind() {
        var pattern = Pattern.compile(".*(?<=jupiter).*assertEquals;");

        var matcher = pattern.matcher("import static org.junit.jupiter.api.Assertions.assertEquals;");

        assertTrue(matcher.find());
        assertEquals("import static org.junit.jupiter.api.Assertions.assertEquals;", matcher.group());

        assertFalse(pattern.matcher("import static org.junit.Assert.assertEquals;").find());
    }


    public static void main(String[] args) {

    }
    /*
        (?<!criteria)

        Asserts that what immediately precedes the current position in the string is not foo.
     */
    @Test
    public void negativeLookbehind() {
        var pattern = Pattern.compile(".*(?<!jupiter.{0,30})assertEquals;");

        var matcher = pattern.matcher("import static org.junit.Assert.assertEquals;");
        assertTrue(matcher.find());
        assertEquals("import static org.junit.Assert.assertEquals;", matcher.group());

        assertFalse(pattern.matcher("import static org.junit.jupiter.api.Assertions.assertEquals;").find());
    }


}
