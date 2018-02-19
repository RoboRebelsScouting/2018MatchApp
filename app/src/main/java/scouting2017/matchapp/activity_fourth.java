package scouting2017.matchapp;
//This class has the necessary methods for the end game page
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static scouting2017.matchapp.FirstActivity.myAppVariables;

public class activity_fourth extends AppCompatActivity {
public boolean robotClimbed = false;
public boolean robotParked = false;
public boolean robotBroke = false;
public boolean robotTipped = false;
    public boolean useBluetoothActivity = false;
    public boolean saveFileOnly = false;

    @Override
    public void onBackPressed() {
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        getSupportActionBar().setTitle(Integer.toString(FirstActivity.myAppVariables.robotNumber));
        if (FirstActivity.myAppVariables.allianceColor == true) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        } else {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        }
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        startFirstActivity();
    }
    public void startFirstActivity() {
        Intent intent = new Intent(this, FirstActivity.class) ;
        String competitionNameInfo = FirstActivity.myAppVariables.competitionName;
        String scouterNameInfo = FirstActivity.myAppVariables.scouterName;
        Integer matchNumberInfo = FirstActivity.myAppVariables.matchNumber;
        Boolean allianceColor = FirstActivity.myAppVariables.allianceColor;
        FirstActivity.myAppVariables.reset();
        FirstActivity.myAppVariables.competitionName = competitionNameInfo;
        FirstActivity.myAppVariables.scouterName = scouterNameInfo;
        FirstActivity.myAppVariables.matchNumber = matchNumberInfo + 1;
        FirstActivity.myAppVariables.allianceColor = allianceColor;
        startActivity(intent);
    }

    public void climb (View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.climbedNo:
                if (checked == false) {
                    robotClimbed = true;
                } else {

                }
                break;
            case R.id.climbedYes:
                if (checked == true) {
                    robotClimbed = true;
                }
                break;
        }

    }

    public void parked (View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.parkedNo:
                if(checked == false) {
                    robotParked = true;
                } else {

                }
                break;
            case R.id.parkedYes:
                if(checked == true) {
                    robotParked = true;
                }
                break;

        }

    }
    public void broke (View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.brokeNo:
                if (checked == false) {
                    robotBroke = true;
                }
                break;
            case R.id.brokeYes:
                if (checked == true) {
                    robotBroke = true;
                }
                break;
        }
    }

    public void tipped (View view ) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.tippedNo:
                if(checked == false) {
                    robotTipped = true;
                }
                break;
            case R.id.tippedYes:
                if(checked == true) {
                    robotTipped = true;
                }
                break;
        }
    }
    public void submitNew (View view) {
        useBluetoothActivity = false;
        saveFileOnly = false;
        this.createCSV(view);

        //zeroes out all values when submitted
        myAppVariables.crossBaselineAuto = 0;
        myAppVariables.foulAuto = 0;
        myAppVariables.numberCubesSwitchPlacedAuto = 0;
        myAppVariables.numberCubesScale = 0;
        myAppVariables.numberCubesSwitchPlacedTeleop = 0;
        myAppVariables.numberCubesOpponentSwitchPlacedTeleop = 0;
        myAppVariables.numberCubesScaleTeleop = 0;
        myAppVariables.numberCubesVault = 0;
        myAppVariables.numberCubesHuman = 0;
        myAppVariables.numberCubesDroppedTeleop = 0;
        myAppVariables.numberCubesFromGround = 0;
        myAppVariables.numberCubesStuck = 0;
        //myAppVariables.scouterName = "";
        //myAppVariables.matchNumber = 0;
        //myAppVariables.robotNumber = 0;


        //EditText editText = (EditText) findViewById(R.id.enterPenalty);
//        if(editText.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), "Enter number of penalties", Toast.LENGTH_LONG).show();
//            return;
//        }
    }


    public void submitOld (View view) {
        useBluetoothActivity = true;
        saveFileOnly = false;
        this.createCSV(view);
    }

    public void saveFile (View view) {
        useBluetoothActivity = false;
        saveFileOnly = true;
        this.createCSV(view);

        //EditText editText = (EditText) findViewById(R.id.enterPenalty);
//        if(editText.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), "Enter number of penalties", Toast.LENGTH_LONG).show();
//            return;
//        }
    }

    public void createCSV (View view) {
        if (robotClimbed == true) {
            GameEvent climb = new GameEvent();
            climb.eventType = "climb";
            climb.eventValue = "1";
            climb.eventTime = System.currentTimeMillis();
            myAppVariables.eventList.add(climb);
        }
        if (robotBroke == true) {
            GameEvent broke = new GameEvent();
            broke.eventType = "broke";
            broke.eventValue = "1";
            broke.eventTime = System.currentTimeMillis();
            myAppVariables.eventList.add(broke);
        }
        if(robotParked = true) {
            GameEvent park = new GameEvent();
            park.eventType = "park";
            park.eventValue = "1";
            park.eventTime = System.currentTimeMillis();
            myAppVariables.eventList.add(park);

        }
        if(robotTipped == true) {
            GameEvent tipped = new GameEvent();
            tipped.eventType = "tipped";
            tipped.eventValue = "1";
            tipped.eventTime = System.currentTimeMillis();
            myAppVariables.eventList.add(tipped);
        }

//        GameEvent allianceColor = new GameEvent();
//        allianceColor.eventType = "allianceColor";
//        if (FirstActivity.myAppVariables.allianceColor == true) {
//            allianceColor.eventValue = "Blue";
//        } else {
//            allianceColor.eventValue = "Red";
//        }
//        allianceColor.eventTime = System.currentTimeMillis();
//        FirstActivity.myAppVariables.eventList.add(allianceColor);



//        GameEvent alliancePosition = new GameEvent();
//        alliancePosition.eventType = "alliancePosition";
//        if(FirstActivity.myAppVariables.robotPosition == 1) {
//            alliancePosition.eventValue = "1";
//        } else if(FirstActivity.myAppVariables.robotPosition == 2) {
//            alliancePosition.eventValue = "2";
//        } else if(FirstActivity.myAppVariables.robotPosition == 3) {
//            alliancePosition.eventValue = "3";
//        }
//        alliancePosition.eventTime = System.currentTimeMillis();
//        FirstActivity.myAppVariables.eventList.add(alliancePosition);

//        for (int c = 0; c < FirstActivity.myAppVariables.numberHighGoalsAuto; c++) {
//            GameEvent highGoalAuto = new GameEvent();
//            highGoalAuto.eventType = "highGoalAuto";
//            highGoalAuto.eventValue = "1";
//            highGoalAuto.eventTime = System.currentTimeMillis();
//            FirstActivity.myAppVariables.eventList.add(highGoalAuto);
//        }

//        for (int c = 0; c < (FirstActivity.myAppVariables.numberHighGoalsTeleop - FirstActivity.myAppVariables.numberHighGoalsAuto); c++) {
//            GameEvent highGoal = new GameEvent();
//            highGoal.eventType = "highGoal";
//            highGoal.eventValue = "1";
//            highGoal.eventTime = System.currentTimeMillis();
//            FirstActivity.myAppVariables.eventList.add(highGoal);
//        }

        myAppVariables.CSVCreate(this,useBluetoothActivity,saveFileOnly);
        if (useBluetoothActivity == false) {
            startFirstActivity();
        }
    }
}
