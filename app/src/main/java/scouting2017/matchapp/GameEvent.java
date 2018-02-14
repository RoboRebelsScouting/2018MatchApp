package scouting2017.matchapp;

/**
 * Created by mcgrathg19 on 1/21/2017.
 */

public class GameEvent {
    public GameEvent() {

    }

    public GameEvent(String eventType) {
        this(eventType,"1");
    }

    public GameEvent(String eventType, String eventValue) {
        this.eventType = eventType;
        this.eventValue = eventValue;
        this.eventTime = System.currentTimeMillis();
    }



    public String eventType ;
    public String eventValue ;
    public Long eventTime ;
}
