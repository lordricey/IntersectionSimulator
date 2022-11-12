package Main;

import Actors.Actor;
import Graphics.GraphicsHandler;
import Graphics.SimulationWindow;
import Intersection.Intersection;
import Utils.SimConstants;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Intersection intersection = new Intersection();
        GraphicsHandler graphics = new GraphicsHandler();

        while (true) {

            //Update intersection and actor data
            ArrayList<Actor> actors = intersection.execute();

            //Draw them
            graphics.drawActors(actors);

            //Pause simulation for viewing in real time
            Thread.currentThread().sleep((long)(SimConstants.SIM_SAMPLE_RATE*1000/2));

        }

    }

}
