package com.celalkorucu.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView textView ;
    TextView textView2 ;

    int skor ;
    int zaman ;


ImageView picture1 ;
ImageView picture2 ;
ImageView picture3 ;
    ImageView picture4 ;
    ImageView picture5 ;
    ImageView picture6 ;

    Runnable runnable ;
    Handler handler = new Handler() ;


ImageView [] imageArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialize

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        picture1 = findViewById(R.id.imageView10);
        picture2 = findViewById(R.id.imageView14);
        picture3 = findViewById(R.id.imageView12);
        picture4 = findViewById(R.id.imageView13);
        picture5 = findViewById(R.id.imageView15);
        picture6 = findViewById(R.id.imageView16);


        imageArray = new ImageView[] {picture1,picture2,picture3,picture4,picture5,picture6};

        hideImages();


        skor = 0;

        new CountDownTimer(10000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                textView.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }


                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }
        }.start();

    }

    public void increaseScore (View view) {

        skor++;
        //score = score + 1;

        textView2.setText("Score: " + skor);


    }

    public void hideImages() {

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(6);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable , 500);
            }
        };


        handler.post(runnable);


    }
}