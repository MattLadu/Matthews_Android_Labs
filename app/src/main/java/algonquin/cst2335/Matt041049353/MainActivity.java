package algonquin.cst2335.Matt041049353;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import algonquin.cst2335.Matt041049353.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    protected String cityName;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(click -> {
            cityName = binding.editText.getText().toString();
            String stringURL = null;
            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                        + URLEncoder.encode(cityName, "UTF-8")
                        + "&appid=053841a22986188ebced46cea8d48135&units=metric";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {
                        try {
                            //weather
                            JSONObject coord = response.getJSONObject("coord");
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject position0 = weatherArray.getJSONObject(0);
                            String description = position0.getString("description");
                            String iconName = position0.getString("icon");
                            //main
                            JSONObject mainObject = response.getJSONObject("main");
                            double current = mainObject.getDouble("temp");
                            double min = mainObject.getDouble("temp_min");
                            double max = mainObject.getDouble("temp_max");
                            int humidity = mainObject.getInt("humidity");

                            int vis = response.getInt("visibility");
                            String name = response.getString("name");



                                String pathname = getFilesDir() + "/" + iconName + ".png";
                                File file = new File(pathname);
                                if(file.exists()){
                                    image = BitmapFactory.decodeFile(pathname);
                                }
                                else {
                                    String ImageURL = "https://openweathermap.org/img/w/" + iconName + ".png";
                                    ImageRequest imgReq = new ImageRequest(ImageURL, new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap bitmap) {
                                            try {
                                                image = bitmap;
                                                image.compress(Bitmap.CompressFormat.PNG, 100, MainActivity.this.openFileOutput(iconName + ".png", Activity.MODE_PRIVATE));
                                                MainActivity.this.openFileOutput(iconName+".png", Activity.MODE_PRIVATE);
                                                binding.icon.setImageBitmap(image);
                                            } catch (IOException e) {
                                                e.printStackTrace();

                                            }

                                        }
                                    }, 1024, 1024, ImageView.ScaleType.CENTER, null, (error) -> {});
                                    queue.add(imgReq);
                                }
                            runOnUiThread(() -> {
                                binding.temp.setText("The current temperature is: " + current);
                                binding.temp.setVisibility(View.VISIBLE);

                                binding.min.setText("The min temperature is: " + min);
                                binding.min.setVisibility(View.VISIBLE);

                                binding.max.setText("The max temperature is: " + max);
                                binding.max.setVisibility(View.VISIBLE);

                                binding.humidity.setText("The humidity is: " + humidity);
                                binding.humidity.setVisibility(View.VISIBLE);

                                binding.desc.setText("Description: " + description);
                                binding.desc.setVisibility(View.VISIBLE);

                                binding.icon.setImageBitmap(image);
                                binding.icon.setVisibility(View.VISIBLE);

                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    (error) -> {
                    });
            queue.add(request);

        });



    }
}