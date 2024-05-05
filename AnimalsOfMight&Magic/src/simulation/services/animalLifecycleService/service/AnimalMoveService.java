package simulation.services.animalLifecycleService.service;

import island.IslandField;
import abstracts.Animal;

import java.util.List;

public class AnimalMoveService implements Runnable {
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getSpeed() > 0).toList();
        for (Animal animal : animals) {
            animal.move();
        }
    }
}
