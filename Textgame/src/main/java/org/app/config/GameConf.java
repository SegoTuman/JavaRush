package org.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.models.GameData;

import java.io.File;
import java.io.IOException;

public class GameConf {
    public String path;

    public GameConf(String path) {
        this.path = path;
    }
    public void setPath(String inPath) {
        path = inPath;
    }

    public GameData getGameData() throws IOException {
        File src = new File(path);
        return new ObjectMapper().readValue(src, GameData.class);
    }
}
