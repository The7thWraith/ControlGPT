package mistake.Event.events;

import lombok.Getter;
import mistake.Event.Event;

public class EventCommandError extends Event {

    @Getter String commandError;

    public EventCommandError(String commandError) {
        this.commandError = commandError;
    }
}
