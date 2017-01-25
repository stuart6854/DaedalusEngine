package main.java.org.daedalus.utils;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Time {

    //private static float realTimeSinceStartup; // Time (in seconds) since the engine started. Unaffected by timeScale.
    //private static float timeScale; // The scale that time passes.
    private static double deltaTime; // The time taken to complete the last frame.

    public void setDeltaTime(double _deltaTime){
        Time.deltaTime = _deltaTime;
    }

    public static double getDeltaTime(){
        return deltaTime;
    }

}
