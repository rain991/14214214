class ShipBullet extends Entity implements BulletMoving {
    public ShipBullet(int positionX, int positionY, Entity[][] gameSpace) {
        super(1, positionX, positionY, gameSpace);
    }

    @Override
    public void move() {
        if (positionY > 0) {
            gameSpace[positionX][positionY] = null;
            positionY--;
            gameSpace[positionX][positionY] = this;
        }
    }
}
