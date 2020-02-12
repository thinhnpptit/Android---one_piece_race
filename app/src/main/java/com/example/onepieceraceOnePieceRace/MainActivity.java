package com.example.onepieceraceOnePieceRace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3;
    SeekBar sk1, sk2, sk3;
    TextView txtScore;
    ImageButton btnPlay;
    int score =100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();
        txtScore.setText(score +"");
        sk1.setEnabled(false);
        sk2.setEnabled(false);
        sk3.setEnabled(false);

        final CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                 Check Win
                if( sk1.getProgress() >= sk1.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "One Win", Toast.LENGTH_SHORT).show();
                    btnPlay.setVisibility(View.VISIBLE);
                    if(cb1.isChecked()){
                        score += 10;
                    } else {
                        score -=10;
                    }
                    txtScore.setText(score +"");
                    setEnableCheckBox();
                }
                if( sk2.getProgress() >= sk2.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Two Win", Toast.LENGTH_SHORT).show();
                    btnPlay.setVisibility(View.VISIBLE);
                    if(cb2.isChecked()){
                        score += 10;
                    } else {
                        score -=10;
                    }
                    txtScore.setText(score +"");
                    setEnableCheckBox();
                }
                if( sk3.getProgress() >= sk3.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Three Win", Toast.LENGTH_SHORT).show();
                    btnPlay.setVisibility(View.VISIBLE);
                    if(cb3.isChecked()){
                        score += 10;
                    } else {
                        score -=10;
                    }
                    txtScore.setText(score +"");
                    setEnableCheckBox();
                }

                int number = 5;
                Random random = new Random();
                int off1 = random.nextInt(number);
                int off2 = random.nextInt(number);
                int off3 = random.nextInt(number);

                sk1.setProgress(sk1.getProgress() + off1);
                sk2.setProgress(sk2.getProgress() + off2);
                sk3.setProgress(sk3.getProgress() + off3);

            }

            @Override
            public void onFinish() {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                    sk1.setProgress(0);
                    sk2.setProgress(0);
                    sk3.setProgress(0);
                    setDisableCheckBox();

                    btnPlay.setVisibility(v.INVISIBLE);
                    countDownTimer.start();
                } else {
                    Toast.makeText(MainActivity.this, "Bạn vui lòng đặt cược!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cb2.setChecked(false);
                cb3.setChecked(false);
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cb1.setChecked(false);
                cb3.setChecked(false);
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cb1.setChecked(false);
                cb2.setChecked(false);
            }
        });

    }

    private void setEnableCheckBox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }
    private void setDisableCheckBox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }

    private void Mapping() {
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
        sk1 = findViewById(R.id.seekbar1);
        sk2 = findViewById(R.id.seekbar2);
        sk3 = findViewById(R.id.seekbar3);
        txtScore = findViewById(R.id.txtScore);
        btnPlay = findViewById(R.id.btnPlay);
    }
}
