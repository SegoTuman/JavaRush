package config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static consts.Consts.PATH_TO_POSSIBILITY;

public class PossibilityOfEatingConfig {

    private  List<Map<String, Object>> eatingConfig = jsonParse();

    private static volatile PossibilityOfEatingConfig instance;


    private PossibilityOfEatingConfig() throws IOException {
    }

    public static PossibilityOfEatingConfig getInstance() throws IOException {
        if (instance == null) {
            synchronized (PossibilityOfEatingConfig.class) {
                if (instance == null) {
                    instance = new PossibilityOfEatingConfig();
                }
            }
        }
        return instance;
    }

    public List<Map<String, Object>> getEatingConfig() {
        return eatingConfig;
    }
    private List<Map<String, Object>> jsonParse() throws IOException {
        return new ObjectMapper().readValue(new File(PATH_TO_POSSIBILITY), new TypeReference<List<Map<String, Object>>>(){});
    }
}
