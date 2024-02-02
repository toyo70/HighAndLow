package com.example.highandlow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    ImageView ivYourCard;
    ImageView ivNextCard;
    Button btHigh;
    Button btLow;
    Button btNext;
    TextView winorlose;

    final int YOURCARD_RESOURCE[] ={
            R.drawable.c01,R.drawable.c02,R.drawable.c03,R.drawable.c04,
            R.drawable.c05,R.drawable.c06,R.drawable.c07,R.drawable.c08,
            R.drawable.c09,R.drawable.c10,R.drawable.c11,R.drawable.c12,
            R.drawable.c13
    };
    final int NEXTCARD_RESOURCE[] ={
            R.drawable.c01,R.drawable.c02,R.drawable.c03,R.drawable.c04,
            R.drawable.c05,R.drawable.c06,R.drawable.c07,R.drawable.c08,
            R.drawable.c09,R.drawable.c10, R.drawable.d11,R.drawable.c12,
            R.drawable.c13
    };

    int yourCard=0;
    int nextCard=0;
    enum selectBt{HIGH,LOW,NEXT};
    int win=0;
    int lose=0;
    boolean wingame =true;
    boolean losegame =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        ivYourCard = findViewById(R.id.ivYourCard);
        ivNextCard = findViewById(R.id.ivNextCard);
        btHigh = findViewById(R.id.btHigh);
        btLow = findViewById(R.id.btLow);
        btNext = findViewById(R.id.btNext);
        winorlose = findViewById(R.id.winorlose);


        nextGame();

        btHigh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                judge(selectBt.HIGH);
            }
        });
        btLow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                judge(selectBt.LOW);
            }
        });
        btNext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(wingame==false || losegame==false){
                    win=lose=0;
                    wingame=losegame=true;
                    btNext.setText("NEXT");
                    btHigh.setVisibility(View.VISIBLE);
                    btLow.setVisibility(View.VISIBLE);
                    winorlose.setText("Win:0 Lose:0");
                    winorlose.setVisibility(View.INVISIBLE);
                }
                nextGame();
            }
        });


    }
    private void nextGame(){
        Random rand =new Random();

        if(win>=5 && losegame){
            tvResult.setText("あなたの勝ち");
            wingame = false;
            btNext.setText("ReStart");
            btHigh.setVisibility(View.INVISIBLE);
            btLow.setVisibility(View.INVISIBLE);
        }else if(lose>=5 && wingame) {
            tvResult.setText("あなたの負け");
            losegame =false;
            btNext.setText("ReStart");
            btHigh.setVisibility(View.INVISIBLE);
            btLow.setVisibility(View.INVISIBLE);
        }else{
            yourCard = rand.nextInt(YOURCARD_RESOURCE.length);
            nextCard = rand.nextInt(NEXTCARD_RESOURCE.length);
            ivYourCard.setImageResource(YOURCARD_RESOURCE[yourCard]);
            ivNextCard.setImageResource(R.drawable.z01);

            tvResult.setText("");
            btHigh.setEnabled(true);
            btLow.setEnabled(true);
            btNext.setEnabled(false);

        }
    }

    private void judge(selectBt select){
        ivNextCard.setImageResource(NEXTCARD_RESOURCE[nextCard]);

        btHigh.setEnabled(false);
        btLow.setEnabled(false);
        btNext.setEnabled(true);
        winorlose.setVisibility(View.VISIBLE);
        if(yourCard < nextCard && select == selectBt.HIGH){
            tvResult.setText("正解");

            win++;
        }else if(yourCard > nextCard && select == selectBt.LOW){
            tvResult.setText("正解");
            win++;
        }else if(yourCard == nextCard){
            tvResult.setText("ドロー");
        }else{
            tvResult.setText("はずれ");
            lose++;
        }
        winorlose.setText("Win:"+win+" Lose:"+lose+"");
    }

}