package algonquin.cst2335.Matt041049353;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.Matt041049353.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment{

    ChatMessage selected;
    public MessageDetailsFragment(ChatMessage m){
        selected = m;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);

        binding.messageText.setText(selected.message);
        binding.timeSent.setText(selected.timeSent);
        binding.isSentOrReceive.setText("isSent: "+selected.isSentButton);
        binding.databaseID.setText("ID = "+selected.id);
        return binding.getRoot();
    }
}
