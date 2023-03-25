package mistake.event.events;

import lombok.Getter;
import mistake.event.Event;

public class EventRequestRejected extends Event {

    @Getter private final int code;
    public EventRequestRejected(int code) {
        this.code = code;
    }
}
