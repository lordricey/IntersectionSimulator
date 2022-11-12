package Graphics;

import Actors.Actor;
import Utils.SimConstants;
import javax.swing.*;
import java.util.ArrayList;

public class GraphicsHandler {

    SimulationWindow window = new SimulationWindow();

    public GraphicsHandler() {
        initializeSimulationWindow();
    }

    public void initializeSimulationWindow() {

        JFrame frame = new JFrame("Simulation");
        window = new SimulationWindow();
        frame.add(window);
        frame.setSize(SimConstants.WINDOW_WIDTH, SimConstants.WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void drawActors(ArrayList<Actor> actors) {
        this.window.drawActors(actors);
    }

}
