package net.fodev.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FairCatanDice dice = new FairCatanDice();
    boolean historyVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice.printValueOnly();
        dice.reset();
        dice.shuffle(5);
        dice.printValueOnly();
    }

    public void onRestart(View view) {
        TextView textView = findViewById(R.id.textRoll);
        TextView textHistory = findViewById(R.id.textHistory);
        dice.printValueOnly();
        dice.reset();
        dice.shuffle(5);
        dice.printValueOnly();
        textView.setText("Restarted");
        textHistory.setText("New Random pool generated.\n\n\nPress roll to start.\n");
    }

    public void onRoll(View view) {
        TextView textView = findViewById(R.id.textRoll);
        TextView textHistory = findViewById(R.id.textHistory);
        Pair<Integer, Integer> roll = dice.getNext();
        if (roll != null) {
            dice.printValueOnly();
            textView.setText(String.format("%d + %d = %d", roll.getFirst(), roll.getSecond(), roll.getFirst() + roll.getSecond()));
            textHistory.setText("Roll history:\n\n" + dice.getHistoryValueOnly());
        } else {
            textView.setText("Restart?");
            if (!textHistory.getText().subSequence(0, 3).equals("Fin")) {
                textHistory.setText("Finished random pool (36 rolls).\n" + textHistory.getText());
            }
        }
    }

    public void onInfo(View view) {
        TextView textHistory = findViewById(R.id.textHistory);
        Button hideInfoButton = findViewById(R.id.buttonInfo);
        historyVisible = !historyVisible;
        if (!historyVisible) {
            textHistory.setVisibility(View.INVISIBLE);
            hideInfoButton.setText("Show Info");
        } else {
            textHistory.setVisibility(View.VISIBLE);
            hideInfoButton.setText("Hide Info");
        }
    }
}