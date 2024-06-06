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
                } else if (entity instanceof EnemyBullet) {
                    System.out.print("| ");
                }else if (entity instanceof ShipBullet) {
                    System.out.print("|| ");
                }
                else {
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

    private void clearConsole() {
        for (int i = 0; i < 7; i++) {
            System.out.println();
        }
    }
}
