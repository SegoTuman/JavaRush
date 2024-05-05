package simulation.services.animalLifecycleService.service;

import island.IslandField;
import island.Location;
import abstracts.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalMultiplyService implements Runnable {
    private int babies;
    private final CountDownLatch latch;

    public AnimalMultiplyService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        babies = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getX(), currentAnimal.getY());
                    List<Animal> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getClass().getSimpleName().equals(currentAnimal.getClass().getSimpleName()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            Animal partner = locationAnimals.get(0);

                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.multiply(partner);
                                //System.out.println(currentAnimal.getClass().getSimpleName());
                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }

    public int getBabies() {
        return babies;
    }
}
