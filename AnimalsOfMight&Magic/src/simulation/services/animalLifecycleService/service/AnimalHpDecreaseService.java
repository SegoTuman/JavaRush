package simulation.services.animalLifecycleService.service;

import island.IslandField;
import abstracts.Animal;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalHpDecreaseService {
    private double percentOfHpToDecrease = 0.15;
    private int animalsDiedByHungry;

    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> !c.isFull()).toList();
        for (Animal animal : animals) {
            double hpToDecrease = animal.getKgToFullEating() * percentOfHpToDecrease;
            if (animal.getHp() - hpToDecrease > 0) {
                animal.setHp(animal.getHp() - hpToDecrease);
            } else {
                IslandField.getInstance().removeAnimal(animal);
                animalsDiedByHungry++;
            }
        }
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
