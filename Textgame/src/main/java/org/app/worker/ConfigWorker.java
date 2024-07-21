package org.app.worker;

import org.app.config.GameConf;
import org.app.models.Choice;
import org.app.models.GameData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.app.consts.Consts.PATH_TO_DATA_JSON;

public class ConfigWorker {
    GameData gameData;


    public ConfigWorker() {

        String path = this.getClass().getResource(PATH_TO_DATA_JSON).getPath();
        GameConf gameConf = new GameConf(path);
        try {
            this.gameData = gameConf.getGameData();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public String getQuestionText(String id) {
        return gameData.questions.get(id).text;
    }

    public List<Choice> getChoices(String id) {
        List<Choice> choices = new ArrayList<>();
        gameData.questions.get(id).choices.forEach((elem) -> choices.add(gameData.choices.get(elem)));
        return choices;
    }
}
