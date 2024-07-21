package simulation.services;

import island.IslandField;
import simulation.IslandSimulation;
import simulation.services.animalLifecycleService.service.AnimalEatService;
import simulation.services.animalLifecycleService.service.AnimalHpDecreaseService;
import simulation.services.animalLifecycleService.service.AnimalMultiplyService;


public class StatisticsService  {
    private boolean isTimeOver;
    private int babies;
    private int animalsEaten;
    private int animalsDiedByHungry;
    private int countAnimalsEnd;
    private int countPlants;
    private static int currentDay = 0;
    private final AnimalMultiplyService animalMultiplyService;
    private final AnimalEatService animalEatService;
   // private final AnimalHpDecreaseService animalHpDecreaseService;

    public StatisticsService(AnimalEatService animalEatService, AnimalMultiplyService animalMultiplyService) {
        this.animalEatService = animalEatService;
     //   this.animalHpDecreaseService = animalHpDecreaseService;
        this.animalMultiplyService = animalMultiplyService;
    }

    public void run() {
        babies = animalMultiplyService.getBabies();
        animalsEaten = animalEatService.getAnimalsEaten();
        animalsDiedByHungry = animalEatService.getAnimalsDiedByHungry();
        countPlants = IslandField.getInstance().getAllPlants().size();
        countAnimalsEnd = IslandField.getInstance().getAllAnimals().size();
        printStats();
    }

    private void printStats() {
        System.out.printf("--- ДЕНЬ %d ---", currentDay);
        currentDay++;
        System.out.println();

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
