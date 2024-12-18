package models.animals.predators;

import abstracts.Animal;

public abstract class Predator extends Animal {


    public Predator(double weight, int speed, int maxPopulation, Double kgToFullEating) {
        super(weight, speed, maxPopulation, kgToFullEating);
    }
}
