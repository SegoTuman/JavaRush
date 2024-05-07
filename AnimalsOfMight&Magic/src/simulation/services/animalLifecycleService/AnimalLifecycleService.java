package simulation.services.animalLifecycleService;

import simulation.services.animalLifecycleService.service.AnimalEatService;
import simulation.services.animalLifecycleService.service.AnimalHpDecreaseService;
import simulation.services.animalLifecycleService.service.AnimalMoveService;
import simulation.services.animalLifecycleService.service.AnimalMultiplyService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class AnimalLifecycleService{
    private final AnimalEatService animalEatService;
    private final AnimalMultiplyService animalMultiplyService;
//    private final AnimalHpDecreaseService animalHpDecreaseService;
    private final AnimalMoveService animalMoveService;

    public AnimalLifecycleService() {
        animalEatService = new AnimalEatService();
        animalMultiplyService = new AnimalMultiplyService();
       // animalHpDecreaseService = new AnimalHpDecreaseService();
        animalMoveService = new AnimalMoveService();
    }

    public void run(int step) {
        animalEatService.run();
        animalMultiplyService.run(step);
       // animalHpDecreaseService.run();


    }

    public AnimalMultiplyService getObjectMultiplyTask() {
        return animalMultiplyService;
    }

    public AnimalEatService getAnimalEatTask() {
        return animalEatService;
    }

   // public AnimalHpDecreaseService getAnimalHpDecreaseTask() {
  //      return animalHpDecreaseService;
  //  }
}
