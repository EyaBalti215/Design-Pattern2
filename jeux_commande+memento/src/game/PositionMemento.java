package game;

public class PositionMemento {
    private final int x;
    private final int y;

    public PositionMemento(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
