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
    void shouldComposeFuntions() {
        //given
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, String> toString = String::valueOf;
        var compose = Function.compose(toString, triple);

        //when
        var result = compose.apply(10);

        //then
        Assertions.assertEquals("30", result);
    }

}