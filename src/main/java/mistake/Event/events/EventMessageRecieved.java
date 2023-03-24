package mistake.Event.events;

import lombok.Getter;
import mistake.Event.Event;

public class EventMessageRecieved extends Event {
    @Getter private final String message;

    public EventMessageRecieved(String message) {
        this.message = message;
    }
}
