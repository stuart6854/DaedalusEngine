package main.java.org.daedalus.utils;

import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.ByteBuffer;
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
    
    public static ByteBuffer ReadFile(String _path){
        try {
            File file = new File(_path);
            
            byte[] bytes = Files.readAllBytes(new File(_path).toPath());
            ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
            buffer.put(bytes);
            
            buffer.flip();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
