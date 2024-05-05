package simulation.services.animalLifecycleService;

import simulation.services.animalLifecycleService.service.AnimalEatService;
import simulation.services.animalLifecycleService.service.AnimalHpDecreaseService;
import simulation.services.animalLifecycleService.service.AnimalMoveService;
import simulation.services.animalLifecycleService.service.AnimalMultiplyService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class AnimalLifecycleService implements Runnable {
    private final AnimalEatService animalEatService;
    private final AnimalMultiplyService animalMultiplyService;
    private final AnimalHpDecreaseService animalHpDecreaseService;
    private final CountDownLatch latch;

    public AnimalLifecycleService() {
        latch = new CountDownLatch(3);
        animalEatService = new AnimalEatService(latch);
        animalMultiplyService = new AnimalMultiplyService(latch);
        animalHpDecreaseService = new AnimalHpDecreaseService(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(animalEatService);
        executorService.submit(animalMultiplyService);
        executorService.submit(animalHpDecreaseService);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(new AnimalMoveService());
    }

    public AnimalMultiplyService getObjectMultiplyTask() {
        return animalMultiplyService;
    }

    public AnimalEatService getAnimalEatTask() {
        return animalEatService;
    }

    public AnimalHpDecreaseService getAnimalHpDecreaseTask() {
        return animalHpDecreaseService;
    }
}
