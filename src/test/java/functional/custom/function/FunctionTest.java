package functional.custom.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FunctionTest {

    @Test
    void shouldApplyOperationOnArgument() {
        //given
        Function<Integer, Integer> triple = x -> x * 3;

        //when
        var result = triple.apply(10);

        //then
        Assertions.assertEquals(30, result);
    }

    @Test
    void shouldComposeFunctions() {
        //given
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, String> toString = String::valueOf;
        var compose = Function.compose(toString, triple);

        //when
        var result = compose.apply(10);

        //then
        Assertions.assertEquals("30", result);
    }

    @Test
    void ex_2_3_writeFunctionToAddTwoNumbers() {
        //given
        Function<Integer, Function<Integer, Integer>> add = y -> x -> x + y;

        //when
        var result = add.apply(4).apply(5);

        //then
        Assertions.assertEquals(9, result);
    }

    @Test
    void ex_2_4_writeFunctionComposedOfSquareAndTriple() {
        //given
        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> triple = x -> x * x * x;
        Function<Integer, Integer> compose = x -> Function.compose(square, triple).apply(x);

        //when
        var result = compose.apply(2);

        //then
        Assertions.assertEquals(64, result);
    }

    @Test
    void ex_2_5_higherCompose() {
        //given
        Function<Integer, Integer> twice = x -> 2 * x;
        Function<Integer, String> toText = String::valueOf;

        //when
        var result = Function.<Integer, Integer, String>higherCompose()
                .apply(toText)
                .apply(twice)
                .apply(2);

        //then
        Assertions.assertEquals("4", result);
    }

    @Test
    void ex_2_6_higherAndThen() {
        //given
        Function<Integer, Integer> twice = x -> 2 * x;
        Function<Integer, String> toText = String::valueOf;

        //
        var result = Function.<Integer, Integer, String>higherAndThen()
                .apply(twice)
                .apply(toText)
                .apply(2);

        //
        Assertions.assertEquals("4", result);
    }

    @Test
    void ex_2_7_partialFunction() {
        //given
        var stringify = Function.<Double, Double, String>higherCompose()
                .apply(String::valueOf);

        //when
        var result = stringify
                .apply(x -> x * 0.23)
                .apply(10.0);

        //then
        Assertions.assertEquals(2.3F, Float.parseFloat(result), 0.0001);
    }

    @Test
    void ex_2_8_partialBFunction() {
        //given
        Function<Float, Function<Integer, String>> doubled = m -> i -> String.valueOf(m.intValue() * 2 + i);

        //when 2 * 2 + 10 -> toString
        var result = Function.partialB(10, doubled)
                .apply(2F);

        //then
        Assertions.assertEquals("14", result);
    }

    @Test
    void ex_2_9_stringFormatter() {
        //when
        var result = Function.fourFormatter().apply("first")
                .apply("second")
                .apply("third")
                .apply("fourth");

        //then
        Assertions.assertEquals("first second third fourth", result);
    }

    @Test
    void ex_2_10_tupleResolver() {
        //given
        Function<Tuple<Integer, Integer>, String> convert = tuple -> String.valueOf(tuple.getLeft() + tuple.getRight());

        //when
        var result = Function.tupleResolver(convert).apply(10).apply(5);

        //then
        Assertions.assertEquals("15", result);
    }

    @Test
    void ex_2_11_reverseArguments() {
        //given
        Function<String, Function<String, Integer>> adder = s1 -> s2 -> Integer.parseInt(s1) + Integer.parseInt(s2);

        //when
        var result = Function.reverseArgs(adder).apply("1").apply("1");

        //then
        Assertions.assertEquals(2, result);
    }

    @Test
    void ex_2_12_factorial() {
        //when
        var result = Function.factorial().apply(5);

        //then
        Assertions.assertEquals(120, result);
    }

}