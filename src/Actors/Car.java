package Actors;

import Intersection.Street;
import Utils.SimConstants;

public class Car extends Actor {

    protected Direction dir;
    protected double timeWaiting;
    protected boolean crossing;
    protected boolean crossed;
    protected double crossTime;
    protected Street street;

    public Car(Direction dir, Street street) {
        this.street = street;
        this.dir = dir;
        this.timeWaiting = 0;
        this.crossing = false;
        this.crossed = false;
        placeCar();
    }

    public Car(Direction dir, Street street, double[] position, double[] vel, double[] accel) {
        super(position, vel, accel);
        this.dir = dir;
        this.timeWaiting = 0;
        this.crossing = false;
        this.street = street;
    }

    public void setCrossing(boolean crossing) { this.crossing = crossing; }

    public double getCrossTime() { return crossTime; }

    public Street getStreet() { return this.street; }

    public void updateCar() {

        double LEFT_RADIUS = 7.5;
        double RIGHT_RADIUS = 4.5;

        if (this.crossing) {
            if (this.dir == Direction.STRAIGHT) {
                if (this.street == Street.NORTH) this.setAccelCartesian(new double[]{0,-2});
                else if (this.street == Street.EAST) this.setAccelCartesian(new double[]{-2,0});
                else if (this.street == Street.SOUTH) this.setAccelCartesian(new double[]{0,2});
                else if (this.street == Street.WEST) this.setAccelCartesian(new double[]{2,0});
            }
            else if (this.dir == Direction.LEFT) {
                if (this.getVelMag() == 0) {
                    if (this.street == Street.NORTH) this.setVelPolar(5, -Math.PI/2);
                    else if (this.street == Street.EAST) this.setVelPolar(5, Math.PI);
                    else if (this.street == Street.SOUTH) this.setVelPolar(5, Math.PI/2);
                    else if (this.street == Street.WEST) this.setVelPolar(5, 0);
                }
                double accelMag = Math.pow(this.getVelMag(), 2) / LEFT_RADIUS;
                double accelAngle = this.getHeading() + Math.PI/2;
                this.setAccelPolar(accelMag, accelAngle);
            }
            else if (this.dir == Direction.RIGHT) {
                if (this.getVelMag() == 0) {
                    if (this.street == Street.NORTH) this.setVelPolar(5, -Math.PI/2);
                    else if (this.street == Street.EAST) this.setVelPolar(5, Math.PI);
                    else if (this.street == Street.SOUTH) this.setVelPolar(5, Math.PI/2);
                    else if (this.street == Street.WEST) this.setVelPolar(5, 0);
                }
                double accelMag = Math.pow(this.getVelMag(), 2) / RIGHT_RADIUS;
                double accelAngle = this.getHeading() - Math.PI/2;
                this.setAccelPolar(accelMag, accelAngle);
            }
        }
        else {
            this.setVelCartesian(new double[]{0,0});
            this.setAccelCartesian(new double[]{0,0});
        }

        this.updateKinematics(SimConstants.SIM_SAMPLE_RATE);

    }

    public void placeCar() {
        if (this.street == Street.NORTH) {
            this.position = new double[]{-SimConstants.LANE_WIDTH/2, SimConstants.LANE_WIDTH + SimConstants.CAR_LENGTH/2};
        }
        else if (this.street == Street.EAST) {
            this.position = new double[]{SimConstants.LANE_WIDTH + SimConstants.CAR_LENGTH/2, SimConstants.LANE_WIDTH/2};
        }
        else if (this.street == Street.SOUTH) {
            this.position = new double[]{SimConstants.LANE_WIDTH/2, -SimConstants.LANE_WIDTH - SimConstants.CAR_LENGTH/2};
        }
        else if (this.street == Street.WEST) {
            this.position = new double[]{-SimConstants.LANE_WIDTH - SimConstants.CAR_LENGTH/2, -SimConstants.LANE_WIDTH/2};
        }
    }

}
