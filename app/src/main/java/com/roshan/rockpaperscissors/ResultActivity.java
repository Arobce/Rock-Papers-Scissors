package com.roshan.rockpaperscissors;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    ImageView player1Img;
    ImageView player2Img;
    TextView resulttxt;
    String player1choice;
    String player2choice;
    ImageView victoryMsg;
    RelativeLayout gameLayout;
    TextView playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //get values from game activity
        Bundle result = getIntent().getExtras();
        String winner = result.getString("winner");
        Toast.makeText(this, "Winner is: "+winner, Toast.LENGTH_SHORT).show();
        player1choice = result.getString("player1");
        player2choice = result.getString("player2");

        player1Img = (ImageView) findViewById(R.id.player1image);
        player2Img = (ImageView) findViewById(R.id.player2image);

        //set text views
        resulttxt = (TextView) findViewById(R.id.resultText);

        //announce winner
        if(winner.equals("player1")){
            resulttxt.setText("The winner is Player 1");
        }else if(winner.equals("player2")){
            resulttxt.setText("The winner is Player 2");
        }else{
            resulttxt.setText("Draw");
        }

        //set relevant image
        if(player1choice.equals("paper")){
            player1Img.setImageResource(R.drawable.handpaper);
        }else if(player1choice.equals("rock")){
            player1Img.setImageResource(R.drawable.handrock);
        }else if(player1choice.equals("scissor")){
            player1Img.setImageResource(R.drawable.handscissors);
        }
        if(player2choice.equals("paper")){
            player2Img.setImageResource(R.drawable.handpaper);
        }else if(player2choice.equals("rock")){
            player2Img.setImageResource(R.drawable.handrock);
        }else if(player2choice.equals("scissor")){
            player2Img.setImageResource(R.drawable.handscissors);
        }

        winningAnimation();



    }

    public void winningAnimation(){
        new CountDownTimer(2000,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                victoryMsg = (ImageView) findViewById(R.id.coolText);

                if((player1choice.equals("rock")&&player2choice.equals("paper"))||
                        (player2choice.equals("rock")&&player1choice.equals("paper"))){

                    //if rock beats paper

                    victoryMsg.setImageResource(R.drawable.paper);
                    victoryMsg.animate().scaleX(1f).scaleY(1f);

                }else if((player1choice.equals("scissor")&& player2choice.equals("rock")) ||
                        (player2choice.equals("scissor")&& player1choice.equals("rock"))){

                    //if rock beats scissors

                    victoryMsg.setImageResource(R.drawable.rock);
                    victoryMsg.animate().scaleX(1f).scaleY(1f);

                }else if((player1choice.equals("paper")&&player2choice.equals("scissor"))||
                (player2choice.equals("paper") && player1choice.equals("scissor"))){

                    //if scissors beats paper

                    victoryMsg.setImageResource(R.drawable.scissors);
                    victoryMsg.animate().scaleX(1f).scaleY(1f);
                }

                //make layout transparent
                gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
                gameLayout.setAlpha(0.25f);

                //show play again button
                playAgainButton = (Button) findViewById(R.id.playAgain);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void playAgain(View view){

        //redirect to game activity

        Intent playAgain= new Intent(this,GameActivity.class);
        startActivity(playAgain);
    }


}
