package org.treeleaf;

public class ArrayInversion {
    public static void main(String[] args) {
        int[] arrayValues = {1, 9, 6, 4, 5};

        int inversionCount = countArrayInversions(arrayValues);

        System.out.println("Total Inversions in Given Array: " + inversionCount);
    }

    private static int countArrayInversions(int[] arrayValues) {
        int count = 0;
        int arrayLength = arrayValues.length;

        for(int i = 0; i < arrayLength; i++) {
            for(int j = i + 1; j < arrayLength; j++) {
                if(arrayValues[i] > arrayValues[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}