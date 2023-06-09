package mistake.apiRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import lombok.Getter;
import mistake.ControlGPT;
import mistake.event.Event;
import mistake.event.events.EventMessageRecieved;
import mistake.event.events.EventRequestRejected;
import mistake.event.events.EventSendRequest;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatAPIDriver {
    @Getter private final String model = "gpt-4";

    // This API key is disabled so don't try
    @Getter private final String api_key = "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";
    @Getter private final double temperature;
    @Getter private final int max_tokens;
    @Getter private String jsonResponse;
    @Getter private String outputText;

    public ChatAPIDriver(double temperature, int max_tokens){
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }

    public String sendRequest( List<message> messageSeries) {
        try {

            JSONObject request = new JSONObject();

            // Running out of variable names here
            JSONArray sentMessages = new JSONArray();
            for (message m : messageSeries) {
                JSONObject singleMessage = new JSONObject();
                singleMessage.put("role", m.type.toString().toLowerCase());
                singleMessage.put("content", m.text);
                sentMessages.put(singleMessage);
            }


            request.put("model", this.model);
            request.put("messages", sentMessages);
            request.put("temperature", this.temperature);
            request.put("max_tokens", this.max_tokens);
            this.jsonResponse = request.toString();

            // Pre Event fire
            EventSendRequest sendRequest = new EventSendRequest(request.toString());
            sendRequest.setType(Event.EventType.PRE);
            ControlGPT.INSTANCE.getBus().post(sendRequest);

            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + api_key);
            con.setDoOutput(true);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(request.toString().getBytes("UTF-8"));
            outputStream.close();

            // Post event fire
            sendRequest.setType(Event.EventType.POST);
            ControlGPT.INSTANCE.getBus().post(sendRequest);

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                con.disconnect();
                try {
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray choices = jsonResponse.getJSONArray("choices");
                    JSONObject choices2 = new JSONObject(choices.get(0).toString());
                    JSONObject choices3 = new JSONObject(choices2.get("message").toString());

                    // TODO: Add a Pre and Post event for this to allow for custom handling/editing of the response
                    ControlGPT.INSTANCE.getBus().post(new EventMessageRecieved(choices3.get("content").toString()));
                    return choices3.get("content").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    return response.toString();
                }
            } else {
                System.out.println("Rejected/Failed: " + responseCode);
                ControlGPT.INSTANCE.getBus().post(new EventRequestRejected(responseCode));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
