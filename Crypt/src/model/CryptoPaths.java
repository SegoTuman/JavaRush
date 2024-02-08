package model;

public class CryptoPaths {
    private String pathFrom;
    private String pathTo;
    private String resource;
    public CryptoPaths(){
    }

    public String getPathFrom() {
        return pathFrom;
    }

    public void setPathFrom(String pathFrom) {
        this.pathFrom = pathFrom;
    }

    public String getPathTo() {
        return pathTo;
    }

    public void setPathTo(String pathTo) {
        this.pathTo = pathTo;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
    public CryptoPaths(String pathFrom, String pathTo, String resource) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
        this.resource = resource;
    }
}




