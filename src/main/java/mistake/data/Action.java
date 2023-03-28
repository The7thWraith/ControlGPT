package mistake.data;

import lombok.Getter;
import mistake.action.ActionType;

public class Action {

    @Getter
    public ActionType actionType;
    @Getter public String action;

    public Action(String action, ActionType actionType){
        this.action = action;
        this.actionType = actionType;

    }
}
