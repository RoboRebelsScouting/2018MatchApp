package scouting2017.matchapp;
//This class has the necessary methods for the teleop page
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static scouting2017.matchapp.FirstActivity.myAppVariables;

public class thirdActivity extends AppCompatActivity {
    public Handler teleopTimer = new Handler();

    @Override
    public void onBackPressed() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().setTitle(Integer.toString(FirstActivity.myAppVariables.robotNumber));
        if (FirstActivity.myAppVariables.allianceColor == true) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        } else {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        }
        FirstActivity.myAppVariables.teleopTime = 135000;
        TextView teleopTimerText = (TextView) findViewById(R.id.teleopTimerText);
        Long timeRemaining = FirstActivity.myAppVariables.teleopTime / 1000;
        Long remainingMinutes = timeRemaining / 60;
        Long remainingSeconds = timeRemaining - (remainingMinutes * 60);
        if (remainingSeconds < 10) {
            teleopTimerText.setText(remainingMinutes + ":0" + remainingSeconds);
        } else {
            teleopTimerText.setText(remainingMinutes + ":" + remainingSeconds);
        }
        teleopTimer.postDelayed(updateTimer, 1000);
        //toggleGoal();
        //ImageButton hopperDumpedTeleop = (ImageButton)findViewById(R.id.hopperDumpedTeleop);
        //TextView numberOfHighGoalsTeleop = (TextView) findViewById(R.id.numberOfHighGoalsTeleop);
        FirstActivity.myAppVariables.numberHighGoalsTeleop = FirstActivity.myAppVariables.numberHighGoalsAuto;
        //numberOfHighGoalsTeleop.setText (String.valueOf(FirstActivity.myAppVariables.numberHighGoalsTeleop));

    }

    public void toEndOfGame(View view) {
        if (FirstActivity.myAppVariables.teleopTime < 120000) {
            Intent intent = new Intent(this, activity_fourth.class);
            startActivity(intent);
            teleopTimer.removeCallbacks(updateTimer);
        }
    }

    private final Runnable updateTimer = new Runnable() {
        public void run() {
            try {
                FirstActivity.myAppVariables.teleopTime -= 1000;
                TextView teleopTimerText = (TextView) findViewById(R.id.teleopTimerText);
                Long timeRemaining = FirstActivity.myAppVariables.teleopTime / 1000;
                Long remainingMinutes = timeRemaining / 60;
                Long remainingSeconds = timeRemaining - (remainingMinutes * 60);
                if (remainingSeconds < 10) {
                    teleopTimerText.setText(remainingMinutes + ":0" + remainingSeconds);
                } else {
                    teleopTimerText.setText(remainingMinutes + ":" + remainingSeconds);
                }
                if (FirstActivity.myAppVariables.teleopTime > 0) {
                    teleopTimer.postDelayed(this, 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void cubesPlacedSwitchTeleop(View view) {
        myAppVariables.numberCubesSwitchPlacedTeleop++;
        TextView numberOfCubesSwitch = (TextView) findViewById(R.id.numberOfCubesSwitchTeleop);
        numberOfCubesSwitch.setText(Integer.toString(myAppVariables.numberCubesSwitchPlacedTeleop));
        GameEvent teleopAllianceSwitch = new GameEvent();
        teleopAllianceSwitch.eventType = "teleopAllianceSwitch";
        teleopAllianceSwitch.eventValue = "1";
        teleopAllianceSwitch.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(teleopAllianceSwitch);
    }

    public void cubesPlacedOpponentSwitchTeleop(View view) {
        myAppVariables.numberCubesOpponentSwitchPlacedTeleop++;
        TextView numberOfCubesOpponentSwitch = (TextView) findViewById(R.id.numberOfCubesOpponentSwitchTeleop);
        numberOfCubesOpponentSwitch.setText(Integer.toString(myAppVariables.numberCubesOpponentSwitchPlacedTeleop));
        GameEvent opponentSwitch = new GameEvent();
        opponentSwitch.eventType = "opponentSwitch";
        opponentSwitch.eventValue = "1";
        opponentSwitch.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(opponentSwitch);

    }

    public void cubesPlacedScaleTeleop(View view) {
        myAppVariables.numberCubesScaleTeleop++;
        TextView numberOfCubesScale = (TextView) findViewById(R.id.numberOfCubesScaleTeleop);
        numberOfCubesScale.setText(Integer.toString(myAppVariables.numberCubesScaleTeleop));
        GameEvent teleopScale = new GameEvent();
        teleopScale.eventType = "teleopScale";
        teleopScale.eventValue = "1";
        teleopScale.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(teleopScale);

    }

    public void cubesPlacedVault(View view) {
        myAppVariables.numberCubesVault++;
        TextView numberOfCubesVault = (TextView) findViewById(R.id.numberOfCubesVault);
        numberOfCubesVault.setText(Integer.toString(myAppVariables.numberCubesVault));
        GameEvent vault = new GameEvent();
        vault.eventType = "vault";
        vault.eventValue = "1";
        vault.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(vault);
    }

    public void cubesFromHuman(View view) {
        myAppVariables.numberCubesHuman++;
        TextView numberOfCubesHuman = (TextView) findViewById(R.id.numberOfCubesHuman);
        numberOfCubesHuman.setText(Integer.toString(myAppVariables.numberCubesHuman));
        GameEvent humanCube = new GameEvent();
        humanCube.eventType = "humanCube";
        humanCube.eventValue = "1";
        humanCube.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(humanCube);
    }

    public void cubesDroppedTeleop(View view) {
        myAppVariables.numberCubesDroppedTeleop++;
        TextView numberOfCubesDroppedTeleop = (TextView) findViewById(R.id.numberOfCubesDroppedTeleop);
        numberOfCubesDroppedTeleop.setText(Integer.toString(myAppVariables.numberCubesDroppedTeleop));
        GameEvent droppedCube = new GameEvent();
        droppedCube.eventType = "droppedCube";
        droppedCube.eventValue = "1";
        droppedCube.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(droppedCube);
    }

    public void cubesPickedupGround(View view) {
        myAppVariables.numberCubesFromGround++;
        TextView numberOfCubesGround = (TextView) findViewById(R.id.numberOfCubesGround);
        numberOfCubesGround.setText(Integer.toString(myAppVariables.numberCubesFromGround));
        GameEvent groundCube = new GameEvent();
        groundCube.eventType = "groundCube";
        groundCube.eventValue = "1";
        groundCube.eventTime = System.currentTimeMillis();
        myAppVariables.eventList.add(groundCube);
    }



    public void minusCubesSwitchTeleop(View view) {
        if (FirstActivity.myAppVariables.numberCubesSwitchPlacedTeleop > 0) {
            FirstActivity.myAppVariables.numberCubesSwitchPlacedTeleop--;
        }
        TextView numberOfCubesSwitchDropped = (TextView) findViewById(R.id.numberOfCubesSwitchTeleop);
        numberOfCubesSwitchDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesSwitchPlacedTeleop));
        GameEvent minusCubesSwitchDropped = new GameEvent();
        minusCubesSwitchDropped.eventType = "cubesDroppedSwitchTeleop";
        minusCubesSwitchDropped.eventValue = "-1";
        minusCubesSwitchDropped.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusCubesSwitchDropped);
    }

    public void minusCubesOpponentSwitchTeleop(View view) {
        if(FirstActivity.myAppVariables.numberCubesOpponentSwitchPlacedTeleop > 0) {
            FirstActivity.myAppVariables.numberCubesOpponentSwitchPlacedTeleop--;
        }
        TextView numberOfCubesOpponentSwitchDropped = (TextView) findViewById(R.id.numberOfCubesOpponentSwitchTeleop);
        numberOfCubesOpponentSwitchDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesOpponentSwitchPlacedTeleop));
        GameEvent minusCubesOpponentSwitchDropped = new GameEvent();
        minusCubesOpponentSwitchDropped.eventType = "cubesDroppedOpponentSwitchTeleop";
        minusCubesOpponentSwitchDropped.eventValue = "-1";
        minusCubesOpponentSwitchDropped.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusCubesOpponentSwitchDropped);

    }

    public void minusCubesScaleTeleop(View view) {
        if(FirstActivity.myAppVariables.numberCubesScaleTeleop > 0) {
            FirstActivity.myAppVariables.numberCubesScaleTeleop--;
        }
        TextView numberOfCubesScaleDropped = (TextView) findViewById(R.id.numberOfCubesScaleTeleop);
        numberOfCubesScaleDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesScaleTeleop));
        GameEvent minusCubesScaleDropped = new GameEvent();
        minusCubesScaleDropped.eventType = "cubesDroppedScaleTeleop";
        minusCubesScaleDropped.eventValue = "-1";
        minusCubesScaleDropped.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusCubesScaleDropped);

    }

    public void minusCubesVault(View view) {
        if(FirstActivity.myAppVariables.numberCubesVault > 0) {
            FirstActivity.myAppVariables.numberCubesVault--;
        }
        TextView numberOfCubesVaultDropped = (TextView) findViewById(R.id.numberOfCubesVault);
        numberOfCubesVaultDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesVault));
        GameEvent minusCubesVault = new GameEvent();
        minusCubesVault.eventType = "cubesDroppedVault";
        minusCubesVault.eventValue = "-1";
        minusCubesVault.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusCubesVault);

        }

    public void minusCubesHuman(View view) {
        if(FirstActivity.myAppVariables.numberCubesHuman > 0) {
            FirstActivity.myAppVariables.numberCubesHuman--;
        }
        TextView numberOfCubesHumanDropped = (TextView) findViewById(R.id.numberOfCubesHuman);
        numberOfCubesHumanDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesHuman));
        GameEvent minusCubesHuman = new GameEvent();
        minusCubesHuman.eventType = "cubesDroppedHuman";
        minusCubesHuman.eventValue = "-1";
        minusCubesHuman.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusCubesHuman);
    }

    public void minusCubesDroppedTeleop(View view) {
        if(FirstActivity.myAppVariables.numberCubesDroppedTeleop > 0) {
            FirstActivity.myAppVariables.numberCubesDroppedTeleop--;
        }
        TextView numberOfCubesDroppedTeleop = (TextView) findViewById(R.id.numberOfCubesDroppedTeleop);
        numberOfCubesDroppedTeleop.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesDroppedTeleop));
        GameEvent minusCubesDroppedTeleop = new GameEvent();
        minusCubesDroppedTeleop.eventType = "cubesDroppedTeleop";
        minusCubesDroppedTeleop.eventValue = "-1";
        minusCubesDroppedTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusCubesDroppedTeleop);
    }

    public void minusCubesGround(View view) {
        if(FirstActivity.myAppVariables.numberCubesFromGround > 0) {
            FirstActivity.myAppVariables.numberCubesFromGround--;
        }
        TextView numberOfCubesFromGround = (TextView) findViewById(R.id.numberOfCubesGround);
        numberOfCubesFromGround.setText(Integer.toString(FirstActivity.myAppVariables.numberCubesFromGround));
        GameEvent minusCubesFromGround = new GameEvent();
        minusCubesFromGround.eventType = "cubesDroppedGround";
        minusCubesFromGround.eventValue = "-1";
        minusCubesFromGround.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusCubesFromGround);
    }
    }
