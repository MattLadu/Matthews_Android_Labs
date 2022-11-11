package algonquin.cst2335.Matt041049353;

public class ChatMessage {

    String message;
    String timeSent;
    boolean isSentButton;

    void ChatRoom(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage(String message){
        return this.message;
    }

    public String getTime(String timeSent){
        return this.timeSent;
    }

    public boolean getIsSent(boolean isSentButton){
        return this.isSentButton;
    }
}
