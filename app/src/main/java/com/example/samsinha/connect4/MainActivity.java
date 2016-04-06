package com.example.samsinha.connect4;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    // francis = 0, deadpool = 1
    int activePlayer = 0;

    int gameon = 1;
    int playcount = 0;
    int draw = 1;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameon == 1) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                playcount += 1;
                counter.setImageResource(R.drawable.francis);
                activePlayer = 1;
            } else {
                playcount += 1;
                counter.setImageResource(R.drawable.dp);
                activePlayer = 0;

            }


            counter.animate().translationYBy(1000f).setDuration(400);
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                        draw = 0;
                        gameon = 0;

                        String winner = "Deadpool";
                        if(gameState[winningPosition[0]] == 0) {

                            winner = "Francis";

                        }

                        TextView winnerMsg = (TextView) findViewById(R.id.winnerMsg);
                        winnerMsg.setText( winner + " wins!");

                        LinearLayout layout =  (LinearLayout)findViewById(R.id.pagainLayout);
                        layout.setTranslationY(-1000f);

                        layout.setVisibility(View.VISIBLE);
                        layout.animate().translationYBy(1000f).setDuration(1000);
                }

            }
            if (draw == 1 && playcount == 9){
                gameon = 0;
                TextView winnerMsg = (TextView) findViewById(R.id.winnerMsg);
                winnerMsg.setText( " Drawn!");

                LinearLayout layout =  (LinearLayout)findViewById(R.id.pagainLayout);
                layout.setTranslationY(-1000f);

                layout.setVisibility(View.VISIBLE);
                layout.animate().translationYBy(1000f).setDuration(1000);
            }
        }
    }

    public void playAgain(View view) {
        draw = 1;
        playcount = 0;
        gameon = 1;
        LinearLayout layout =  (LinearLayout)findViewById(R.id.pagainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i< gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
