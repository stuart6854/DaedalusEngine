package main.java.org.daedalus.graphics.ui;

import main.java.org.daedalus.graphics.Window;
import main.java.org.daedalus.utils.FileUtils;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memAllocDouble;
import static org.lwjgl.system.MemoryUtil.memFree;

/**
 * Created by Stuart on 31/01/2017.
 */
public class GUI {
    
    private long vg;
    
    private NVGColor color;
    
    private DoubleBuffer posX, posY;
    
    private ByteBuffer fontBuffer;
    private String fontName = "Regular";
    
    public void Initialise(){
        vg = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES);
        if(vg == NULL)
            throw new RuntimeException("Could not init NanoVG!");
        
        fontBuffer = FileUtils.ReadFile("resources/fonts/OpenSans-Regular.ttf");
        int font = NanoVG.nvgCreateFontMem(vg, fontName, fontBuffer, 0);
        if(font == -1)
            throw new RuntimeException("Could not add font!");
        
        color = NVGColor.create();
        
        posX = memAllocDouble(1);
        posY = memAllocDouble(1);
    }
    
    public void Render(){
        int wWidth = Window.getResolution().width;
        int wHeight = Window.getResolution().height;
        
        nvgBeginFrame(vg, wWidth, wHeight, 1);
        
        //Blue Ribbon
        nvgBeginPath(vg);
        nvgRect(vg, 0, wHeight - 100, wWidth, 50);
        nvgFillColor(vg, rgba(0x23, 0xa1, 0xf1, 200, color));
        nvgFill(vg);
        
        //Text
        nvgFontSize(vg, 40.0f);
        nvgFontFace(vg, fontName);
        nvgTextAlign(vg, NVG_ALIGN_CENTER | NVG_ALIGN_TOP);
        nvgFillColor(vg, rgba(0xe6, 0xea, 0xed, 255, color));
        nvgText(vg, wWidth / 2, wHeight - 95, "Hello World!");
        
        
        nvgEndFrame(vg);
        
        //Restore OpenGL State
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_STENCIL_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
    
    public void Cleanup(){
        nvgDelete(vg);
        memFree(posX);
        memFree(posY);
    }
    
    private NVGColor rgba(int r, int g, int b, int a, NVGColor colour) {
        colour.r(r / 255.0f);
        colour.g(g / 255.0f);
        colour.b(b / 255.0f);
        colour.a(a / 255.0f);
        
        return colour;
    }
    
}
