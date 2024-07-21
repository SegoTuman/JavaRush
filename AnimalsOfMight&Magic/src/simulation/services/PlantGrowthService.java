package simulation.services;

import simulation.IslandSimulation;


public class PlantGrowthService {
    public void run() {
        int countPlants = 20;
        if (IslandSimulation.getInstance().getTimeNow() >= 2) {
            IslandSimulation.getInstance().placePlants(countPlants / 2);
        } else {
            IslandSimulation.getInstance().placePlants(countPlants);
        }
    }
}
