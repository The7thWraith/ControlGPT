package mistake.event.events;

import lombok.Getter;
import mistake.event.Event;

public class EventCommandError extends Event {

    @Getter String commandError;

    public EventCommandError(String commandError) {
        this.commandError = commandError;
    }
}
