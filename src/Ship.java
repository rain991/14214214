class Ship extends Entity {
    public Ship(int positionX, int positionY, int gameSpaceHeight, Entity[][] gameSpace) {
        super(5, positionX, positionY, gameSpaceHeight, gameSpace);
    }

    public void setPosition(int x, int y) {
        gameSpace[positionX][positionY] = null;
        positionX = x;
        positionY = y;
        gameSpace[positionX][positionY] = this;
    }
}
