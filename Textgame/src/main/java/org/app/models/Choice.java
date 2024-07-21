package org.app.models;

import java.util.Objects;

public class Choice {

    private String text;
    private String transition;

    public String getText() {
        return text;
    }

    public String getTransition() {
        return transition;
    }

    public void setTextAndTransition(String text, String transition) {
        this.text = text;
        this.transition = transition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return Objects.equals(text, choice.text) && Objects.equals(transition, choice.transition);
    }
}