package mistake.Event.events;

import lombok.Getter;
import mistake.Event.Event;

public class EventRequestRejected extends Event {

    @Getter private final int code;

    public EventRequestRejected(int code) {
        this.code = code;
    }
}
