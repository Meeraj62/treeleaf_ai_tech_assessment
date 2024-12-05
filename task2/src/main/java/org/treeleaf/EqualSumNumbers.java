package org.treeleaf;

import java.util.ArrayList;
import java.util.List;

public class EqualSumNumbers {
    public static void main(String[] args) {
        for (int n = 1; n <= 9; n++) {
            for (int sum = 1; sum <= 81; sum++) {
                List<String> results = findNumbersWithEqualSum(n, sum);

                if (!results.isEmpty()) {
                    System.out.println("The numbers are: " + results);
                }

            }
        }
    }

    private static List<String> findNumbersWithEqualSum(int n, int sum) {
        List<String> result = new ArrayList<>();
        List<String> queue = new ArrayList<>();
        queue.add("");

        while (!queue.isEmpty()) {
            String current = queue.remove(0);

            if (current.length() == n) {
                if (digitSum(current) == sum) {
                    result.add(current);
                }
                continue;
            }

            int startDigit = current.isEmpty() ? 1 : 0;

            for (int digit = startDigit; digit <= 9; digit++) {
                if (digitSum(current) + digit <= sum) {
                    queue.add(current + digit);
                }
            }
        }

        return result;
    }

    private static int digitSum(String number) {
        int sum = 0;
        for (char c : number.toCharArray()) {
            sum += c - '0';
        }
        return sum;
    }
}
