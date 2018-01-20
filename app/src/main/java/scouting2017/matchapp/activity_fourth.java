package scouting2017.matchapp;

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
public boolean robotBroke = false;
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
    public void submitNew (View view) {
        useBluetoothActivity = false;
        saveFileOnly = false;
        this.createCSV(view);

        EditText editText = (EditText) findViewById(R.id.enterPenalty);
        if(editText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter number of penalties", Toast.LENGTH_LONG).show();
            return;
        }
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

        EditText editText = (EditText) findViewById(R.id.enterPenalty);
        if(editText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter number of penalties", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void createCSV (View view) {
        if (robotClimbed == true) {
            GameEvent climbed = new GameEvent();
            climbed.eventType = "climbed";
            climbed.eventValue = "1";
            climbed.eventTime = System.currentTimeMillis();
            myAppVariables.eventList.add(climbed);
        }
        if (robotBroke == true) {
            GameEvent broken = new GameEvent();
            broken.eventType = "broken";
            broken.eventValue = "1";
            broken.eventTime = System.currentTimeMillis();
            myAppVariables.eventList.add(broken);
        }
        GameEvent colorOfAlliance = new GameEvent();
        colorOfAlliance.eventType = "allianceColor";
        if (FirstActivity.myAppVariables.allianceColor == true) {
            colorOfAlliance.eventValue = "Blue";
        } else {
            colorOfAlliance.eventValue = "Red";
        }
        colorOfAlliance.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(colorOfAlliance);

        for (int c = 0; c < FirstActivity.myAppVariables.numberHighGoalsAuto; c++) {
            GameEvent highGoalAuto = new GameEvent();
            highGoalAuto.eventType = "highGoalAuto";
            highGoalAuto.eventValue = "1";
            highGoalAuto.eventTime = System.currentTimeMillis();
            FirstActivity.myAppVariables.eventList.add(highGoalAuto);
        }

        for (int c = 0; c < (FirstActivity.myAppVariables.numberHighGoalsTeleop - FirstActivity.myAppVariables.numberHighGoalsAuto); c++) {
            GameEvent highGoal = new GameEvent();
            highGoal.eventType = "highGoal";
            highGoal.eventValue = "1";
            highGoal.eventTime = System.currentTimeMillis();
            FirstActivity.myAppVariables.eventList.add(highGoal);
        }

        myAppVariables.CSVCreate(this,useBluetoothActivity,saveFileOnly);
        if (useBluetoothActivity == false) {
            startFirstActivity();
        }
    }
}
