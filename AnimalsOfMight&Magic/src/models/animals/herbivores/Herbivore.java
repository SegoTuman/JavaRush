package models.animals.herbivores;

import abstracts.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int speed, int maxPopulation, Double kgToFullEating) {
        super(weight, speed, maxPopulation, kgToFullEating);
    }
}
