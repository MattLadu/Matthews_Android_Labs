package algonquin.cst2335.Matt041049353;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

        ImageView imgView;
        Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.flag);
        sw = findViewById(R.id.switch_spin);

        sw.setOnCheckedChangeListener((btn, isChecked)->{
            if (isChecked) {

                RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,0.5f);
                rotate.setDuration(3000);
                rotate.setRepeatCount(Animation.INFINITE);
                rotate.setInterpolator(new LinearInterpolator());

                imgView.startAnimation(rotate);
            }
            else{
                imgView.clearAnimation();
            }
        });
    }
}