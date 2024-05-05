package simulation.services;

import island.IslandField;
import simulation.IslandSimulation;
import simulation.services.animalLifecycleService.service.AnimalEatService;
import simulation.services.animalLifecycleService.service.AnimalHpDecreaseService;
import simulation.services.animalLifecycleService.service.AnimalMultiplyService;


public class StatisticsService implements Runnable {
    private boolean isTimeOver;
    private int babies;
    private int animalsEaten;
    private int animalsDiedByHungry;
    private int countAnimalsEnd;
    private int countPlants;
    private static int currentDay = 0;
    private final AnimalMultiplyService animalMultiplyService;
    private final AnimalEatService animalEatService;
    private final AnimalHpDecreaseService animalHpDecreaseService;

    public StatisticsService(AnimalEatService animalEatService, AnimalHpDecreaseService animalHpDecreaseService, AnimalMultiplyService animalMultiplyService) {
        this.animalEatService = animalEatService;
        this.animalHpDecreaseService = animalHpDecreaseService;
        this.animalMultiplyService = animalMultiplyService;
    }

    @Override
    public void run() {
        long timeNow = IslandSimulation.getInstance().getTimeNow();
        isTimeOver = checkTime(timeNow);

        babies = animalMultiplyService.getBabies();
        countAnimalsEnd = IslandField.getInstance().getAllAnimals().size();
        animalsEaten = animalEatService.getAnimalsEaten();
        animalsDiedByHungry = animalHpDecreaseService.getAnimalsDiedByHungry();
        countPlants = IslandField.getInstance().getAllPlants().size();
        printStats();

        if (isTimeOver) {
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }


    private boolean checkTime(long timeNow) {
        return timeNow / 60 >= 5;
    }


    private void printStats() {
        if (isTimeOver) {
            System.out.println("ПОБЕДА!!! ВЫ ПРОДЕРЖАЛИСЬ 5 МИНУТ!");
            System.out.println("----------------------------------");
        } else {
            System.out.printf("--- ДЕНЬ %d ---", currentDay);
            currentDay++;
            System.out.println();
        }
        System.out.println();
        System.out.println("СТАТИСТИКА ПО ОСТРОВУ");
        System.out.println();

        System.out.print("Животных на острове: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Животных умерло от голода: ");
        System.out.println(animalsDiedByHungry);

        System.out.print("Животных съедено: ");
        System.out.println(animalsEaten);

        System.out.print("Детенышей родилось: ");
        System.out.println(babies);

        System.out.print("Растений на острове: ");
        System.out.println(countPlants);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }

    public static int getCurrentDay() {
        return currentDay;
    }
}
