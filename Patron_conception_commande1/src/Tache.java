public class Tache {
    private String description;
    private boolean complete;

    public Tache(String description) {
        this.description = description;
        this.complete = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return description + (complete ? " [Complétée]" : " [Incomplète]");
    }
}
