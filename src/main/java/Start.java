import mistake.ControlGPT;
import mistake.Event.Event;
import mistake.Event.events.EventMessageRecieved;
import mistake.apiRequest.ChatAPIDriver;
import mistake.Data.IntroString;
import mistake.apiRequest.message;
import mistake.apiRequest.messageType;
import com.google.common.eventbus.Subscribe;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Start {

    private static ArrayList<message> messageSeries = new ArrayList<message>(Arrays.asList(new message(IntroString.psychoGPTNew, messageType.SYSTEM), new message("DIRECTIVE: ONLY DO SHELL COMMANDS. INITATE:", messageType.USER)));
    public static void main(String[] args){
        ControlGPT.INSTANCE.getBus().register(new Start());
        ChatAPIDriver apiDriver = new ChatAPIDriver(messageSeries, 0.7, 256);
        System.out.println(apiDriver.sendRequest());
    }

    @Subscribe
    private void onEvent(Event e){
        if(e instanceof EventMessageRecieved){
            System.out.println(((EventMessageRecieved) e).getMessage());
        }
    }
}