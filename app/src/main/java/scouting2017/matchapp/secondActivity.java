package scouting2017.matchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static scouting2017.matchapp.FirstActivity.myAppVariables;


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

    public void cubesPlacedSwitch(View view) {
        myAppVariables.numberCubesSwitchPlacedAuto++;
        TextView numberOfCubesSwitch = (TextView) findViewById(R.id.numberOfCubesSwitchTeleop);
        numberOfCubesSwitch.setText(Integer.toString(myAppVariables.numberCubesSwitchPlacedAuto));
        GameEvent autoAllianceSwitch = new GameEvent();
        autoAllianceSwitch.eventType = "autoAllianceSwitch";
        autoAllianceSwitch.eventValue = "1";
        autoAllianceSwitch.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(autoAllianceSwitch);
    }

    public void cubesPlacedScale(View view) {
        myAppVariables.numberCubesScale++;
        TextView numberOfCubesScale = (TextView) findViewById(R.id.numberOfCubesScaleTeleop);
        numberOfCubesScale.setText(Integer.toString(myAppVariables.numberCubesScale));
        GameEvent autoScale = new GameEvent();
        autoScale.eventType = "autoScale";
        autoScale.eventValue = "1";
        autoScale.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(autoScale);
    }

//    public void highGoalAuto(View view) {
//        TextView numberOfHighGoalsText = (TextView) findViewById(view.getId());
//        TextView numberOfHighGoalsAuto = (TextView) findViewById(R.id.numberOfHighGoalsAuto);
//        Integer highGoalAutoButtonValue = 0;
//        if (!numberOfHighGoalsText.getText().toString().equalsIgnoreCase("X")) {
//            highGoalAutoButtonValue = Integer.parseInt(numberOfHighGoalsText.getText().toString());
//        }
//        myAppVariables.numberHighGoalsAuto += highGoalAutoButtonValue;
//        numberOfHighGoalsAuto.setText(Integer.toString(myAppVariables.numberHighGoalsAuto));
//        //GameEvent highGoalAuto = new GameEvent();
//        //highGoalAuto.eventType = "highGoalAuto";
//        //highGoalAuto.eventValue = highGoalAutoButtonValue.toString();
//        //highGoalAuto.eventTime = System.currentTimeMillis();
//        //myAppVariables.eventList.add(highGoalAuto);
//    }

//    public void hopperDumpedAuto(View view) {
//        if (myAppVariables.numberHoppersDumpedAuto < 5) {
//            myAppVariables.numberHoppersDumpedAuto++;
//        } else {
//            return;
//        }
//        TextView numberOfHoppersDumpedText = (TextView) findViewById(R.id.numberOfCubesDropped);
//        numberOfHoppersDumpedText.setText(Integer.toString(myAppVariables.numberHoppersDumpedAuto));
//        GameEvent hopperDumpedAuto = new GameEvent();
//        hopperDumpedAuto.eventType = "hopperDumpedAuto";
//        hopperDumpedAuto.eventValue = "1";
//        hopperDumpedAuto.eventTime = System.currentTimeMillis();
//        myAppVariables.eventList.add(hopperDumpedAuto);
//    }

//    public void cubesDroppedAuto(View view) {
//        FirstActivity.myAppVariables.numberCubesDroppedAuto++;
//        TextView numberOfCubesDropped = (TextView) findViewById(R.id.numberOfCubesDroppedTeleop);
//        numberOfCubesDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesDroppedAuto));
//        GameEvent cubesDroppedAuto = new GameEvent();
//        cubesDroppedAuto.eventType = "cubesDroppedAuto";
//        cubesDroppedAuto.eventValue = "1";
//        cubesDroppedAuto.eventTime = System.currentTimeMillis();
//        FirstActivity.myAppVariables.eventList.add(cubesDroppedAuto);
//
//    }

//    public void minusHopperDumpedAuto(View view) {
//        if (myAppVariables.numberHoppersDumpedAuto > 0) {
//            myAppVariables.numberHoppersDumpedAuto--;
//        }
//        TextView numberOfHoppersDumpedText = (TextView) findViewById(R.id.numberOfCubesDropped);
//        numberOfHoppersDumpedText.setText(Integer.toString(myAppVariables.numberHoppersDumpedAuto));
//        GameEvent minusHopperDumpedAuto = new GameEvent();
//        minusHopperDumpedAuto.eventType = "hopperDumpedAuto";
//        minusHopperDumpedAuto.eventValue = "1";
//        minusHopperDumpedAuto.eventTime = System.currentTimeMillis();
//        myAppVariables.eventList.add(minusHopperDumpedAuto);
//    }

//    public void minusCubesDroppedAuto(View view) {
//        if (FirstActivity.myAppVariables.numberCubesDroppedAuto > 0) {
//            FirstActivity.myAppVariables.numberCubesDroppedAuto--;
//        }
//        TextView numberOfCubesDropped = (TextView) findViewById(R.id.numberOfCubesDroppedTeleop);
//        numberOfCubesDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesDroppedAuto));
//        GameEvent minusCubesDroppedAuto = new GameEvent();
//        minusCubesDroppedAuto.eventType = "cubesDroppedAuto";
//        minusCubesDroppedAuto.eventValue = "-1";
//        minusCubesDroppedAuto.eventTime = System.currentTimeMillis();
//        FirstActivity.myAppVariables.eventList.add(minusCubesDroppedAuto);
//    }

//    public void minusHighGoalAuto(View view) {
//        if (myAppVariables.numberHighGoalsAuto > 0) {
//            myAppVariables.numberHighGoalsAuto--;
//        }
//        TextView numberOfHighGoalsText = (TextView) findViewById(R.id.numberOfHighGoalsAuto);
//        numberOfHighGoalsText.setText(Integer.toString(myAppVariables.numberHighGoalsAuto));
//        //GameEvent minusHighGoalAuto = new GameEvent();
//        //minusHighGoalAuto.eventType = "highGoalAuto";
//        //minusHighGoalAuto.eventValue = "1";
//       // minusHighGoalAuto.eventTime = System.currentTimeMillis();
//        //myAppVariables.eventList.add(minusHighGoalAuto);
//    }

    public void minusCubeScaleAuto(View view) {
        if (myAppVariables.numberCubesScale > 0) {
            myAppVariables.numberCubesScale--;
        }
        TextView numberOfCubesScaleTeleop = (TextView) findViewById(R.id.numberOfCubesScaleTeleop);
        numberOfCubesScaleTeleop.setText(Integer.toString(myAppVariables.numberCubesScale));
        GameEvent minusCubesScaleAuto = new GameEvent();
        minusCubesScaleAuto.eventType = "cubeScalePlacedAuto";
        minusCubesScaleAuto.eventValue = "1";
        minusCubesScaleAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(minusCubesScaleAuto);
    }

    public void minusCubeSwitchAuto(View view) {
        if (myAppVariables.numberCubesSwitchPlacedAuto > 0) {
            myAppVariables.numberCubesSwitchPlacedAuto--;
        }
        TextView numberOfCubesSwitchTeleop = (TextView) findViewById(R.id.numberOfCubesSwitchTeleop);
        numberOfCubesSwitchTeleop.setText(Integer.toString(myAppVariables.numberCubesSwitchPlacedAuto));
        GameEvent minusDroppedCubeSwitchAuto = new GameEvent();
        minusDroppedCubeSwitchAuto.eventType = "droppedCubeSwitchAuto";
        minusDroppedCubeSwitchAuto.eventValue = "1";
        minusDroppedCubeSwitchAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(minusDroppedCubeSwitchAuto);
    }

    public void crossBaseline(View view) {
        if (myAppVariables.crossBaselineAuto < 1) {
            myAppVariables.crossBaselineAuto++;
        } else {
            return;
        }
        TextView crossBaselineText = (TextView) findViewById(R.id.crossBaselineText);
        crossBaselineText.setText("✓");
        GameEvent crossBaseline = new GameEvent();
        crossBaseline.eventType = "crossBaseline";
        crossBaseline.eventValue = "1";
        crossBaseline.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(crossBaseline);
    }

    public void foul(View view) {
        if (myAppVariables.foulAuto < 1) {
            myAppVariables.foulAuto++;
        } else {
            return;
        }

        TextView foulText = (TextView) findViewById(R.id.foulText);
        foulText.setText("✓");
        GameEvent foulAuto = new GameEvent();
        foulAuto.eventType = "foulAuto";
        foulAuto.eventValue = "1";
        foulAuto.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(foulAuto);

    }


}
