package main.java.org.daedalus.utils;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Debug {

    public static void Log(Object _message){
        System.out.println(_message);
    }

    public static void Error(Object _error){
        System.err.println("Error: " + _error);
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        for(int i = 1; i < stackTraces.length; i++){
            StackTraceElement stackTrace = stackTraces[i];
            System.err.println("\t" + stackTrace.getClassName() + "." + stackTrace.getMethodName() + "():" + stackTrace.getLineNumber());
        }
    }


}
