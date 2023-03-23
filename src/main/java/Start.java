import apiRequest.ChatAPIDriver;
import apiRequest.message;
import apiRequest.messageType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Start {
    public static ArrayList<message> messageSeries = new ArrayList<message>(Arrays.asList(new message("You are an AI created by OpenAI", messageType.SYSTEM),
            new message("How do I cook an egg?", messageType.USER)));
    public static void main(String[] args){
        ChatAPIDriver apiDriver = new ChatAPIDriver(messageSeries, 0.7, 256);
        System.out.println(apiDriver.sendRequest());
    }
}
