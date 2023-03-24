package mistake.Event;

import lombok.Getter;
import lombok.Setter;

public abstract class Event {

    // Getter not necessary here, but it's here for consistency
    @Getter @Setter private EventType type;

    public enum EventType {
        PRE,
        POST
    }

    public boolean isPre() {
        if (type == null) {
            return false;
        }

        return type == EventType.PRE;

    }

    public boolean isPost() {
        if (type == null) {
            return false;
        }

        return type == EventType.POST;
    }
}
