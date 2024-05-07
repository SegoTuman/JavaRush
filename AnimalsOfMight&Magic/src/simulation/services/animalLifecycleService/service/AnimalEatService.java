package simulation.services.animalLifecycleService.service;

import island.IslandField;
import island.Location;
import abstracts.Entity;
import abstracts.Animal;
import simulation.IslandSimulation;
import simulation.services.StatisticsService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


public class AnimalEatService {
    private int animalsEaten;
    private int animalsDiedByHungry;

    public void run() {
        animalsDiedByHungry = 0;
        animalsEaten = 0;
        Set<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<Entity> lifeFormsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getClass().getSimpleName().equals("Caterpillar"))).toList().size() > 0) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                currentAnimal.setFull(false);
                Double alreadyEaten = 0.0;
                if (currentAnimal.getKgToFullEating() > 0) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getX(), currentAnimal.getY());
                    List<Entity> locationEntities = location.getLifeForms();
                    if (!locationEntities.isEmpty()) {
                        for (Entity entity : locationEntities) {
                            if (!(lifeFormsEaten.contains(entity))) {
                                boolean isEaten = currentAnimal.eat(entity);


                                if (isEaten) {
                                    lifeFormsEaten.add(entity);
                                    alreadyEaten += entity.getWeight();
                                    if (currentAnimal.getHp() + alreadyEaten >= currentAnimal.getKgToFullEating()) {
                                        currentAnimal.setHp(currentAnimal.getKgToFullEating());
                                        currentAnimal.setFull(true);
                                    }
                                    if (entity instanceof Animal animalEaten) {
                                        animalsEaten++;
                                    }
                                }
                                if (currentAnimal.isFull() == true) {
                                    break;
                                }
                            }
                        }
                        if(!currentAnimal.isFull()) {
                            currentAnimal.setHp(currentAnimal.getHp() + alreadyEaten);
                        }
                        double hpToDecrease = currentAnimal.getKgToFullEating() * 0.15;
                        if (currentAnimal.getHp() - hpToDecrease > 0) {
                            currentAnimal.setHp(currentAnimal.getHp() - hpToDecrease);
                        } else {
                            IslandField.getInstance().removeAnimal(currentAnimal);
                            animalsDiedByHungry++;
                        }
                    }
                }
                iterator.remove();
            }
        } else {
            System.out.printf("ПОБЕДИЛИ ГУСЕНИЦЫ! В ЖИВЫХ ОСТАЛИСЬ ТОЛЬКО ОНИ НА %d ДЕНЬ!", StatisticsService.getCurrentDay());
            System.exit(0);
        }
    }

    public int getAnimalsEaten() {
        return animalsEaten;
    }

    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
