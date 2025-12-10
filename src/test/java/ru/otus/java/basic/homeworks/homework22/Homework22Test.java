package ru.otus.java.basic.homeworks.homework22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Homework22Test {
    Homework22 homework22;

    public static Stream<Arguments> createArrayAfterLastUnit() {
        List<Arguments> arrayList = new ArrayList<>();
        arrayList.add(Arguments.of(new int[]{}, new int[]{1, 2, 1, 2, 1}));
        arrayList.add(Arguments.of(new int[]{2, 3, 5, 7}, new int[]{1, 2, 3, 5, 7}));
        arrayList.add(Arguments.of(new int[]{2, 2}, new int[]{1, 2, 1, 2, 2}));
        return arrayList.stream();
    }

    public static Stream<Arguments> createArrayCheckOneAndTwo() {
        return Stream.of(
                Arguments.of(true, new int[]{1, 2, 1, 2, 1}),
                Arguments.of(false, new int[]{1, 2, 3}),
                Arguments.of(false, new int[]{1, 1}),
                Arguments.of(false, new int[]{2, 2}),
                Arguments.of(true, new int[]{1, 2}),
                Arguments.of(false, new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("createArrayCheckOneAndTwo")
    void checkOneAndTwo(boolean expected, int[] array) {
        assertEquals(expected, homework22.checkOneAndTwo(array));
    }

    @ParameterizedTest
    @MethodSource("createArrayAfterLastUnit")
    void afterLastUnit(int[] expected, int[] array) {
        assertArrayEquals(expected, homework22.afterLastUnit(array));
    }

    @Test
    void afterLastUnit_ThrowsException() {
        assertThrows(RuntimeException.class, () -> homework22.afterLastUnit(new int[]{2, 3, 4}));
        assertThrows(RuntimeException.class, () -> homework22.afterLastUnit(new int[]{}));
    }
}