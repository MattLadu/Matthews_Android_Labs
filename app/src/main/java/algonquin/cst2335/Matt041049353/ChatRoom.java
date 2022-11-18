package algonquin.cst2335.Matt041049353;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.Matt041049353.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.Matt041049353.databinding.RecieveMessageBinding;
import algonquin.cst2335.Matt041049353.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages;
    private RecyclerView.Adapter myAdapter;
    ChatRoomViewModel chatModel;
    ChatMessageDAO mDA0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        mDA0 = db.cmDAO();

        if(messages == null) {
            chatModel.messages.setValue(messages = new ArrayList<>());
            Executor thread = Executors.newSingleThreadExecutor();

            thread.execute(() ->
            {
                messages.addAll( mDA0.getAllMessages() );
                runOnUiThread( () ->  binding.RecyclerView.setAdapter( myAdapter ));
            });
        }

        binding.sendButton.setOnClickListener(click -> {
            String input = binding.textInput.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            ChatMessage cm = new ChatMessage(input,currentDateandTime,true);
            messages.add(cm);

            myAdapter.notifyItemInserted(messages.size()-1);
            binding.textInput.setText("");
        });

        binding.recieveButton.setOnClickListener(click -> {
            String input = binding.textInput.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            ChatMessage cm = new ChatMessage(input,currentDateandTime,true);
            messages.add(cm);

            myAdapter.notifyItemInserted(messages.size()-1);
            binding.textInput.setText("");
        });

        binding.RecyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if(viewType == 0){
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                } else{
                    RecieveMessageBinding binding = RecieveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.getMessage());
                holder.timeText.setText(obj.getTime());
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }
            @Override
            public int getItemViewType(int position){
                ChatMessage obj = messages.get(position);
                return obj.getIsSent() ? 0 : 1;
            }
        });

        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(click -> {
                int position = getAbsoluteAdapterPosition();

                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );

                builder.setMessage("Do you want to delete the message: " + messageText.getText ())
                       .setTitle("Question: ")
                       .setNegativeButton("No", (dialog, cl) -> { })
                       .setPositiveButton("Yes", (dialog, cl) -> {
                    ChatMessage m = messages.get(position);
                    Executor thread = Executors.newSingleThreadExecutor();
                    thread.execute(() ->{
                        mDA0.deleteMessage(m);
                    });
                    messages.remove(position);
                    myAdapter.notifyItemRemoved(position);

                           Snackbar.make(messageText, "You deleted message #" + position, Snackbar.LENGTH_LONG)
                                   .setAction("Undo", clk -> {
                                       messages.add(position,m);
                                       myAdapter.notifyItemInserted(position);
                                   })
                                   .show();
                })
                       .create().show();

            });
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);


        }
    }
}

