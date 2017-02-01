package main.java.org.daedalus.utils;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Time {

    //private static float realTimeSinceStartup; // Time (in seconds) since the engine started. Unaffected by timeScale.
    //private static float timeScale; // The scale that time passes.
    private static float deltaTime; // The time (in ms) taken to complete the last frame.
    private static float fixedDeltaTime = 1.0f / 60.0f; //0.2f; // The time (ms) between physics updates.

    public void setDeltaTime(double _deltaTime){
        Time.deltaTime = (float)_deltaTime;
    }

    public static float getDeltaTime(){
        return deltaTime;
    }

    public static float getFixedDeltaTime(){
        return fixedDeltaTime;
    }
    
}
