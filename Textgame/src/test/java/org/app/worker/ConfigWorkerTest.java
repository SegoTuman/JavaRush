package org.app.worker;

import org.app.models.Choice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigWorkerTest {
    @Test
    public void getQuestionTextTest() {
        ConfigWorker configWorker = new ConfigWorker();
        String text = configWorker.getQuestionText("1");
        assertEquals("Выбор 2: Вы приближаетесь к древнему храму, где, по слухам, спрятан артефакт. Но у входа вас встречает стражник. Что вы скажете ему?", text);
    }

    @Test
    public void getChoicesTest() {
        ConfigWorker configWorker = new ConfigWorker();
        List<Choice> choices = new ArrayList<>();
        Choice choice1 = new Choice();
        choice1.setTextAndTransition("Я пришёл с миром и хочу увидеть артефакт.", "-1");
        Choice choice2 = new Choice();
        choice2.setTextAndTransition("Мне нужно пройти внутрь, и ты меня не остановишь.", "-1");
        Choice choice3 = new Choice();
        choice3.setTextAndTransition("У меня есть важная информация об артефакте, и я хочу поделиться ею.", "2");

        choices.add(choice1);
        choices.add(choice2);
        choices.add(choice3);
        assertEquals(choices, configWorker.getChoices("1"));
    }
}