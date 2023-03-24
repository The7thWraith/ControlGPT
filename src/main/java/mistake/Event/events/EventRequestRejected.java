package mistake.Event.events;

import lombok.Getter;

public class EventRequestRejected {

    @Getter private final int code;
    public EventRequestRejected(int code) {
        this.code = code;
    }
}
