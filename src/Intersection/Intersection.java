package Intersection;

import Actors.*;
import Utils.SimConstants;

import java.util.*;

public class Intersection {

    private ArrayList<ArrayDeque<Car>> cars;
    private ArrayDeque<Car> crossings;
    private double nextCrossTime = 0;
    private double nextSpawnTime = 0;
    private double elapsedTime = 0;
    private Car carToRemove = null;

    //Create a new Intersection object
    public Intersection() {

        this.cars = new ArrayList<ArrayDeque<Car>>();
        for (int i = 0; i < 4; i++) {
            this.cars.add(new ArrayDeque<Car>());
        }

        this.crossings = new ArrayDeque<Car>();

    }

    public ArrayList<Actor> execute() {

        this.elapsedTime += SimConstants.SIM_SAMPLE_RATE;
        spawnCarRandomly();
        updateActorPositions();
        crossCar();
        return getAllActors();

    }

    //Return the next car in the crossings queue if the crossing time of the last car has passed.
    public Car crossCar() {

        if (this.elapsedTime >= nextCrossTime && crossings.size() > 0) {
            //remove the car from the crossings array and the array of cars
            Car car = crossings.remove();
            car.setCrossing(true);

            nextCrossTime = this.elapsedTime + car.getCrossTime();
            System.out.println("CAR CROSSING! (" + elapsedTime + ")");

            if (carToRemove != null) {
                int street = carToRemove.getStreet().ordinal();
                this.cars.get(street).remove(carToRemove);
            }

            carToRemove = car;
            return car;
        }
        else
            return null;

    }

    private void spawnCarRandomly() {

        if (this.elapsedTime >= this.nextSpawnTime) {

            Random rand = new Random();

            int streetInt = rand.nextInt(4);
            int directionInt = rand.nextInt(3);

            boolean autonomous = rand.nextDouble() <= SimConstants.AUTONOMOUS_PERCENTAGE;
            Street street = Street.values()[streetInt];
            Direction dir = Direction.values()[directionInt];

            spawnCar(autonomous, street, dir);

            nextSpawnTime = (this.elapsedTime + rand.nextDouble()*(SimConstants.CAR_SPAWN_TIME_MAX - SimConstants.CAR_SPAWN_TIME_MIN)
                    + SimConstants.CAR_SPAWN_TIME_MIN);

        }

    }

    private void spawnCar(boolean autonomous, Street street, Direction dir) {

        Car car = autonomous ?
                new AutonomousCar(dir, street) :
                new DrivenCar(dir, street);

        cars.get(street.ordinal()).add(car);

        //THIS LOGIC ADDS A CAR TO THE EVENT QUEUE BASED ON SPAWN TIME. TO BE CHANGED.
        crossings.add(car);

        System.out.println("CAR SPAWNED! (" + elapsedTime + ") Autonomous = " + autonomous + ", Street = " + street + ", Direction = " + dir);

    }

    private void updateActorPositions() {
        for (Deque<Car> queue : cars) {
            for (Car car: queue) {
                car.updateCar();
            }
        }
    }

    private ArrayList<Actor> getAllActors() {

        ArrayList<Actor> actors = new ArrayList<Actor>();

        for (Deque<Car> queue : cars) {
            for (Car car: queue) {
                actors.add(car);
            }
        }

        //PEDESTRIANS TO BE ADDED

        return actors;

    }

}
