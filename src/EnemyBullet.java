class EnemyBullet extends Entity implements BulletMoving {
    public EnemyBullet(int positionX, int positionY, Entity[][] gameSpace) {
        super(1, positionX, positionY, gameSpace);
    }

    @Override
    public void move() {
        if (positionY < GameEngine.gameSpaceHeight - 1) {
            gameSpace[positionX][positionY] = null;
            positionY++;
            gameSpace[positionX][positionY] = this;
        }
    }
}
