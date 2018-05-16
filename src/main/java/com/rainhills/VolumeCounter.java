package com.rainhills;

import java.util.function.Function;
import java.util.function.Predicate;

class VolumeCounter {

    private static final int MAX_ARRAY_LENGTH = 32000;
    private static final int MAX_HEIGHT = 32000;
    private static final int MIN_HEIGHT = 0;

    private void validate(int height) {
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new IllegalArgumentException(String.format("Invalid height of hill: %d. Valid range: [%d, %d]",
                    height, MIN_HEIGHT, MAX_HEIGHT));
        }
    }

    private int[] prepareMax(int[] heights, int firstIndex,
                             Function<Integer, Integer> indexModifier,
                             Predicate<Integer> until) {
        int[] result = new int[heights.length];
        int max = 0;
        for (int i = firstIndex; until.test(i); i = indexModifier.apply(i)) {
            int currentHeight = heights[i];
            validate(currentHeight);
            if (max < currentHeight) {
                max = currentHeight;
            }
            result[i] = max;
        }
        return result;
    }

    private int[] prepareMaxFromLeft(int[] heights) {
        return prepareMax(heights,
                0,
                i -> i + 1,
                i -> i < heights.length);
    }

    private int[] prepareMaxFromRight(int[] heights) {
        return prepareMax(heights,
                heights.length - 1,
                i -> i - 1,
                i -> i > 0);
    }

    public int calcWaterAmount(int[] landscape) {
        if (landscape.length == 0) {
            return 0;
        }
        if (landscape.length > MAX_ARRAY_LENGTH) {
            throw new IllegalArgumentException("The maximum length of input array (landscape) is: " + MAX_ARRAY_LENGTH);
        }
        int[] leftHighest = prepareMaxFromLeft(landscape);
        int[] rightHighest = prepareMaxFromRight(landscape);
        int sum = 0;
        for (int i = 1; i < landscape.length - 1; i++) {
            int current = Math.min(leftHighest[i], rightHighest[i]);
            int volume = current - landscape[i];
            if (volume > 0) {
                sum += volume;
            }
        }
        return sum;
    }

}
