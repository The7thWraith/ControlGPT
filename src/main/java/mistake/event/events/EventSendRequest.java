package mistake.event.events;
import mistake.event.Event;

import lombok.Getter;

public class EventSendRequest extends Event {

    @Getter private final String request;

    public EventSendRequest(String request) {
        this.request = request;
    }
}
