package com.CondylesIndustries1.CondylesIndustries;

public class Main {

    // all values in in

    static double distanceToTape = 7.0; // diagonal

    static double x = Constants.y / Math.tan(Math.asin(Constants.y / distanceToTape)) + Constants.XdistanceFromTapeToCenterOfHub;

    static double minimumVelocity = 267.7; // calculated in Find MinsAndMaxes
    static double maximumVelocity = 405.5;

    static double minimumAngle = 0.804;
    static double maximumAngle = 1.380;

    public static void main(String[] args) {

        x = 180; // *************** distance from fender to center is 0.860 m.
        System.out.println("Diagonal distance from Limelight to Reflective Tape: " + distanceToTape + " m");
        System.out.println("X distance " + x + " in");
        System.out.println("Y distance " + Constants.y + " in");
        System.out.println("Velocity: " + testVelocity(x)[0] + " in/s" + "\nAngle: " + testVelocity(x)[1] + " radians, " + (testVelocity(x)[1]*180.0/Math.PI) + " degrees");
    }

    public static double[] testVelocity(double x) {

        double velocity = 0.00;
        for (double i = minimumVelocity; i <= maximumVelocity; i += (1.0 / Math.pow(10, Constants.velocityDecimals))) {
            System.out.println(i);
            if ((testAngle(i,x) > minimumAngle) && (testAngle(i, x) < maximumAngle)) {
                velocity = i;
                break;
            }
        }

        double[] array = new double[2];
        array[0] = velocity;
        array[1] = testAngle(velocity, x);
        return array;
    }

    public static double testAngle(double velocity, double x) {

        double angle = minimumAngle;

        double[] possibleAngles = new double[(int) ((maximumAngle * Math.pow(10, Constants.angleDecimals)) - (minimumAngle * Math.pow(10, Constants.angleDecimals)) + 1)];
        for (int i = 0; i < possibleAngles.length; i++) {
            possibleAngles[i] = angle;
            angle = angle + (1 / Math.pow(10, Constants.angleDecimals));
        }

        for (double possibleAngle : possibleAngles) {
            double slope = Constants.slopeOfLine(velocity, possibleAngle, x);
            if (!Double.isNaN(slope)) {
                if (slope > 0.00) {
                    angle = possibleAngle;
                    break;
                }
            }
        }

        while(Constants.slopeOfLine(velocity,angle, x) > 0.000) {
            angle += (1.0 / Math.pow(10, Constants.angleDecimals));
        }
        return angle;
    }


}
