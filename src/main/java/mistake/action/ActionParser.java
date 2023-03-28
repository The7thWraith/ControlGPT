package mistake.action;

import mistake.data.Action;

public class ActionParser {

    public static Action parseAction(String actionString){
        if(actionString.contains("BASHCOMMAND:") || actionString.contains("ZSHCOMMAND:")){
            return new Action(actionString.substring(actionString.indexOf(":") + 1), ActionType.COMMAND);
        } else if(actionString.contains("SITEEDIT:")){
            return new Action(actionString.substring(actionString.indexOf(":") + 1), ActionType.SITEEDIT);
        } else if(actionString.contains("INTERNET:")){
            return new Action(actionString.substring(actionString.indexOf(":") + 1), ActionType.INTERNET);
        } else {
            return null;
        }
    }
    public static ActionType parseActionToType(String actionString){
        if(actionString.contains("BASHCOMMAND:") || actionString.contains("ZSHCOMMAND:")){
            return ActionType.COMMAND;
        } else if(actionString.contains("SITEEDIT:")){
            return ActionType.SITEEDIT;
        } else if(actionString.contains("INTERNET:")){
            return ActionType.INTERNET;
        } else {
            return null;
        }
    }
}
