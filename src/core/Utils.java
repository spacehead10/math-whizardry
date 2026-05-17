package core;

/**
 *Basic general methods useful in a variety of projects.
 */
public class Utils {
    /**
     * Useful for things like color RGB values where random ints are used a lot, making Math.random()
     * expressions casted to int extremely inconvenient.
     * @param min Minimum value, inclusive
     * @param max Maximum value, inclusive
     */
    public static int randomIntInRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * Useful for Slick-related variables where random floats are used a lot, making Math.random()
     * expressions casted to float extremely inconvenient.
     * @param min Minimum value, inclusive
     * @param max Maximum value, exclusive
     */
    public static float randomFloatInRange(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }

    /**
     * Convert an angle in degrees to radians. Useful as all trig functions in Math take arguments in radians.
     * @param degrees Angle in degrees
     */
    public static double toRadians(double degrees) {
        return degrees * Math.PI / 180.;
    }

    /**
     * Find the distance between 2 points
     */
    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.abs(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
}
