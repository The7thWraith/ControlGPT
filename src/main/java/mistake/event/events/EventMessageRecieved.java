package mistake.event.events;

import lombok.Getter;
import mistake.event.Event;

public class EventMessageRecieved extends Event {
    @Getter private final String message;

    public EventMessageRecieved(String message) {
        this.message = message;
    }
}
