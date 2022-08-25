/**
 * CS2030S Lab 0: RandonPoint.java
 * Semester 1, 2022/23
 *
 * <p>The RandomPoint class encapsulates a point on a 2D plane.
 *
 * @author Dominic Ng (Group 14B) 
 */

import java.util.Random;

// TODO
class RandomPoint extends Point {

    private static Random rng = new Random(1);

    public static void setSeed(int seed) {
        RandomPoint.rng.setSeed(seed);
    }

    public RandomPoint(double minX , double maxX, double minY, double maxY) {
        super(RandomPoint.rng.nextDouble() * (maxX - minX) + minX, RandomPoint.rng.nextDouble() * (maxY - minY) + minY);
    }
}