package game;

import java.util.ArrayDeque;
import java.util.Deque;

public class PositionCaretaker {
    private final Deque<PositionMemento> history = new ArrayDeque<>();

    public void save(Position position) {
        if (position == null) {
            return;
        }
        history.push(position.save());
    }

    public PositionMemento popLast() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }

    public boolean hasHistory() {
        return !history.isEmpty();
    }

    public int size() {
        return history.size();
    }
}
