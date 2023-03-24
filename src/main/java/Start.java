import apiRequest.ChatAPIDriver;
import apiRequest.Data.IntroString;
import apiRequest.message;
import apiRequest.messageType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Start {
    public static ArrayList<message> messageSeries = new ArrayList<message>(Arrays.asList(new message(IntroString.psychoGPTString, messageType.SYSTEM), new message("INITATE:", messageType.USER)));
    public static void main(String[] args){
        ChatAPIDriver apiDriver = new ChatAPIDriver(messageSeries, 0.7, 256);
        System.out.println(apiDriver.sendRequest());
    }
}