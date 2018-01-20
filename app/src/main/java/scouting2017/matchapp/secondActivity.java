package scouting2017.matchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.TextView;

import static scouting2017.matchapp.FirstActivity.myAppVariables;
import static scouting2017.matchapp.R.id.crossBaselineAuto;


public class secondActivity extends AppCompatActivity {
    public boolean crossedBaseline = false;
    public Handler autoTimer = new Handler();

    @Override
    public void onBackPressed() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // only allow one timer to be created, if onCreate launched a 2nd time
        // don't create new timer
        if (myAppVariables.timerStarted == false) {
            myAppVariables.timerStarted = true;
            myAppVariables.autoTime = 20000;
            TextView autoTimerText = (TextView) findViewById(R.id.autoTimerText);
            autoTimerText.setText(String.valueOf(myAppVariables.autoTime / 1000));
            autoTimer.postDelayed(updateTimer, 1000);
            getSupportActionBar().setTitle(Integer.toString(myAppVariables.robotNumber));
            if (myAppVariables.allianceColor == true) {
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
            } else {
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
            }
        }
    }

    private final Runnable updateTimer = new Runnable() {
        public void run() {
            try {
                myAppVariables.autoTime -= 1000;
                TextView autoTimerText = (TextView) findViewById(R.id.autoTimerText);
                autoTimerText.setText(String.valueOf(myAppVariables.autoTime / 1000));
                if (myAppVariables.autoTime <= 0) {
                    toTeleop((View) findViewById(R.id.activity_second));
                } else {
                    autoTimer.postDelayed(this, 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void toTeleop(View view) {
        Intent intent = new Intent(this, thirdActivity.class);
        autoTimer.removeCallbacks(updateTimer);
        startActivity(intent);
    }

    public void gearPlaced(View view) {
        myAppVariables.numberGearsPlacedAuto++;
        TextView numberOfGearsPlacedText = (TextView) findViewById(R.id.numberOfGearsPlacedText);
        numberOfGearsPlacedText.setText(Integer.toString(myAppVariables.numberGearsPlacedAuto));
        GameEvent gearPlacedAuto = new GameEvent();
        gearPlacedAuto.eventType = "gearPlacedAuto";
        gearPlacedAuto.eventValue = "1";
        gearPlacedAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(gearPlacedAuto);
    }

    public void droppedGear(View view) {
        myAppVariables.numberDroppedGearsAuto++;
        TextView numberOfDroppedGearsText = (TextView) findViewById(R.id.numberOfDroppedGearsText);
        numberOfDroppedGearsText.setText(Integer.toString(myAppVariables.numberDroppedGearsAuto));
        GameEvent droppedGearAuto = new GameEvent();
        droppedGearAuto.eventType = "droppedGearAuto";
        droppedGearAuto.eventValue = "1";
        droppedGearAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(droppedGearAuto);
    }

    public void highGoalAuto(View view) {
        TextView numberOfHighGoalsText = (TextView) findViewById(view.getId());
        TextView numberOfHighGoalsAuto = (TextView) findViewById(R.id.numberOfHighGoalsAuto);
        Integer highGoalAutoButtonValue = 0;
        if (!numberOfHighGoalsText.getText().toString().equalsIgnoreCase("X")) {
            highGoalAutoButtonValue = Integer.parseInt(numberOfHighGoalsText.getText().toString());
        }
        myAppVariables.numberHighGoalsAuto += highGoalAutoButtonValue;
        numberOfHighGoalsAuto.setText(Integer.toString(myAppVariables.numberHighGoalsAuto));
        //GameEvent highGoalAuto = new GameEvent();
        //highGoalAuto.eventType = "highGoalAuto";
        //highGoalAuto.eventValue = highGoalAutoButtonValue.toString();
        //highGoalAuto.eventTime = System.currentTimeMillis();
        //myAppVariables.eventList.add(highGoalAuto);
    }

    public void hopperDumpedAuto(View view) {
        if (myAppVariables.numberHoppersDumpedAuto < 5) {
            myAppVariables.numberHoppersDumpedAuto++;
        } else {
            return;
        }
        TextView numberOfHoppersDumpedText = (TextView) findViewById(R.id.numberOfHoppersDumpedText);
        numberOfHoppersDumpedText.setText(Integer.toString(myAppVariables.numberHoppersDumpedAuto));
        GameEvent hopperDumpedAuto = new GameEvent();
        hopperDumpedAuto.eventType = "hopperDumpedAuto";
        hopperDumpedAuto.eventValue = "1";
        hopperDumpedAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(hopperDumpedAuto);
    }

    public void minusHopperDumpedAuto(View view) {
        if (myAppVariables.numberHoppersDumpedAuto > 0) {
            myAppVariables.numberHoppersDumpedAuto--;
        }
        TextView numberOfHoppersDumpedText = (TextView) findViewById(R.id.numberOfHoppersDumpedText);
        numberOfHoppersDumpedText.setText(Integer.toString(myAppVariables.numberHoppersDumpedAuto));
        GameEvent minusHopperDumpedAuto = new GameEvent();
        minusHopperDumpedAuto.eventType = "hopperDumpedAuto";
        minusHopperDumpedAuto.eventValue = "1";
        minusHopperDumpedAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(minusHopperDumpedAuto);
    }

    public void minusHighGoalAuto(View view) {
        if (myAppVariables.numberHighGoalsAuto > 0) {
            myAppVariables.numberHighGoalsAuto--;
        }
        TextView numberOfHighGoalsText = (TextView) findViewById(R.id.numberOfHighGoalsAuto);
        numberOfHighGoalsText.setText(Integer.toString(myAppVariables.numberHighGoalsAuto));
        //GameEvent minusHighGoalAuto = new GameEvent();
        //minusHighGoalAuto.eventType = "highGoalAuto";
        //minusHighGoalAuto.eventValue = "1";
       // minusHighGoalAuto.eventTime = System.currentTimeMillis();
        //myAppVariables.eventList.add(minusHighGoalAuto);
    }

    public void minusGearPlacedAuto(View view) {
        if (myAppVariables.numberGearsPlacedAuto > 0) {
            myAppVariables.numberGearsPlacedAuto--;
        }
        TextView numberOfGearsPlacedText = (TextView) findViewById(R.id.numberOfGearsPlacedText);
        numberOfGearsPlacedText.setText(Integer.toString(myAppVariables.numberGearsPlacedAuto));
        GameEvent minusGearPlacedAuto = new GameEvent();
        minusGearPlacedAuto.eventType = "gearPlacedAuto";
        minusGearPlacedAuto.eventValue = "1";
        minusGearPlacedAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(minusGearPlacedAuto);
    }

    public void minusDroppedGearAuto(View view) {
        if (myAppVariables.numberDroppedGearsAuto > 0) {
            myAppVariables.numberDroppedGearsAuto--;
        }
        TextView numberOfDroppedGearsText = (TextView) findViewById(R.id.numberOfDroppedGearsText);
        numberOfDroppedGearsText.setText(Integer.toString(myAppVariables.numberDroppedGearsAuto));
        GameEvent minusDroppedGearAuto = new GameEvent();
        minusDroppedGearAuto.eventType = "droppedGearAuto";
        minusDroppedGearAuto.eventValue = "1";
        minusDroppedGearAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(minusDroppedGearAuto);
    }

    public void crossBaseline(View view) {
        if (myAppVariables.crossBaselineAuto < 1) {
            myAppVariables.crossBaselineAuto++;
        } else {
            return;
        }
        TextView crossBaselineText = (TextView) findViewById(R.id.crossBaselineText);
        crossBaselineText.setText("✓");
        GameEvent crossBaselineAuto = new GameEvent();
        crossBaselineAuto.eventType = "crossBaselineAuto";
        crossBaselineAuto.eventValue = "1";
        crossBaselineAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(crossBaselineAuto);
    }
}
