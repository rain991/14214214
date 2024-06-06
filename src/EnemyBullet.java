class EnemyBullet extends Entity implements BulletMoving, HitObjectCallback, EndGameCallback {
    public EnemyBullet(int positionX, int positionY,int gameSpaceHeight, Entity[][] gameSpace) {
        super(1, positionX, positionY,gameSpaceHeight ,gameSpace);
    }

    @Override
    public void move() {
        if (positionY < GameEngine.gameSpaceHeight - 1) {
            gameSpace[positionX][positionY] = null;
            positionY++;
            if (gameSpace[positionX][positionY] instanceof Ship || gameSpace[positionX][positionY] instanceof Enemy) {
                onHit(gameSpace, positionX, positionY);
            } else if(positionY == gameSpaceHeight){
                // doing nothing, instance is null now
            }else{
                gameSpace[positionX][positionY] = this;
            }
        }
    }

    @Override
    public void onHit(Entity[][] gamespace, int x, int y) {
        Entity currentGamespaceEntity = gamespace[x][y];
        if ((currentGamespaceEntity instanceof Ship || currentGamespaceEntity instanceof Enemy)) {
            currentGamespaceEntity.setHealthPoints(getHealthPoints() - 1);
            if (currentGamespaceEntity.healthPoints == 0 && currentGamespaceEntity instanceof Ship) {
                onEndGame();
            }
            if (currentGamespaceEntity.healthPoints == 0 && currentGamespaceEntity instanceof Ship) {
                gamespace[x][y] = null;
            }
        }
    }

    @Override
    public void onEndGame() {
        System.out.println("Game over");
        System.exit(0);
    }
}
