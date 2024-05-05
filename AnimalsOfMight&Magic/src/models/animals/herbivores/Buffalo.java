package models.animals.herbivores;

import config.EntityCharacteristicConfig;
import island.IslandField;
import island.Location;
import abstracts.Animal;

import java.io.IOException;
import java.util.Map;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(700.0, 3, 10, 100.0);
        try {
            Map<String, Object> characteristic = (Map<String, Object>) EntityCharacteristicConfig.getInstance().getEntityMapConfig().get("buffalo");
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
        if (partner instanceof Buffalo){
            Location location = IslandField.getInstance().getLocation(partner.getX(), partner.getY());
            IslandField.getInstance().addAnimal(new Buffalo(), location.getX(), location.getY());
        }
    }

}
