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
       Switch myswitch = variableBinding.myswitch;
       RadioButton radioButton = variableBinding.myradio;
       ImageButton imageButton = variableBinding.myimagebutton;

        variableBinding.mybutton.setOnClickListener(click ->{
            String editString = myEdit.getText().toString();
            model.editString.postValue(editString);
          });
        model.editString.observe(this, newStringValue ->{
            variableBinding.mytext.setText("Textview set to: " + newStringValue);
        });

        model.isChecked.observe(this, selected -> {
            variableBinding.mycheck.setChecked(selected);
        variableBinding.myradio.setChecked(selected);
        variableBinding.myswitch.setChecked(selected);
        } ) ;

        check.setOnCheckedChangeListener((chk, isChecked )->{
            model.isChecked.postValue(isChecked);
        });

        radioButton.setOnCheckedChangeListener((rb, isChecked )->{
            model.isChecked.postValue(isChecked);
        });

        myswitch.setOnCheckedChangeListener((sw, isChecked) ->{
            if(myswitch.isChecked()){
                model.isChecked.postValue(isChecked);
                Toast.makeText(getApplicationContext(),"Switched on", Toast.LENGTH_SHORT).show();
            }
            else{
                model.isChecked.postValue(isChecked);
                Toast.makeText(getApplicationContext(),"Switched off", Toast.LENGTH_SHORT).show();
            }
        });

        check.setOnClickListener(click -> {
            if (check.isChecked()){
                Toast.makeText(getApplicationContext(),"Checkbox is checked",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Checkbox is unchecked", Toast.LENGTH_SHORT).show();
            }
        });

        radioButton.setOnClickListener(click ->{
            Toast.makeText(getApplicationContext(),"Radio button clicked", Toast.LENGTH_SHORT).show();
        });

        imageButton.setOnClickListener(click ->{
            Toast.makeText(getApplicationContext(),"The width is: " + imageButton.getMeasuredWidth() +
                    "The height is: " + imageButton.getMeasuredHeight(), Toast.LENGTH_SHORT).show();
        });
    }
}