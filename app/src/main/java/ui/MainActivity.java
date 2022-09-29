package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.Matt041049353.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

       Button btn = variableBinding.mybutton;
       TextView myText = variableBinding.mytext;
       EditText myEdit = variableBinding.myedittext;
       CheckBox check = variableBinding.mycheck;
       Switch switch1 = variableBinding.myswitch;
       RadioButton radioButton = variableBinding.myradio;
       ImageButton imageButton = variableBinding.myimagebutton;

        variableBinding.mybutton.setOnClickListener(click ->{
            String editString = myEdit.getText().toString();
            variableBinding.mytext.setText("Textview set to: " + editString);
        });

        check.setOnClickListener(click -> {
           if (check.isChecked()){
               Toast.makeText(getApplicationContext(),"Checkbox is checked",Toast.LENGTH_SHORT).show();
           }
           else{
               Toast.makeText(getApplicationContext(),"Checkbox is unchecked", Toast.LENGTH_SHORT).show();
           }
        });
    }
}