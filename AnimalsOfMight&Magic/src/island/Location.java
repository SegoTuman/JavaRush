package island;

import abstracts.Entity;
import abstracts.Animal;
import models.plant.Plant;

import java.util.ArrayList;
import java.util.List;


public class Location {
    public Integer x;
    public Integer y;
    private final List<Animal> animals;
    private final List<Plant> plants;


    public Location(int x, int y) {
        this.x = x;
        this.y = y;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animal.setX(x);
        animal.setY(y);

        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plant.setX(x);
        plant.setY(y);
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Entity> getLifeForms() {
        List<Entity> entities = new ArrayList<>();
        entities.addAll(animals);
        entities.addAll(plants);
        return entities;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
