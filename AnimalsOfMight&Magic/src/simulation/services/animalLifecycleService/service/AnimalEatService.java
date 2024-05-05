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
import java.util.concurrent.CountDownLatch;


public class AnimalEatService implements Runnable {
    private final CountDownLatch latch;
    private int animalsEaten;


    public AnimalEatService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<Entity> lifeFormsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getClass().getSimpleName().equals("Caterpillar"))).toList().size() > 0) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getKgToFullEating() > 0) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getX(), currentAnimal.getY());
                    List<Entity> locationEntities = location.getLifeForms();
                   //locationEntities.forEach(System.out::println);
                    if (!locationEntities.isEmpty()) {
                        for (Entity entity : locationEntities) {
                           //System.out.println(currentAnimal.getClass().getSimpleName() + "  " + entity.getClass().getSimpleName());
                                if (currentAnimal.getChanceToEat(entity.getClass().getSimpleName()) > 0 && !(lifeFormsEaten.contains(entity))) {
                                    boolean isEaten = currentAnimal.eat(entity);

                                    if (isEaten) {
                                        if (entity instanceof Animal animalEaten) {
                                            lifeFormsEaten.add(animalEaten);
                                            animalsEaten++;
                                        }
                                    }
                                    break;
                                }
                        }
                    }
                }
                iterator.remove();
            }
        } else if (countAnimalsStart == 0) {
            System.out.printf("ВСЕ ЖИВОТНЫЕ УМЕРЛИ НА %d ДЕНЬ!", StatisticsService.getCurrentDay());
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.printf("ПОБЕДИЛИ ГУСЕНИЦЫ! В ЖИВЫХ ОСТАЛИСЬ ТОЛЬКО ОНИ НА %d ДЕНЬ!", StatisticsService.getCurrentDay());
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
    }
    public int getAnimalsEaten() {
        return animalsEaten;
    }
}
