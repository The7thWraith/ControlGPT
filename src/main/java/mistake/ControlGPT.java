package mistake;

import mistake.action.ActionDriver;
import com.google.common.eventbus.EventBus;
import lombok.Getter;

public enum ControlGPT {
    INSTANCE;

    @Getter private ActionDriver actionDriver = new ActionDriver();

    // implement a google guava eventbus
    @Getter private EventBus bus = new EventBus("mainBus");



}
