package com.rainhills;

import org.junit.Test;

import static org.junit.Assert.*;

public class VolumeCounterTest {

    @Test
    public void testGivenExample() {
        assertEquals(9, new VolumeCounter().calcWaterAmount(new int[] {5, 2, 3, 4, 5, 4, 0, 3, 1}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, new VolumeCounter().calcWaterAmount(new int[] {}));
    }

    @Test
    public void testSingleHill() {
        assertEquals(0, new VolumeCounter().calcWaterAmount(new int[] {1}));
    }

    @Test
    public void testMountain() {
        assertEquals(0, new VolumeCounter().calcWaterAmount(new int[] {1, 2, 1}));
    }

    @Test
    public void testSimplePit() {
        assertEquals(1, new VolumeCounter().calcWaterAmount(new int[] {2, 1, 2}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNegativeHeight() {
        new VolumeCounter().calcWaterAmount(new int[] {-1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHeight() {
        new VolumeCounter().calcWaterAmount(new int[] {64000});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInputArray() {
        new VolumeCounter().calcWaterAmount(new int[32001]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullArgument() {
        new VolumeCounter().calcWaterAmount(null);
    }

    @Test
    public void testTrickyLandscape() {
        assertEquals(18L, new VolumeCounter().calcWaterAmount(new int[] {1, 4, 1, 2, 5, 2, 3, 0, 5, 4, 0, 3, 1}));
    }

}