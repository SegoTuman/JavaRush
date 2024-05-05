package models.animals.predators;

import config.EntityCharacteristicConfig;
import island.IslandField;
import island.Location;
import abstracts.Animal;

import java.io.IOException;
import java.util.Map;

public class Bear extends Predator {
    public Bear() {
        super(500, 2, 5, 80.0);
        try {
            Map<String, Object> characteristic = (Map<String, Object>) EntityCharacteristicConfig.getInstance().getEntityMapConfig().get("bear");
            this.setWeight((Double)characteristic.get("weight"));
            this.setSpeed((Integer) characteristic.get("speed"));
            this.setMaxCountOnField((Integer) characteristic.get("maxCountOnField"));
            this.setKgToFullEating((Double) characteristic.get("kgToFullEating"));
            this.setHp((Double) characteristic.get("kgToFullEating"));
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Bear){
            Location location = IslandField.getInstance().getLocation(partner.getX(), partner.getY());
            IslandField.getInstance().addAnimal(new Bear(), location.getX(), location.getY());
        }
    }
}
