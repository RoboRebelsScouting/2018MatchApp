package scouting2017.matchapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//This class contains the necessary variables used throughout the app
/**
 * Created by mcgrathg19 on 1/18/2017.
 */

public class Variables {

    //public Intent firstActivity;
    public int numberCubesSwitchPlacedAuto;
    public int numberOfCubesSwitch;
    public int numberCubesScale;
    //public int numberCubesSwitchPlacedTeleop;
    public int numberHighGoalsAuto;
    public int crossBaselineAuto;
    public int foulAuto;
    public int numberCubesSwitchPlacedTeleop;
    public int numberCubesDroppedAuto;
    public int numberCubesOpponentSwitchPlacedTeleop;
    public int numberCubesScaleTeleop;
    public int numberCubesVault;
    public int numberCubesHuman;
    public int numberCubesDroppedTeleop;
    public int numberCubesFromGround;
    public List<GameEvent> eventList;
    public long startAutoTime;
    public long autoTime;
    public boolean timerStarted = false;
    public long startTeleopTime;
    public long teleopTime;
    public int robotNumber;
    public boolean allianceColor = false;
    public int matchNumber;
    public String scouterName;
    public String competitionName;
    public int numberHighGoalsTeleop;
    public BluetoothClient btClient;
    public boolean btClientSendOnStart = false;
    public String btClientFileName;
    public String btClientMessageString;
    public int robotPosition = 0;
    public Activity btClientActivity;
    ArrayList<Match> matchArrayList = new ArrayList<Match>();

    public Variables() {

        reset();
    }

    public void getMatchSchedule () {
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "") ;
        String fileName = competitionName + "_schedule.dat.csv";
        File file = new File(folder,fileName);

        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                try {
                    while ((line = br.readLine()) != null) {
                        String[] lineList = line.split(",");
                        Match newMatch = new Match();
                        // skip header line
                        if (!lineList[0].equalsIgnoreCase("Start Time")) {
                            newMatch.matchNumber = Integer.parseInt(lineList[1]);
                            newMatch.red1 = Integer.parseInt(lineList[2]);
                            newMatch.red2 = Integer.parseInt(lineList[3]);
                            newMatch.red3 = Integer.parseInt(lineList[4]);
                            newMatch.blue1 = Integer.parseInt(lineList[5]);
                            newMatch.blue2 = Integer.parseInt(lineList[6]);
                            newMatch.blue3 = Integer.parseInt(lineList[7]);
                            matchArrayList.add(newMatch);
                        }
                    }
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRobotNumber(int matchNumber, boolean allianceColor, int robotPosition) {
        for (Match m : matchArrayList) {
            if (m.matchNumber == matchNumber) {
                if (!allianceColor) {
                    if (robotPosition == 1) {
                        return m.red1;
                    } else if (robotPosition == 2) {
                        return m.red2;
                    } else {
                        return m.red3;
                    }
                } else {
                    if (robotPosition == 1) {
                        return m.blue1;
                    } else if (robotPosition == 2) {
                        return m.blue2;
                    } else {
                        return m.blue3;
                    }
                }
            }
        }
        return 0;
    }

    public void setAssignment(String assignment) {
        switch (assignment) {
            case "Red 1":
                allianceColor = false;
                robotPosition = 1;
                break;
            case "Red 2":
                allianceColor = false;
                robotPosition = 2;
                break;
            case "Red 3":
                allianceColor = false;
                robotPosition = 3;
                break;
            case "Blue 1":
                allianceColor = true;
                robotPosition = 1;
                break;
            case "Blue 2":
                allianceColor = true;
                robotPosition = 2;
                break;
            case "Blue 3":
                allianceColor = true;
                robotPosition = 3;
                break;
        }
    }

    public void startBluetoothWithFile(Activity theActivity, String fileString, String fileNameBase) {
        btClientSendOnStart = true;
        btClientFileName = btClient.fname =  String.format("%50s",fileNameBase);
        btClientMessageString = fileString;
        btClientActivity = theActivity;
        btClient = startBluetooth(theActivity);
    }
    public BluetoothClient startBluetooth(Activity theActivity) {
        // create bluetooth client and send file
        int REQUEST_ENABLE_BT = 1;
        BluetoothAdapter mBluetoothAdapter = android.bluetooth.BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(theActivity.getApplicationContext(), "No Bluetooth", Toast.LENGTH_LONG).show();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE);
            theActivity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // bluetooth enabled
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                if (deviceName.equalsIgnoreCase("TestingServer")) {
                    // create the client, set the file namd and message string, start the thread to send it
                    btClient = new BluetoothClient(mBluetoothAdapter,device);

                    if (btClientSendOnStart) {
                        btClient.sendOnStart = btClientSendOnStart;
                        btClient.launchActivity = btClientActivity;
                        btClient.fname = btClientFileName;
                        btClient.messageString = btClientMessageString;
                    }
                    btClient.start();
                    return btClient;
                }
            }
        }
        return null;
    }

    public void reset() {
        numberCubesSwitchPlacedAuto = 0;
        numberOfCubesSwitch = 0;
        crossBaselineAuto = 0;
        eventList = new ArrayList<GameEvent>();
        scouterName = "" ;
        competitionName = "" ;
        allianceColor = false ;
        matchNumber = 0 ;
        numberHighGoalsTeleop = 0;
        timerStarted = false;
    }

    /* some random code Olivia has
     public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    } */

    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), albumName) ;
        if (!file.mkdirs()) {
            Log.e("ERROR", "directory NOT Created");
        }
        return file;
    }

    void CSVCreate(Activity theActivity, Boolean useBluetoothActivity, Boolean saveFileOnly) {
        String fileNameBase = competitionName + "-" + matchNumber + "-" + robotNumber + "-" + scouterName.trim();
        String fileName = fileNameBase +
                ".csv";
        File directory = getAlbumStorageDir("/FRC2018");
        File file = new File(directory,fileName);

        String fileString = new String();

        try {
            FileWriter writer = new FileWriter(file);
            String lineOne = competitionName + "," + matchNumber + "," + robotNumber + "," + scouterName.trim() ;

            writer.write(lineOne + "\n");
            fileString = fileString + lineOne + "\n";

            for (int c = 0; c < eventList.size(); c++) {
                String output = "";
                Long timeSinceStart = (eventList.get(c).eventTime - startAutoTime)/1000 ;
                output = timeSinceStart + "," +
                        eventList.get(c).eventType + "," +
                        eventList.get(c).eventValue;

                writer.write(output + "\n");
                fileString = fileString + output + "\n";

            }

            writer.close();
            //
            if (useBluetoothActivity == true) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                theActivity.startActivityForResult(intent, 0);
            //
            } else if (saveFileOnly == false){
                //btClient.start();
                btClient.fname =  String.format("%50s",fileNameBase);
                btClient.messageString = fileString;
                btClient.launchActivity = theActivity;

                // if not connected
                if (!btClient.mmSocket.isConnected()) {
                    btClient.cancel();
                    this.startBluetoothWithFile(theActivity,fileString,fileNameBase);
                } else {
                    btClient.btSend(fileString);
                }
            }
        } catch (IOException e) {
            Log.e("ERROR", "File NOT Created", e);
        }
    }
}


