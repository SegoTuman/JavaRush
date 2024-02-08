package model;

public class CryptoModel {
    public enum Mode{
        ENCODE,
        DECODE
    }
    private String pathFrom;
    private String pathTo;
    private int key;

    private Mode mode;

    public String getPathFrom() {

        return pathFrom;
    }

    public String getPathTo() {

        return pathTo;
    }

    public int getKey() {

        return key;
    }

    public Mode getMode() {
        return mode;
    }

    public void setPathFrom(String pathFrom) {

        this.pathFrom = pathFrom;
    }

    public void setPathTo(String pathTo) {

        this.pathTo = pathTo;
    }

    public void setKey(int key) {

        this.key = key;
    }

    public void setMode(int value) {
        switch (value){
            case 1 -> this.mode = Mode.ENCODE;
            case 2 -> this.mode = Mode.DECODE;
        }
    }


    public CryptoModel() {
    }

    public CryptoModel(String pathFrom, String pathTo, int key, Mode mode) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
        this.key = key;
        this.mode = mode;
    }
}
