package org.shorts;

public final class MathUtils {

    public static double roundHalfDown(double x) {
        int floor = (int) x;
        double decimalPart = x - floor;
        if (decimalPart > 0.5) {
            return floor + 1d;
        } else {
            return floor;
        }
    }

    public static double roundHalfUp(double x) {
        int floor = (int) x;
        double decimalPart = x - floor;
        if (decimalPart >= 0.5) {
            return floor + 1d;
        } else {
            return floor;
        }
    }
}
