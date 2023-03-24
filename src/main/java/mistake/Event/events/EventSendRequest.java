package mistake.Event.events;

import lombok.Getter;

public class EventSendRequest extends mistake.Event.Event {
    @Getter
    private final String request;

    public EventSendRequest(String request) {
        this.request = request;
    }
}
