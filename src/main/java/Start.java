import com.google.common.eventbus.Subscribe;
import mistake.ControlGPT;
import mistake.action.ActionParser;
import mistake.data.IntroString;
import mistake.event.Event;
import mistake.event.events.EventMessageRecieved;
import mistake.event.events.EventSendRequest;
import mistake.apiRequest.message;
import mistake.apiRequest.messageType;

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
                messageSeries.add(new message( "Command not recognized", messageType.USER));
                System.out.println(ActionParser.parseAction(((EventMessageRecieved) e).getMessage()));
                ControlGPT.INSTANCE.getApiDriver().sendRequest(messageSeries);

                // ONLY INCLUDE THIS LINE IF YOU WANT CHATGPT TO DO CONTROL YOUR COMPUTER
                // ControlGPT.INSTANCE.getActionDriver().executeAction(ActionParser.parseAction(((EventMessageRecieved) e).getMessage()));
                messageNumber++;
            }
        }
        if(e instanceof EventSendRequest){
        }
    }
}