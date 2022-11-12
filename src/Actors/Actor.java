package Actors;

public class Actor {

    //All arrays are in the form [x, y]. Units are meters, meters/sec, meters/sec^2.
    protected double[] position;
    protected double[] velocity;
    protected double[] acceleration;

    public Actor() {
        this.position = new double[]{0, 0};
        this.velocity = new double[]{0, 0};
        this.acceleration = new double[]{0, 0};
    }

    public Actor(double[] position, double[] velocity, double[] acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public double[] getPosition() { return this.position; }

    public double[] getVelocity() { return this.velocity; }

    public double[] getAcceleration() { return this.acceleration; }

    public void setPosCartesian(double[] position) { this.position = position; }

    public void setVelCartesian(double[] vel) { this.velocity = vel; }

    public void setVelPolar(double vel, double heading) {
        this.velocity[0] = vel*Math.cos(heading);
        this.velocity[1] = vel*Math.sin(heading);
    }

    public void setAccelCartesian(double[] accel) { this.acceleration = accel; }

    public void setAccelPolar(double accel, double heading) {
        this.acceleration[0] = accel*Math.cos(heading);
        this.acceleration[1] = accel*Math.sin(heading);
    }

    public double getHeading() { return Math.atan2(this.velocity[1], this.velocity[0]); }

    public double getVelMag() { return Math.sqrt( Math.pow(this.velocity[0], 2) + Math.pow(this.velocity[1], 2) ); }

    public void updateKinematics(double sampleTime) {
        this.velocity[0] += this.acceleration[0]*sampleTime;
        this.velocity[1] += this.acceleration[1]*sampleTime;
        this.position[0] += this.velocity[0]*sampleTime;
        this.position[1] += this.velocity[1]*sampleTime;
    }

}
