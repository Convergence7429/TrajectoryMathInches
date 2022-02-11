package com.CondylesIndustries1.CondylesIndustries;

public class FindMinsAndMaxes {

    // all values in m


    // need to account for distance between shooter and limelight, easy to do
    static double minimumDistance = 42.559; // // 0.860; // X distance // horizontal // distance from fender to center of hub.
    // 1.081 = front of robot to shooter + fender to tape horizontal + half of distance between tape and center of hub
    // need to offset with distance between front of robot and limelight and shooter
    static double maximumDistance = 324.016; // X distance // horizontal // 8.23 m on the field. half the length of the field
    // if limelight and shooter was on the end of the robot,
    // subtract the length of the robot for maximum distance because the distance wouldn't be longer than that

    static double minimumVelocity = Double.MAX_VALUE;
    static double maximumVelocity = Double.MIN_VALUE;

    static double minimumAngle = Double.MAX_VALUE;
    static double maximumAngle = Double.MIN_VALUE;

    public static void main(String[] args) {
        findDifference();
        System.out.println("minimumVelocity: " + minimumVelocity + " in/s");
        System.out.println("maximumVelocity: " + maximumVelocity + " in/s");
        System.out.println("minimumAngle: " + minimumAngle + " radians, " + ((minimumAngle*180.0)/Math.PI) + " degrees");
        System.out.println("maximumAngle: " + maximumAngle + " radians, " + ((maximumAngle*180.0)/Math.PI) + " degrees");
    }

    public static double[] findVelocityLimits(double distance) {

        for(double i = 0; i <= Double.MAX_VALUE; i+= (1.0/Math.pow(10, Constants.velocityDecimals))){
            System.out.println(i);
            if(findAngleLimits(i, distance) < 1.570){
                double[] array = new double[2];
                array[0] = i;
                array[1] = findAngleLimits(i, distance);
                return array;
            }
        }

        return new double[2];
    }

    public static double findAngleLimits(double velocity, double distance){

        double angle = 0.00;
        double[] possibleAngles = new double[(int) (1.572 * Math.pow(10, Constants.angleDecimals)) + 1];
        for (int i = 0; i < possibleAngles.length; i++) {
            possibleAngles[i] = angle;
            angle = angle + (1 / Math.pow(10, Constants.angleDecimals));
        }

        for (double possibleAngle : possibleAngles) {
            double slope = Constants.slopeOfLine(velocity, possibleAngle, distance);
            if (!Double.isNaN(slope)) {
                if (slope > 0.00) {
                    angle = possibleAngle;
                    break;
                }
            }
        }

        while(Constants.slopeOfLine(velocity,angle, distance) > 0.000) {
            angle += (1.0 / Math.pow(10, Constants.angleDecimals));
        }
        return angle;
    }

    public static void findDifference(){

        for(double j = minimumDistance; j < maximumDistance; j+= 0.01) {
            //double x = j;
            if (findVelocityLimits(j)[1] < minimumAngle) {
                minimumAngle = findVelocityLimits(j)[1];
            }
            System.out.println("minimumAngle: " + minimumAngle + " radians, " + ((minimumAngle*180.0)/Math.PI) + " degrees");
            if ((findVelocityLimits(j)[1] < 1.570) && (findVelocityLimits(j)[1] > maximumAngle)) {
                maximumAngle = findVelocityLimits(j)[1];
            }
            System.out.println("maximumAngle: " + maximumAngle + " radians, " + ((maximumAngle*180.0)/Math.PI) + " degrees");
            if (findVelocityLimits(j)[0] < minimumVelocity) {
                minimumVelocity = findVelocityLimits(j)[0];
            }
            System.out.println("minimumVelocity: " + minimumVelocity + " m/s");
            if (findVelocityLimits(j)[0] > maximumVelocity) {
                maximumVelocity = findVelocityLimits(j)[0];
            }
            System.out.println("maximumVelocity: " + maximumVelocity + " m/s");


        }
    }

}
