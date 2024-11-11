package com.epam.jmp.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @ParameterizedTest
    @MethodSource("source")
    public void test(int[] input, int expected) {
        //given
        //method source

        //when
        int actual = 0;

        //then
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(new int[]{}, 0),
                Arguments.of(new int[]{}, 0));
    }
}
