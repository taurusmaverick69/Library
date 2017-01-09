package com.maverick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashaRunner {
    private static int[][] ringArray = {
            {0, 1},
            {1, 0},
            {2, 0},
            {3, 0},
            {4, 0},
            {5, 0},
            {6, 1},
            {7, 0},
            {8, 2},
            {9, 1},
    };

    private static int[] numberToDigits(int number) {
        List<Integer> integers = new ArrayList<>();
        while (number > 0) {
            integers.add(number % 10);
            number /= 10;
        }
        int[] digits = new int[integers.size()];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = integers.get(i);
        }
        return digits;
    }

    private static int sumOfRings(int[] digits) {
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < ringArray.length; j++) {
                if (digits[i] == ringArray[j][0]) {
                    sum += ringArray[j][1];
                }
            }
        }
        return sum;
    }

    private static void sort(int[] numbers) {
        int temp;

        for (int i = 0; i < numbers.length; i++) {

            for (int j = 0; j < numbers.length - 1; j++) {

                int sum = sumOfRings(numberToDigits(numbers[j]));
                int sumNext = sumOfRings(numberToDigits(numbers[j + 1]));

                if (sum > sumNext) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }

                if (sum == sumNext) {
                    if (numbers[j] > numbers[j + 1]) {
                        temp = numbers[j];
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {
                10, 4, 120, 98, 13, 100, 5, 61
        };
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
