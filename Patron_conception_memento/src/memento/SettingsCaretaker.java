package memento;

public class SettingsCaretaker {
    private SettingsMemento memento;

    public void save(Settings settings) {
        if (settings == null) {
            return;
        }
        memento = settings.save();
    }

    public void restore(Settings settings) {
        if (settings == null) {
            return;
        }
        settings.restore(memento);
    }

    public boolean hasSavedState() {
        return memento != null;
    }
}
