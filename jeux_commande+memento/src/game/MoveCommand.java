package game;

public class MoveCommand implements Command {
    private final Position position;
    private final int dx;
    private final int dy;

    public MoveCommand(Position position, int dx, int dy) {
        this.position = position;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        position.move(dx, dy);
    }
}
