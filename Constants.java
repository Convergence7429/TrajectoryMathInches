package com.CondylesIndustries1.CondylesIndustries;

public class Constants {

    // all values in in

    final public static double y = 104.016; //2.642 + .127+.127; // height of hub + 2*5 inches (half the diameter of ball)
    final public static double y0 = 21.0;
    final public static double gravity = 386.103;
    static double XdistanceFromTapeToCenterOfHub = 26.614;
    final public static int velocityDecimals = 1;
    final public static int angleDecimals = 3;

    public static double slopeOfLine(double velocity, double possibleAngle, double x) {

        return ((y - x) / (((-1) * ((-1) * velocity * Math.sin(possibleAngle) - Math.sqrt(Math.pow(velocity, 2)
                * Math.pow(Math.sin(possibleAngle), 2) + ((2*gravity)*(y0-y)))) / gravity)
                - (x / (velocity * Math.cos(possibleAngle)))));
    }
}
