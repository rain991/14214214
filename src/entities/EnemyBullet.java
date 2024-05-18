package entities;

public class EnemyBullet extends Entity implements BulletMoving {
    public EnemyBullet() {
        super(1);
    }


    @Override
    public void move(Entity[][] gameSpace, int currentX, int currentY) {
        Entity currentEntity = gameSpace[currentX][currentY];
        if(currentY < gameSpace[][]){
            gameSpace[currentX][currentY+1] = currentEntity;
            gameSpace[currentX][currentY] = null;
        }
    }
}
