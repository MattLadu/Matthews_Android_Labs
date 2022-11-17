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
    protected int sendOrReceive;

    boolean isSentButton;

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    public int id;


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
