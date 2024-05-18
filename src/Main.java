import entities.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

abstract class GameEngine {
    static public Entity[][] gameSpace;
    protected Ship ship;
    protected ArrayList<BulletMoving> bulletsList;
    protected ArrayList<Enemy> enemyList;
    static protected int gameSpaceWidth;
    static protected int gameSpaceHeight;
    protected int iterationCounter = 0;

    public GameEngine(int width, int height) {
        gameSpaceWidth = width;
        gameSpaceHeight = height;
        gameSpace = new Entity[width][height];
        ship = new Ship();
        bulletsList = new ArrayList<>();
        enemyList = new ArrayList<>();
        init();
    }

    abstract void view();

    abstract void moveLeft();

    abstract void moveRight();

    void init() {
        ship.setPosition(gameSpaceWidth / 2, gameSpaceHeight - 1);
        gameSpace[gameSpaceWidth / 2][gameSpaceHeight - 1] = ship;
    }

    void iteration() {
        if (iterationCounter % 2 == 0) {
            Enemy newEnemy = new Enemy();
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
                iterator.remove();
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
        clearConsole();
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
        int shipX = ship.getPositionX();
        if (shipX > 0) {
            gameSpace[shipX][ship.getPositionY()] = null;
            ship.setPosition(shipX - 1, ship.getPositionY());
            gameSpace[ship.getPositionX()][ship.getPositionY()] = ship;
        }
    }

    @Override
    void moveRight() {
        int shipX = ship.getPositionX();
        if (shipX < gameSpaceWidth - 1) {
            gameSpace[shipX][ship.getPositionY()] = null;
            ship.setPosition(shipX + 1, ship.getPositionY());
            gameSpace[ship.getPositionX()][ship.getPositionY()] = ship;
        }
    }

    private void clearConsole() {
        for (int i = 0; i < 7; i++) {
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SpaceInvadersConsole gameEngine = new SpaceInvadersConsole(20, 20);
        Thread userInputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            char userInput;
            while (true) {
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
            }
        });
        userInputThread.start(); // Start the thread
        while (true) {
            gameEngine.iteration();
            gameEngine.view();
            try {
                Thread.sleep(2000); // Delay for 1.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

