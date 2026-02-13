package memento;

public class SettingsMemento {
    private final int volume;
    private final int brightness;
    private final boolean darkMode;

    public SettingsMemento(int volume, int brightness, boolean darkMode) {
        this.volume = volume;
        this.brightness = brightness;
        this.darkMode = darkMode;
    }

    int getVolume() {
        return volume;
    }

    int getBrightness() {
        return brightness;
    }

    boolean isDarkMode() {
        return darkMode;
    }
}
