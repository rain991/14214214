package entities;

public abstract class Entity {
    protected int healthPoints;;

    public Entity(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
