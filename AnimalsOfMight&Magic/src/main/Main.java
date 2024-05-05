package main;
import island.IslandField;
import simulation.IslandSimulation;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        IslandField.getInstance().initializeLocations();
        IslandSimulation.getInstance().createIslandModel();

    }
}
