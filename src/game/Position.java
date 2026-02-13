package game;

public class Position {
    private final int size;
    private int x;
    private int y;

    public Position(int size, int startX, int startY) {
        this.size = size;
        this.x = clamp(startX);
        this.y = clamp(startY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int dx, int dy) {
        int nextX = clamp(x + dx);
        int nextY = clamp(y + dy);
        x = nextX;
        y = nextY;
    }

    public PositionMemento save() {
        return new PositionMemento(x, y);
    }

    public void restore(PositionMemento memento) {
        if (memento == null) {
            return;
        }
        this.x = clamp(memento.getX());
        this.y = clamp(memento.getY());
    }

    private int clamp(int value) {
        if (value < 0) {
            return 0;
        }
        if (value >= size) {
            return size - 1;
        }
        return value;
    }
}
