package entities;

public class ShipBullet extends Entity implements BulletMoving {
    public ShipBullet() {
        super(1);
    }

    @Override
    public void move(Entity[][] gameSpace, int currentX, int currentY) {
        Entity currentEntity = gameSpace[currentX][currentY];
        if(currentY != 0){
            gameSpace[currentX][currentY-1] = currentEntity;
            gameSpace[currentX][currentY] = null;
        }
    }
}
