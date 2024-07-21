package abstracts;

import config.PossibilityOfEatingConfig;
import exception.ObjectNotLifeFormException;
import island.IslandField;
import island.Location;
import models.plant.Plant;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Animal extends Entity {
    private double hp;
    private boolean full;
    public Animal(double weight, int speed, int maxPopulation, Double kgToFullEating) {
        super(weight, maxPopulation, speed,  kgToFullEating);
        hp = kgToFullEating;
    }

    public boolean eat(Object food) {
        double chanceToEat;
        Entity entity = null;
        boolean animalEatFood;

        if (food instanceof Entity) {
            entity = (Entity) food;
        } else {
            try {
                throw new ObjectNotLifeFormException("Объект не является животным/растением.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String foodName = entity.getClass().getSimpleName().toLowerCase();
        chanceToEat = getChanceToEat(foodName) / 100;

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            Location location = IslandField.getInstance().getLocation(this.getX(), this.getY());
            if (entity instanceof Animal animal) {
                if (location.getAnimals().contains(animal)) {
                    IslandField.getInstance().removeAnimal(animal);
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) entity;
                if (location.getPlants().size() > 0) {
                    IslandField.getInstance().removePlant(plant);
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }

    public abstract boolean multiply(Animal partner);





    public double getChanceToEat(String foodName) {
        try {
            if(this.getClass().getSimpleName().toLowerCase().equals(foodName.toLowerCase())){
                return 0.0;
            }
            return (double) PossibilityOfEatingConfig.getInstance().getEatingConfig().stream()
                    .filter((m) -> this.getClass().getSimpleName().toLowerCase().equals(m.get("from"))
                            & foodName.toLowerCase().equals(m.get("to"))).collect(Collectors.toList()).get(0)
                    .get("percent");
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            throw new RuntimeException(e);
        }
    }



    public void move() {
        Random random = new Random();
        int randomCells = random.nextInt(getSpeed()) + 1;
        int direction = random.nextInt(4);
        int newX = getX();
        int newY = getY();
        switch (direction) {
            case 0 -> // Вверх
                    newX -= randomCells;
            case 1 -> // Вниз
                    newX += randomCells;
            case 2 -> // Влево
                    newY -= randomCells;
            case 3 -> // Вправо
                    newY += randomCells;
        }

        while (newX < 0 || newX >= IslandField.getInstance().getWidth() || newY < 0 || newY >= IslandField.getInstance().getHeight()) {
            randomCells = random.nextInt(getSpeed()) + 1;
            direction = random.nextInt(4);

            newX = getX();
            newY = getY();
            switch (direction) {
                case 0 ->
                        newX -= randomCells;
                case 1 ->
                        newX += randomCells;
                case 2 ->
                        newY -= randomCells;
                case 3 ->
                        newY += randomCells;
            }
        }
        IslandField.getInstance().removeAnimal(this);
        IslandField.getInstance().addAnimal(this, newX, newY);
    }

    public Integer getSpeed() {
        return speed;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public Double getKgToFullEating() {
        return kgToFullEating;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}
