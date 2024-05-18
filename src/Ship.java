class Ship extends Entity {
    public Ship(int positionX, int positionY, Entity[][] gameSpace) {
        super(5, positionX, positionY, gameSpace);
    }

    public void setPosition(int x, int y) {
        gameSpace[positionX][positionY] = null;
        positionX = x;
        positionY = y;
        gameSpace[positionX][positionY] = this;
    }
}
