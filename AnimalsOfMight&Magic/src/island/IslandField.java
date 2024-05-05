package island;

import config.IslandConfig;
import abstracts.Animal;
import models.plant.Plant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IslandField {
    private Location[][] locations;
    private static volatile IslandField instance;
    private Integer width;
    private Integer height;

    private IslandField() {
    }

    public static IslandField getInstance() {
        if (instance == null) {
            synchronized (IslandField.class) {
                if (instance == null) {
                    instance = new IslandField();
                }
            }
        }
        return instance;
    }

    public void initializeLocations() throws IOException {
        width = IslandConfig.getInstance().getWidth();
        height = IslandConfig.getInstance().getHeight();
        locations = new Location[width][height];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    public synchronized Location getLocation(int x, int y) {
        return locations[x][y];

    }

    public void addAnimal(Animal animal, int x, int y) {
        Location location = getLocation(x, y);
        location.addAnimal(animal);
    }


    public void removeAnimal(Animal animal, int x, int y) {
        Location location = getLocation(x, y);
        location.removeAnimal(animal);
    }

    public void addPlant(Plant plant, int x, int y) {
        Location location = getLocation(x, y);
        location.addPlant(plant);
    }

    public void removePlant(Plant plant, int x, int y) {
        Location location = getLocation(x, y);
        location.removePlant(plant);
    }

    public synchronized List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] x : locations) {
            for (Location location : x) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }

    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (Location[] x : locations) {
            for (Location location : x) {
                allPlants.addAll(location.getPlants());
            }
        }
        return allPlants;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

}
