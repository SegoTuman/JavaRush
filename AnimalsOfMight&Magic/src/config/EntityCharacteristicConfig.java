package config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static consts.Consts.PATH_TO_ENTITY_CHARACTERISTICS;

public class EntityCharacteristicConfig {

    private Map<String, Object>  entityMapConfig = jsonParse();

    private static volatile EntityCharacteristicConfig instance;


    private EntityCharacteristicConfig() throws IOException {
    }

    public static EntityCharacteristicConfig getInstance() throws IOException {
        if (instance == null) {
            synchronized (EntityCharacteristicConfig.class) {
                if (instance == null) {
                    instance = new EntityCharacteristicConfig();
                }
            }
        }
        return instance;
    }



    public Map<String, Object> getEntityMapConfig() {
        return entityMapConfig;
    }
    private Map<String, Object> jsonParse() throws IOException {
        return new ObjectMapper().readValue(new File(PATH_TO_ENTITY_CHARACTERISTICS), new TypeReference<Map<String, Object>>(){});

    }
}
