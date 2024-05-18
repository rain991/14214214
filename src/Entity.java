import java.util.Arrays;

abstract class Entity {
    protected int healthPoints;
    protected int positionX;
    protected int positionY;
    protected Entity[][] gameSpace;


    public Entity(int healthPoints, int positionX, int positionY, Entity[][] gameSpace) {
        this.healthPoints = healthPoints;
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameSpace = gameSpace;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPosition(int x, int y) {
        positionX = x;
        positionY = y;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "healthPoints=" + healthPoints +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", gameSpace=" + Arrays.toString(gameSpace) +
                '}';
    }
}
