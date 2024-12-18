package abstracts;

public abstract class Entity {
    protected Double weight;
    protected Integer maxCountOnField;
    protected Integer speed;
    protected Double kgToFullEating;
    protected Integer x;
    protected Integer y;
    protected Entity(Double weight, Integer maxCountOnField, Integer speed, Double kgToFullEating) {
        this.weight = weight;
        this.maxCountOnField = maxCountOnField;
        this.speed = speed;
        this.kgToFullEating = kgToFullEating;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getMaxCountOnField() {
        return maxCountOnField;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Double getKgToFullEating() {
        return kgToFullEating;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setMaxCountOnField(Integer maxCountOnField) {
        this.maxCountOnField = maxCountOnField;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setKgToFullEating(Double kgToFullEating) {
        this.kgToFullEating = kgToFullEating;
    }
}
