package mistake.action;

import mistake.ControlGPT;
import mistake.data.Action;
import mistake.event.events.EventCommandAction;
import mistake.event.events.EventCommandError;
import mistake.event.events.EventSendRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ActionDriver {
    // This is where it gets dangerous
    ArrayList<String> siteCommandList = new ArrayList<String>();
    ArrayList<String> internetActionList = new ArrayList<String>();

    public String executeAction(Action action){
        if(action.actionType.equals(ActionType.COMMAND)){
            try{
                return (execCmd(action.action));
            } catch (IOException e){
                ControlGPT.INSTANCE.getBus().post(new EventCommandError(Arrays.toString(e.getStackTrace())));
                e.printStackTrace();
            }
        } else if(action.actionType.equals(ActionType.SITEEDIT)){
            siteCommandList.add(action.action);

        } else if(action.actionType.equals(ActionType.INTERNET)){
            // Execute the internet mistake.action
            internetActionList.add(action.action);
        }
        return "Error";
    }
    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
