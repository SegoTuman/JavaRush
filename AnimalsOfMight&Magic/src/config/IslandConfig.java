package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static consts.Consts.PATH_TO_SETTINGS_PROP;

public class IslandConfig {
    private int daysOfLife;
    private int width;
    private int height;

    private static volatile IslandConfig instance;


    private IslandConfig() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(PATH_TO_SETTINGS_PROP))){
            Properties prop = new Properties();
            prop.load((br));

            this.height = Integer.parseInt(prop.getProperty("island.height"));
            this.width = Integer.parseInt(prop.getProperty("island.width"));
            this.daysOfLife = Integer.parseInt(prop.getProperty("island.days"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static IslandConfig getInstance() throws IOException {
        if (instance == null) {
            synchronized (IslandConfig.class) {
                if (instance == null) {
                    instance = new IslandConfig();
                }
            }
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDaysOfLife() {
        return daysOfLife;
    }
}
