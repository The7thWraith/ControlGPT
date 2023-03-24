package mistake.Data;

import mistake.action.ActionType;

public class Action {

    public ActionType actionType;
    public String action;

    public Action(String action, ActionType actionType){
        this.action = action;
        this.actionType = actionType;

    }
}
