package algonquin.cst2335.Matt041049353;

public class ChatMessage {

    String message;
    String timeSent;
    boolean isSentButton;

    public ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage(){
        return message;
    }

    public String getTime(){
        return timeSent;
    }

    public boolean getIsSent(){
        return isSentButton;
    }
}
