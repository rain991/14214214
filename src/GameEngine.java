import java.util.ArrayList;
import java.util.Iterator;

abstract class GameEngine {
    protected Entity[][] gameSpace;
    protected Ship ship;
    protected ArrayList<BulletMoving> bulletsList = new ArrayList<>();
    static protected int gameSpaceWidth;
    static protected int gameSpaceHeight;
    protected int iterationCounter = 0;

    public GameEngine(int width, int height) {
        this.gameSpace = new Entity[width][height];
        this.ship = new Ship(0, 0, gameSpaceHeight, this.gameSpace);
        init(width, height);
    }

    abstract void view(); // SHOW ONLY NON ZERO HP OBJECTS

    abstract void moveLeft();

    abstract void moveRight();

    void init(int width, int height) {
        gameSpaceWidth = width;
        gameSpaceHeight = height;
        gameSpace[width / 2][height - 1] = ship;
        ship.setPosition(width / 2, height - 1);
    }

    void iteration() {
        if (iterationCounter % 2 == 0) {
            Enemy newEnemy = new Enemy((int) (Math.random() * gameSpaceWidth), 0, gameSpaceHeight, gameSpace);
            gameSpace[newEnemy.getPositionX()][0] = newEnemy;
        }

        if (iterationCounter % 2 == 0) {
            ShipBullet newShipBullet = new ShipBullet(ship.getPositionX(), ship.getPositionY() - 1, gameSpaceHeight, gameSpace);
            bulletsList.add(newShipBullet);
            gameSpace[newShipBullet.getPositionX()][newShipBullet.getPositionY()] = newShipBullet;
        }

        if (iterationCounter % 4 == 0) {
            for (int i = 0; i < gameSpaceWidth; i++) {
                if (gameSpace[i][0] != null) {
                    EnemyBullet newEnemyBullet = new EnemyBullet(i, 1, gameSpaceHeight, gameSpace);
                    bulletsList.add(newEnemyBullet);
                    gameSpace[newEnemyBullet.getPositionX()][newEnemyBullet.getPositionY()] = newEnemyBullet;
                }
            }
        }
        //  ship.moveEntity((Entity) bullet, bullet.getPositionX(), bullet.getPositionY());
        bulletsList.forEach(BulletMoving::move);
        checkForBulletsCollision();
        iterationCounter++;
    }

    private void checkForBulletsCollision() {
        Iterator<BulletMoving> iterator = bulletsList.iterator();
        while (iterator.hasNext()) {
            BulletMoving bullet = iterator.next();
            int bulletX = bullet.getPositionX();
            int bulletY = bullet.getPositionY();
            if (bulletY == 0 && bulletY == (gameSpaceHeight - 1) && gameSpace[bulletX][bulletY] != null) {  // bulletY >= 0 && bulletY < gameSpaceHeight && gameSpace[bulletX][bulletY] != null
                Entity currentEntity = gameSpace[bulletX][bulletY];
                currentEntity.setHealthPoints(currentEntity.getHealthPoints() - 1);
                if (currentEntity.getHealthPoints() <= 0) {
                    gameSpace[bulletX][bulletY] = null;
                    if (currentEntity instanceof Ship) {
                        System.out.println("Game Over!");
                        System.exit(0);
                    }
                }
                iterator.remove();
            }
        }
    }
}
