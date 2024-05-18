import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

abstract class GameEngine {
    protected Entity[][] gameSpace;
    protected Ship ship = new Ship(-500, -500);
    protected ArrayList<BulletMoving> bulletsList = new ArrayList<>();
    protected ArrayList<Enemy> enemyList = new ArrayList<>();
    protected int gameSpaceWidth;
    protected int gameSpaceHeight;
    protected int iterationCounter = 0;

    public GameEngine(int width, int height) {
        gameSpace = new Entity[width][height];
        init(width, height);
    }

    abstract void view(); // SHOW ONLY NON ZERO HP OBJECTS

    abstract void moveLeft();

    abstract void moveRight();

    void init(int width, int height) {
        gameSpaceWidth = width;
        gameSpaceHeight = height;
        ship.setPosition(width / 2, height - 1); // Ship starts at max possible height
        gameSpace[width / 2][height - 1] = ship;
    }

    void iteration() {
        if (iterationCounter % 2 == 0) {
            Enemy newEnemy = new Enemy((int) (Math.random() * gameSpaceWidth), 0);
            enemyList.add(newEnemy);
            gameSpace[newEnemy.getPositionX()][0] = newEnemy;
        }

        if (iterationCounter % 3 == 0) {
            ShipBullet newShipBullet = new ShipBullet(ship.getPositionX(), ship.getPositionY() - 1);
            bulletsList.add(newShipBullet);
            gameSpace[newShipBullet.getPositionX()][newShipBullet.getPositionY()] = newShipBullet;
        }

        if (iterationCounter % 4 == 0) {
            enemyList.forEach(enemy -> {
                EnemyBullet newEnemyBullet = new EnemyBullet(enemy.getPositionX(), enemy.getPositionY() + 1);
                bulletsList.add(newEnemyBullet);
                gameSpace[newEnemyBullet.getPositionX()][newEnemyBullet.getPositionY()] = newEnemyBullet;
            });
        }

        bulletsList.forEach((BulletMoving::move));
        checkForBulletsCollision();
        iterationCounter++;
    }

    private void checkForBulletsCollision() {
        Iterator<BulletMoving> iterator = bulletsList.iterator();
        while (iterator.hasNext()) {
            BulletMoving bullet = iterator.next();
            int bulletX = bullet.getPositionX();
            int bulletY = bullet.getPositionY();
            if (bulletY >= 0 && bulletY < gameSpaceHeight && gameSpace[bulletX][bulletY] != null) {
                Entity currentEntity = gameSpace[bulletX][bulletY];
                currentEntity.setHealthPoints(currentEntity.getHealthPoints() - 1);
                if (currentEntity.getHealthPoints() <= 0) {
                    gameSpace[bulletX][bulletY] = null;
                    if (currentEntity instanceof Ship) {
                        System.out.println("Game Over!");
                        System.exit(0);
                    }
                }
                iterator.remove(); // Remove the bullet from the list
            }
        }
    }
}

class SpaceInvadersConsole extends GameEngine {
    public SpaceInvadersConsole(int width, int height) {
        super(width, height);
    }

    @Override
    void view() {
        for (int height = 0; height < gameSpaceHeight; height++) {
            for (int width = 0; width < gameSpaceWidth; width++) {
                Entity entity = gameSpace[width][height];
                if (entity instanceof Ship) {
                    System.out.print("S ");
                } else if (entity instanceof Enemy) {
                    System.out.print("E ");
                } else if (entity instanceof BulletMoving) {
                    System.out.print("| ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

    @Override
    void moveLeft() {
        if (ship.getPositionX() > 0) {
            ship.setPosition(ship.getPositionX() - 1, ship.getPositionY());
        }
    }

    @Override
    void moveRight() {
        if (ship.getPositionX() < gameSpaceWidth - 1) {
            ship.setPosition(ship.getPositionX() + 1, ship.getPositionY());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SpaceInvadersConsole gameEngine = new SpaceInvadersConsole(20, 20);
        while (true) {
            gameEngine.iteration();
            gameEngine.view();

            Scanner scanner = new Scanner(System.in);
            char userInput;
            System.out.println("Welcome to Space Invaders!");
            while (true) {
                System.out.println("Press 'A' to move left, 'D' to move right, or 'Q' to quit:");
                userInput = scanner.next().charAt(0);
                switch (Character.toUpperCase(userInput)) {
                    case 'A' -> gameEngine.moveLeft();
                    case 'D' -> gameEngine.moveRight();
                    case 'Q' -> {
                        System.out.println("Quitting game. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    }
                }
                gameEngine.view();
            }
        }
    }
}

abstract class Entity {
    protected int healthPoints;
    protected int positionX;
    protected int positionY;

    public Entity(int healthPoints, int positionX, int positionY) {
        this.healthPoints = healthPoints;
        this.positionX = positionX;
        this.positionY = positionY;
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
}

interface BulletMoving {
    void move();
    int getPositionX();
    int getPositionY();
}

class ShipBullet extends Entity implements BulletMoving {
    public ShipBullet(int positionX, int positionY) {
        super(1, positionX, positionY);
    }

    @Override
    public void move() {
        positionY--;
    }
}

class Ship extends Entity {
    public Ship(int positionX, int positionY) {
        super(5, positionX, positionY);
    }

    public void setPosition(int x, int y) {
        positionX = x;
        positionY = y;
    }
}

class EnemyBullet extends Entity implements BulletMoving {
    public EnemyBullet(int positionX, int positionY) {
        super(1, positionX, positionY);
    }

    @Override
    public void move() {
        positionY++;
    }
}

class Enemy extends Entity {
    public Enemy(int positionX, int positionY) {
        super(2, positionX, positionY);
    }
}
