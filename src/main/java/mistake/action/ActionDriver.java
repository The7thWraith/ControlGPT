package mistake.action;

import mistake.Data.Action;

import java.io.IOException;
import java.util.ArrayList;

public class ActionDriver {
    // This is where it gets dangerous
    ArrayList<String> siteCommandList = new ArrayList<String>();
    ArrayList<String> internetActionList = new ArrayList<String>();

    // Take an mistake.action string and either execute it or turn it into a GPT-4 command to turn the internet mistake.action into a command string
    public void executeAction(Action action){
        if(action.actionType.equals(ActionType.COMMAND)){
            try{
                runCommand(action.action);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else if(action.actionType.equals(ActionType.SITEEDIT)){
            siteCommandList.add(action.action);

        } else if(action.actionType.equals(ActionType.INTERNET)){
            // Execute the internet mistake.action
            internetActionList.add(action.action);
        }
    }

    // Write a function to run a command string in the terminal
    public void runCommand(String command) throws IOException {
        // Run the command
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command);
    }


}
