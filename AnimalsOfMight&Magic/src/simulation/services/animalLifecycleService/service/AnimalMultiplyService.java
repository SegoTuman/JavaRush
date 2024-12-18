package simulation.services.animalLifecycleService.service;

import island.IslandField;
import island.Location;
import abstracts.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class AnimalMultiplyService {
    private int babies;

    public void run(int step) {
        babies = 0;
        if(!(step % 4 == 0)){
            return;
        }
        Set<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getX(), currentAnimal.getY());
                    Set<Animal> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        List<Animal> potentialPartners = locationAnimals.stream().
                                filter(c -> c.getClass().getSimpleName().equals(currentAnimal.getClass().
                                        getSimpleName()) && c != currentAnimal).toList();

                        if (potentialPartners.size() > 0) {
                            Animal partner = potentialPartners.get(0);

                           if (!animalsMultiplied.contains(partner)) {
                               if(currentAnimal.multiply(partner)) {
                                   animalsMultiplied.add(currentAnimal);
                                   animalsMultiplied.add(partner);
                                   babies++;
                               }
                           }
                      }
                    }
                }
            }
        }
    }

    public int getBabies() {
        return babies;
    }
}
