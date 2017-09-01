package com.roshan.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    int turn = 0;
    TextView whichPlayer;
    String player1;
    String player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        whichPlayer = (TextView) findViewById(R.id.whichPlayer);
        turn++;
    }

    public void game(View view){
        String chosen = (String) view.getTag();

        //get chosen (i.e paper rock or scissors
        if(turn==1){
            player1 = chosen;
            turn++;
            whichPlayer.setText("Player 2");
        }else if(turn==2){

            player2 = chosen;

            int winNumber = checkWinner();
            String winner;

            //check winner
            if(winNumber==1){
                winner = "player1";
            }else if(winNumber==2){
                winner = "player2";
            }else{
                winner = "draw";
            }

            //======== redirect to other actity ======
            Intent resultIntent = new Intent(this,ResultActivity.class);

            //send through bundle
            Bundle results = new Bundle();
            results.putString("winner",winner);
            results.putString("player1",player1);
            results.putString("player2",player2);

            //reset for next use
            turn = 0;
            player1 = null;
            player2 = null;

            //pass bundle to activity
            resultIntent.putExtras(results);
            startActivity(resultIntent);


        }
    }


    public int checkWinner(){
        if((player1.equals("paper") && player2.equals("rock")) ||
                (player1.equals("scissor") && player2.equals("paper")) ||
                (player1.equals("rock") && player2.equals("scissor"))){
            //if player 1 wins
            return 1;

        }else if((player2.equals("paper") && player1.equals("rock")) ||
                (player2.equals("scissor") && player1.equals("paper")) ||
                (player2.equals("rock") && player1.equals("scissor"))){
            //if player 2 wins
            return 2;
        }else {
            //if draw
            return 0;
        }
    }
}
