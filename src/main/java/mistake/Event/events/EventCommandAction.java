package mistake.Event.events;

import lombok.Getter;
import mistake.Event.Event;

public class EventCommandAction extends Event {

    @Getter String command;

    public EventCommandAction(String command) {
        this.command = command;
    }
}