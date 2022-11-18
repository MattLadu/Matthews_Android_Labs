package algonquin.cst2335.Matt041049353;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

    @ColumnInfo(name="message")
    protected String message;

    @ColumnInfo(name="timeSent")
    protected String timeSent;

    @ColumnInfo(name = "SendOrReceive")
    protected boolean isSentButton;

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    public int id;

    public ChatMessage(String m, String t, boolean sent){
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public ChatMessage(){}

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
