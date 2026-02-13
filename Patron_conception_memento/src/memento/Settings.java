package memento;

public class Settings {
    private int volume;
    private int brightness;
    private boolean darkMode;

    public Settings(int volume, int brightness, boolean darkMode) {
        this.volume = clamp(volume);
        this.brightness = clamp(brightness);
        this.darkMode = darkMode;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = clamp(volume);
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = clamp(brightness);
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public SettingsMemento save() {
        return new SettingsMemento(volume, brightness, darkMode);
    }

    public void restore(SettingsMemento memento) {
        if (memento == null) {
            return;
        }
        this.volume = memento.getVolume();
        this.brightness = memento.getBrightness();
        this.darkMode = memento.isDarkMode();
    }

    public String describe() {
        return "Volume : " + volume + ", Luminosite : " + brightness
            + ", Mode sombre : " + (darkMode ? "Active" : "Desactive");
    }

    private int clamp(int value) {
        if (value < 0) {
            return 0;
        }
        if (value > 100) {
            return 100;
        }
        return value;
    }
}
