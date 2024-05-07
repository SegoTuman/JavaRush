package models.animals.herbivores;

import config.EntityCharacteristicConfig;
import island.IslandField;
import island.Location;
import abstracts.Animal;

import java.io.IOException;
import java.util.Map;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 1, 500, 0.01);
        try {
            Map<String, Object> characteristic = (Map<String, Object>) EntityCharacteristicConfig.getInstance().getEntityMapConfig().get("mouse");
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
        if (partner instanceof Mouse){
            Location location = IslandField.getInstance().getLocation(partner.getX(), partner.getY());
            IslandField.getInstance().addAnimal(new Mouse(), location.getX(), location.getY());
            return true;
        }
        return false;
    }
}
