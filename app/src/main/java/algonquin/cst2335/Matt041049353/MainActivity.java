package algonquin.cst2335.Matt041049353;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( TAG, "In onCreate() - Loading Widgets" );

        Button login = findViewById(R.id.loginButton);
        TextView emailEdit = findViewById(R.id.editEmail);
        TextView passEdit = findViewById(R.id.passwordEdit);
        TextView phoneNum = findViewById(R.id.editNumber);
        Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);

        login.setOnClickListener( clk-> {
            nextPage.putExtra( "EmailAddress", emailEdit.getText().toString() );
          startActivity(nextPage);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w( TAG, "In onStart() - The application is now visible on screen." );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w( TAG, "In onResume() - The application is now responding to user input" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG,"In onPause() - The application no longer responds to user input");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG,"In onStop() - The application is no longer visible.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"In onDestroy() - Any memory used by the application is freed.");
    }


}