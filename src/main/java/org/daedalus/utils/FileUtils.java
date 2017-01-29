package main.java.org.daedalus.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Stuart on 27/01/2017.
 */
public class FileUtils {
    
    public static String ReadTextFile(String _path){
        
        StringBuilder builder = new StringBuilder();
        try{
            List<String> lines = Files.readAllLines(new File(_path).toPath());
            for(String line : lines)
                builder.append(line).append("\n");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    
        
    }
    
}
