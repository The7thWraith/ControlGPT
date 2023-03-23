package apiRequest;



public class message {

    public final String text;
    public final messageType type;

    public message(String text, messageType type){
        this.text = text;
        this.type = type;
    }
}
