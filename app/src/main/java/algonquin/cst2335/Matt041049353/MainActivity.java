package algonquin.cst2335.Matt041049353;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**This class uses password checking functions to verify the user inputted password
 * @author mattladeroute
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the text at the center of the screen */
    private TextView tv = null;
    /** This holds the edit text */
    private EditText et = null;
    /** This holds the button */
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button1);

        btn.setOnClickListener( clk ->{
            String password = et.getText().toString();
            if(checkPasswordComplexity(password)){
                et.setText("meets the requirements");
            }else{
                et.setText("You shall not pass!");
            }
        });
    }

    /** This function checks to validate the password the user enters
     *
     * @param pw The String object that we are checking
     * @return Returns true if the password meets all the requirements
     */
    boolean checkPasswordComplexity(String pw){
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for(int i =0; i< pw.length(); i++){
            char c = pw.charAt(i);
            if(Character.isUpperCase(c)){
                foundUpperCase = true;
            }
            else if(Character.isLowerCase(c)){
                foundLowerCase = true;
            }
            else if(Character.isDigit(c)){
                foundNumber = true;
            }
            else if(isSpecialCharacter(c)){
                foundSpecial = true;
            }
        }

        if(!foundUpperCase)
        {
            Toast.makeText(this, "Missing an uppercase letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundLowerCase)
        {
            Toast.makeText(MainActivity.this, "Missing a lowercase letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundNumber) {
            Toast.makeText(MainActivity.this, "Missing a number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundSpecial) {
            Toast.makeText(MainActivity.this, "Missing a special character", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true; //only get here if they're all true

    }

    /**This function is to validate that a special character was used in the password
     *
     * @param c password entered by user
     * @return true if a special character was used, false is default
     */
    boolean isSpecialCharacter(char c){
        switch(c){
            case '#':
            case '?':
            case '*':
            case '@':
            case '$':
            case '%':
            case '^':
            case '!':
            case '&':
                return true;
            default:
                return false;
        }
    }

}