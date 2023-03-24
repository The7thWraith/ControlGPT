package mistake.Event.events;

import lombok.Getter;

public class EventMessageRecieved {
    @Getter private final String message;

    public EventMessageRecieved(String message) {
        this.message = message;
    }
}
