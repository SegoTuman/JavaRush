package simulation.services.animalLifecycleService.service;

import island.IslandField;
import abstracts.Animal;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalHpDecreaseService implements Runnable {
    private double percentOfHpToDecrease = 15;
    private final CountDownLatch latch;
    private int animalsDiedByHungry;

    public AnimalHpDecreaseService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getKgToFullEating() > 0).toList();
        for (Animal animal : animals) {
            double hpToDecrease = animal.getKgToFullEating() * percentOfHpToDecrease / 100.0;
            if (animal.getHp() - hpToDecrease > 0) {
                animal.setHp(animal.getHp() - hpToDecrease);
            } else {
                IslandField.getInstance().removeAnimal(animal, animal.getX(),animal.getY());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
