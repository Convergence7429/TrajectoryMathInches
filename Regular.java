package com.CondylesIndustries1.CondylesIndustries;

public class Regular {

    // all values in m

    static double distanceToTape = 20; // diagonal
    static double x = Constants.y / Math.tan(Math.asin(Constants.y/distanceToTape)) + Constants.XdistanceFromTapeToCenterOfHub;

    public static void main(String[] args) {

        x = .5588 + .184 + .338;
        System.out.println("Diagonal distance from Limelight to Reflective Tape: " + distanceToTape + " m");
        System.out.println("X distance " + x + " m");
        System.out.println("Y distance " + Constants.y + " m");
        System.out.println("Velocity: " + testVelocity()[0] + " m/s" + "\nAngle: " + testVelocity()[1] + " radians, " + (testVelocity()[1]*180.0/Math.PI) + " degrees");
    }

    public static double[] testVelocity() {

        for(double i = 0.0; i < Double.MAX_VALUE; i += (1.0/Math.pow(10, Constants.velocityDecimals))){
            if(testAngle(i) < 1.570){
                double[] array = new double[2];
                array[0] = i;
                array[1] = testAngle(i);
                return array;
            }
        }
        return new double[2];
    }

    public static double testAngle(double velocity) {
        double angle = 0.00;
        double[] possibleAngles = new double[(int) (1.572 * Math.pow(10, Constants.angleDecimals)) + 1];
        for (int i = 0; i < possibleAngles.length; i++) {
            possibleAngles[i] = angle;
            angle = angle + (1 / Math.pow(10, Constants.angleDecimals));
        }

        for (double possibleAngle : possibleAngles) {
            double slope = Constants.slopeOfLine(velocity, possibleAngle,x);
            if (!Double.isNaN(slope)) {
                if (slope > 0.00) {
                    angle = possibleAngle;
                    break;
                }
            }
        }

        while(Constants.slopeOfLine(velocity,angle,x) > 0.000) {
            angle += (1.0/Math.pow(10, Constants.angleDecimals));
        }
        return angle;
    }
}