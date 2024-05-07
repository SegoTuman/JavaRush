package models.animals.herbivores;

import config.EntityCharacteristicConfig;
import island.IslandField;
import island.Location;
import abstracts.Animal;

import java.io.IOException;
import java.util.Map;

public class Horse extends Herbivore {
    public Horse() {
        super(400, 4, 20, 60.0);
        try {
            Map<String, Object> characteristic = (Map<String, Object>) EntityCharacteristicConfig.getInstance().getEntityMapConfig().get("horse");
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
    public boolean multiply(Animal partner) {
        if (partner instanceof Horse){
            Location location = IslandField.getInstance().getLocation(partner.getX(), partner.getY());
            IslandField.getInstance().addAnimal(new Horse(), location.getX(), location.getY());
            return true;
        }
        return false;
    }
}
