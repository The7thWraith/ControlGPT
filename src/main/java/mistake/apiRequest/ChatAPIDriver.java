package mistake.apiRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mistake.ControlGPT;
import mistake.Data.Action;
import mistake.Event.events.EventMessageRecieved;
import mistake.Event.events.EventRequestRejected;
import mistake.Event.events.EventSendRequest;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatAPIDriver {
    private final String model = "gpt-4";
    private List<message> messageSeries;
    private final String api_key = "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";
    private final double temperature;
    private final int max_tokens;
    private String jsonResponse;
    private String outputText;

    public ChatAPIDriver(ArrayList<message> messageSeries, double temperature, int max_tokens){
        this.messageSeries = messageSeries;
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }

    public String sendRequest(){
        try {

            JSONObject request = new JSONObject();

            // Running out of variable names here
            JSONArray sentMessages = new JSONArray();
            for (message m : messageSeries){
                JSONObject singleMessage = new JSONObject();
                singleMessage.put("role", m.type.toString().toLowerCase());
                singleMessage.put("content", m.text);
                sentMessages.put(singleMessage);
            }

            request.put("model", this.model);
            request.put("messages", sentMessages);
            request.put("temperature", this.temperature);
            request.put("max_tokens", this.max_tokens);
            System.out.println(request);
            this.jsonResponse = request.toString();

            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + api_key);
            con.setDoOutput(true);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(request.toString().getBytes("UTF-8"));
            outputStream.close();
            ControlGPT.INSTANCE.getBus().post(new EventSendRequest(request.get("content").toString()));

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK) {
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
                    ControlGPT.INSTANCE.getBus().post(new EventMessageRecieved(choices3.get("content").toString()));
                    return choices3.get("content").toString();
                }
                catch (Exception e){
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








    // Getters and Setters
    public String getModel() {
        return model;
    }

    public List<message> getMessageSeries() {
        return messageSeries;
    }

    public void setMessageSeries(List<message> messageSeries) {
        this.messageSeries = messageSeries;
    }

    public String getApi_key() {
        return api_key;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getMax_tokens() {
        return max_tokens;
    }
}
