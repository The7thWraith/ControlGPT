package mistake;

import mistake.action.ActionDriver;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import mistake.apiRequest.ChatAPIDriver;

public enum ControlGPT {
    INSTANCE;

    @Getter private ActionDriver actionDriver = new ActionDriver();
    @Getter private ChatAPIDriver apiDriver = new ChatAPIDriver(0.7, 256);

    // implement a google guava eventbus
    @Getter private final EventBus bus = new EventBus("mainBus");



}
