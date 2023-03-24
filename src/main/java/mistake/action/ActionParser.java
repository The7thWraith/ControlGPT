package mistake.action;

import mistake.Data.Action;

public class ActionParser {
    // ActionParser should take a string formatted as "actionType:mistake.action" and return an Action object

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
}
