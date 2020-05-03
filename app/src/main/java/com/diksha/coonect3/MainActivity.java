package com.diksha.coonect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    //2 means unplayed
    //0 means circle 1 means cross
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
public void dropin(View view)
{
    ImageView counter=(ImageView)view;
    System.out.println(counter.getTag().toString());
    int tappedcounter=Integer.parseInt(counter.getTag().toString());
    if(gamestate[tappedcounter]==2)
    {
        gamestate[tappedcounter]=activeplayer;
        counter.setTranslationY(-1000f);
        if (activeplayer == 0) {
            counter.setImageResource(R.drawable.circle);
            activeplayer = 1;
        } else {
            counter.setImageResource(R.drawable.cross);
            activeplayer = 0;
        }
        counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
        for(int[] winningPositions : winningPositions)
        {
            if(gamestate[winningPositions[0]]==gamestate[winningPositions[1]]
                    && gamestate[winningPositions[1]]==gamestate[winningPositions[2]]
                    && gamestate[winningPositions[0]]!=2)
            {
                String winner="CROSS";
                if(gamestate[winningPositions[0]]==0)
                {
                    winner="CIRCLE";
                }
                TextView winnermessage=(TextView)findViewById(R.id.winnerMessage);
                winnermessage.setText(winner +" has won!!!!");
                LinearLayout Layout=(LinearLayout)findViewById(R.id.playagainLayout);
                Layout.setVisibility(View.VISIBLE);
            }
        }
    }
}
public void playAgain(View view)
{
    LinearLayout Layout=(LinearLayout)findViewById(R.id.playagainLayout);
    Layout.setVisibility(View.INVISIBLE);
    activeplayer=0;
for(int i=0;i<9;i++)
{
    gamestate[i]=2;
}
    GridLayout grid=(GridLayout)findViewById(R.id.gone);
for(int i=0;i<grid.getChildCount();i++)
{
    ((ImageView)grid.getChildAt(i)).setImageResource(0);
}
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
