package com.reber.basicstopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button, button2,button3;
    TextView textView;
    int number,min,hour;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        number = -1;
        min = 0;
        hour = 0;
        button2.setEnabled(false);
    }

    public void start(View view) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Time: " + number);
                number++;
                if (number==60){
                    min++;
                    number = number - 60;
                }
                if (min==60){
                    hour++;
                    min = min - 60;
                }
                textView.setText("Time: " + hour + " h "+ min + " m " + number+" s");
                handler.postDelayed(runnable, 1000);
            }
        };
        Toast.makeText(this, "Stopwatch has started to count.", Toast.LENGTH_SHORT).show();
        handler.post(runnable);
        button.setEnabled(false);
        button3.setEnabled(false);
        button2.setEnabled(true);

    }

    public void stop(View view) {
        button2.setEnabled(false);
        handler.removeCallbacks(runnable);
        Toast.makeText(this, "Stopwatch has stopped.", Toast.LENGTH_SHORT).show();
        button3.setEnabled(true);

    }
    public void reset(View view){
        number = -1;
        min = 0;
        button.setEnabled(true);
        button2.setEnabled(false);
        button3.setEnabled(false);
        textView.setText("Time: 0");
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
    }

}