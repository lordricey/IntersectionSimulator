package Actors;

import Intersection.Street;
import Utils.HelperMethods;
import Utils.SimConstants;

public class AutonomousCar extends Car {

    public AutonomousCar(Direction dir, Street street) {
        super(dir, street);
        this.crossTime = getCrossTime();
    }

    public AutonomousCar(Direction dir, Street street, double[] position, double[] velocity, double[] acceleration) {
        super(dir, street, position, velocity, acceleration);
        this.crossTime = getCrossTime();
    }

    @Override
    public double getCrossTime() {

        if (this.dir == Direction.LEFT)  {
            return HelperMethods.randomSampleFromDistribution(
                    SimConstants.LEFT_CROSS_TIME_AUTO_MEAN,
                    SimConstants.LEFT_CROSS_TIME_AUTO_STDDEV);
        }
        else if (this.dir == Direction.RIGHT) {
            return HelperMethods.randomSampleFromDistribution(
                    SimConstants.RIGHT_CROSS_TIME_AUTO_MEAN,
                    SimConstants.RIGHT_CROSS_TIME_AUTO_STDDEV);
        }
        else if (this.dir == Direction.STRAIGHT) {
            return HelperMethods.randomSampleFromDistribution(
                    SimConstants.STRAIGHT_CROSS_TIME_AUTO_MEAN,
                    SimConstants.STRAIGHT_CROSS_TIME_AUTO_STDDEV);
        }
        else return -1;

    }

    public Street getStreet() {
        return this.street;
    }

}
