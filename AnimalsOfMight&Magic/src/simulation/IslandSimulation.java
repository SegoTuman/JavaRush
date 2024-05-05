package simulation;

import island.IslandField;
import island.Location;
import models.animals.herbivores.*;
import models.animals.predators.*;
import models.plant.Plant;
import simulation.services.PlantGrowthService;
import simulation.services.StatisticsService;
import simulation.services.animalLifecycleService.AnimalLifecycleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class IslandSimulation {
    private final long startTime;
    private final int countHerbivores = 35 * 50;
    private final int countPlants = 40 * 50;
    private final int countPredators = 20 * 50;
    private static volatile IslandSimulation instance;
    private volatile ScheduledExecutorService executorService;

    private IslandSimulation() {
        startTime = System.currentTimeMillis();
    }

    public static IslandSimulation getInstance() {
        if (instance == null) {
            synchronized (IslandSimulation.class) {
                if (instance == null) {
                    instance = new IslandSimulation();
                }
            }
        }
        return instance;
    }

    public void createIslandModel() {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleService animalLifecycleService = new AnimalLifecycleService();
        PlantGrowthService plantGrowthService = new PlantGrowthService();
        StatisticsService statisticsService = new StatisticsService(animalLifecycleService.getAnimalEatTask(), animalLifecycleService.getAnimalHpDecreaseTask(), animalLifecycleService.getObjectMultiplyTask());

        executorService.scheduleAtFixedRate(animalLifecycleService, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthService, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsService, 0, 8, TimeUnit.SECONDS);
    }

    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();


        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new WildBoar());

        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {

            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {

                Herbivore newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return herbivores;
    }

    private List<Predator> createPredators(int countPredators) {
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();


        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());


        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {

            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try {

                Predator newPredator = randomPredator.getClass().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return predators;
    }


    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(IslandField.getInstance().getWidth());
                int y = random.nextInt(IslandField.getInstance().getHeight());
                Location location = IslandField.getInstance().getLocation(x, y);
                if (location.getAnimals().stream().filter(c -> c.getClass().getSimpleName().equals(herbivore.getClass().getSimpleName())).toList().size() <= herbivore.getMaxCountOnField()) {
                    IslandField.getInstance().addAnimal(herbivore, x, y);
                    placed = true;
                }
            }
        }
    }

    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);

        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(IslandField.getInstance().getWidth());
                int y = random.nextInt(IslandField.getInstance().getHeight());
                Location location = IslandField.getInstance().getLocation(x, y);
                if (location.getAnimals().stream().filter(c -> c.getClass().getSimpleName().equals(predator.getClass().getSimpleName())).toList().size() <= predator.getMaxCountOnField()) {
                    IslandField.getInstance().addAnimal(predator, x, y);
                    placed = true;
                }
            }
        }
    }

    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(IslandField.getInstance().getWidth());
                int y = random.nextInt(IslandField.getInstance().getHeight());
                Location location = IslandField.getInstance().getLocation(x, y);
                if (location.getPlants().size() <= plant.getMaxCountOnField()) {
                    IslandField.getInstance().addPlant(plant, x, y);
                    placed = true;
                }
            }
        }
    }

    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPlants() {
        return countPlants;
    }

    public int getCountPredators() {
        return countPredators;
    }
}
