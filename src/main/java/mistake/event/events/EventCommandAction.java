package mistake.event.events;

import lombok.Getter;
import mistake.event.Event;

public class EventCommandAction extends Event {

    @Getter String command;

    public EventCommandAction(String command) {
        this.command = command;
    }
}