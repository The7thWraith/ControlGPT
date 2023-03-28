import com.google.common.eventbus.Subscribe;
import mistake.ControlGPT;
import mistake.action.ActionParser;
import mistake.action.ActionType;
import mistake.data.Action;
import mistake.data.IntroString;
import mistake.event.Event;
import mistake.event.events.EventMessageRecieved;
import mistake.event.events.EventSendRequest;
import mistake.apiRequest.message;
import mistake.apiRequest.messageType;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;


public class Start {

    int messageNumber = 0;
    private static ArrayList<message> messageSeries = new ArrayList<message>(Arrays.asList(new message(IntroString.psychoGPTNew, messageType.SYSTEM)));
    public static void main(String[] args){
        ControlGPT.INSTANCE.getBus().register(new Start());
        ControlGPT.INSTANCE.getApiDriver().sendRequest(messageSeries);
    }

    // Google Guava Eventbus
    @Subscribe
    private void onEvent(Event e){
        if(e instanceof EventMessageRecieved){
            if(messageNumber <= 5) {
                System.out.println(((EventMessageRecieved) e).getMessage());
                messageSeries.add(new message(((EventMessageRecieved) e).getMessage(), messageType.ASSISTANT));

                Action action = new Action(((EventMessageRecieved) e).getMessage(), ActionParser.parseActionToType(((EventMessageRecieved) e).getMessage()));


                System.out.println(ActionParser.parseAction(((EventMessageRecieved) e).getMessage()));

                ControlGPT.INSTANCE.getApiDriver().sendRequest(messageSeries);

                // ONLY INCLUDE THIS LINE IF YOU WANT CHATGPT TO DO CONTROL YOUR COMPUTER
                messageSeries.add(
                        new message(ControlGPT.INSTANCE.getActionDriver().executeAction(action), messageType.USER)
                );

                // ControlGPT.INSTANCE.getActionDriver().executeAction(ActionParser.parseAction(((EventMessageRecieved) e).getMessage()));
                messageNumber++;
            }
        }
        if(e instanceof EventSendRequest){
        }
    }
}