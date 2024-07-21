package models.plant;

import config.EntityCharacteristicConfig;
import abstracts.Entity;

import java.io.IOException;
import java.util.Map;

public class Plant extends Entity {
    public Plant() {
        super(1.0, 200, null, null);
        try {
            Map<String, Object> characteristic = (Map<String, Object>) EntityCharacteristicConfig.getInstance().getEntityMapConfig().get("plant");
            this.setWeight((Double)characteristic.get("weight"));
            this.setMaxCountOnField((Integer) characteristic.get("maxCountOnField"));
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
