package algonquin.cst2335.Matt041049353;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        TextView emailTextView = findViewById(R.id.textViewSecond);
        emailTextView.setText("Welcome Back " + emailAddress);

        Button call = findViewById(R.id.callNumnber);
        EditText pNumber = findViewById(R.id.editNumber);
        Intent calling = new Intent(Intent.ACTION_DIAL);
        calling.setData(Uri.parse("tel:" + pNumber));
        call.setOnClickListener( clk ->{
            startActivity(calling);
        });

        ImageView pic = findViewById(R.id.imageView);
        Button changePic = findViewById(R.id.changePictureButton);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        changePic.setOnClickListener( clk ->{
            ActivityResultLauncher<Intent> cameraResult = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Bitmap thumbnail = data.getParcelableExtra("data");
                                pic.setImageBitmap( thumbnail );

                            }
                        }
                    }
            );
            cameraResult.launch(cameraIntent);
            startActivity(cameraIntent);
        });



    }


}