
package scouting2017.matchapp;
//Code for starting page of app
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import BluetoothStatusActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class FirstActivity extends AppCompatActivity {

    public static Variables myAppVariables ;
    public static Activity appActivity;
    public boolean allianceColor;
    public int robotPosition;


    @Override
    public void onBackPressed() {
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appActivity = this;

        if (myAppVariables == null) {
            myAppVariables = new Variables () ;
            myAppVariables.startBluetooth(this);
        } else {
            if (!myAppVariables.btClient.mmSocket.isConnected()) {
                myAppVariables.btClient.cancel();
                myAppVariables.startBluetooth(this);
            }
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String assignment = sharedPref.getString("pref_assignment", "Red 1");
//        myAppVariables.setAssignment(assignment);

            setContentView(R.layout.activity_first);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
        TextView eventNameText = (TextView) findViewById(R.id.enterEvent);
        EditText scouterNameText = (EditText) findViewById(R.id.enterName);
        final EditText matchNumberText = (EditText) findViewById(R.id.enterMatch);
        //final EditText penaltyText = (EditText) findViewById(R.id.enterPenalty);

        // whenver match number is entered manually, try to get the robot number
        matchNumberText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (myAppVariables.robotPosition != 0) {
                        myAppVariables.matchNumber = Integer.parseInt(matchNumberText.getText().toString());
                        myAppVariables.robotNumber =
                                myAppVariables.getRobotNumber(myAppVariables.matchNumber, myAppVariables.allianceColor, myAppVariables.robotPosition);
                        EditText robotText = (EditText) findViewById(R.id.enterRobot);
                        robotText.setText(String.valueOf(myAppVariables.robotNumber));
                    }
                }
            }
        });

        if (!myAppVariables.scouterName.equals("")) {
            scouterNameText.setText(myAppVariables.scouterName);
        }
        if (!myAppVariables.scouterName.equals("")) {
            matchNumberText.setText(Integer.toString(myAppVariables.matchNumber));
            // if robotPosition is set and we have matc schedule loaded, set robot number here
            if (myAppVariables.robotPosition != 0){
                myAppVariables.robotNumber =
                myAppVariables.getRobotNumber(myAppVariables.matchNumber, myAppVariables.allianceColor, myAppVariables.robotPosition);
                EditText robotText = (EditText) findViewById(R.id.enterRobot);
                robotText.setText (String.valueOf(myAppVariables.robotNumber));
            }
        }
        try {
            long currentTimeInMillis = System.currentTimeMillis();

            if ((currentTimeInMillis >= sdf.parse("Feb 9 2018").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Feb 20 2018").getTime() )) {
                eventNameText.setText("Week_0");
            } else if ((currentTimeInMillis >= sdf.parse("Feb 19 2018").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 12 2018").getTime() )) {
                eventNameText.setText("WPI");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 13 2018").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 27 2018").getTime() )) {
                eventNameText.setText("Bryant");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 28 2018").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Apr 9 2018").getTime() )) {
                eventNameText.setText("UNH");
            }  else {
                eventNameText.setText("Worlds");
            }
            if (myAppVariables.competitionName.equalsIgnoreCase("")) {
                myAppVariables.competitionName = eventNameText.getText().toString();
                myAppVariables.getMatchSchedule();
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
//            case R.id.action_status:
//                startActivity(new Intent(this, BluetoothStatusActivity.class));
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void toAuto(View view) {

        EditText t = (EditText) findViewById(R.id.enterRobot);
        if (t.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a Robot Number", Toast.LENGTH_LONG).show();
            return;
        }
        FirstActivity.myAppVariables.robotNumber = Integer.parseInt(t.getText().toString());
        EditText g = (EditText) findViewById(R.id.enterMatch);
        if (g.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a Match Number", Toast.LENGTH_LONG).show();
            return;
        }
        FirstActivity.myAppVariables.matchNumber = Integer.parseInt(g.getText().toString());
        TextView c = (TextView) findViewById(R.id.enterEvent);
        FirstActivity.myAppVariables.competitionName = c.getText().toString();
        EditText f = (EditText) findViewById(R.id.enterName);
        if (f.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Scouter Name", Toast.LENGTH_LONG).show();
            return;
        }
        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String robotPositionString = sharedPref.getString("pref_assignment", null);
        //myAppVariables.robotColor = robotPositionString;
        GameEvent allianceValue  = new GameEvent();
        switch (robotPositionString) {
            case "Red 1":
                // Red 1 processing code
                allianceColor = false;
                myAppVariables.robotColor = "Red";
                myAppVariables.robotPosition = 1;
                break;
            case "Red 2":
                //Red 2 processing code
                allianceColor = false;
                myAppVariables.robotColor = "Red";
                myAppVariables.robotPosition = 2;
                break;
            case "Red 3":
                //Red 3 processing code
                allianceColor = false;
                myAppVariables.robotColor = "Red";
                myAppVariables.robotPosition = 3;
                break;
            case "Blue 1":
                //Blue 1 processing code
                allianceColor = true;
                myAppVariables.robotColor = "Blue";
                myAppVariables.robotPosition = 1;
                break;
            case "Blue 2":
                //Blue 2 processing code
                allianceColor = true;
                myAppVariables.robotColor = "Blue";
                myAppVariables.robotPosition = 2;
                break;
            case "Blue 3":
                //Blue 3 processing code
                allianceColor = true;
                myAppVariables.robotColor = "Blue";
                myAppVariables.robotPosition = 3;
                break;
        }


        if (myAppVariables.robotPosition == 0) {
            Toast.makeText(getApplicationContext(),"Select Robot Position (1,2, or 3)", Toast.LENGTH_LONG).show();
            return;
        }
        if(myAppVariables.matchNumber == 0) {
            Toast.makeText(getApplicationContext(), "Enter Match Number", Toast.LENGTH_LONG).show();
        }
        if(myAppVariables.robotNumber == 0) {
            Toast.makeText(getApplicationContext(), "Enter Robot Number", Toast.LENGTH_LONG).show();
        }



        FirstActivity.myAppVariables.scouterName = f.getText().toString();
        if(myAppVariables.scouterName == "") {
            Toast.makeText(getApplicationContext(), "Enter Scouter Name", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, secondActivity.class);
        myAppVariables.startAutoTime = System.currentTimeMillis();
        startActivity(intent);
    }
}