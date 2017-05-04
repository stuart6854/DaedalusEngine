package main.java.org.daedalus.graphics.ui;

import main.java.org.daedalus.graphics.Window;
import main.java.org.daedalus.utils.FileUtils;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nuklear.NkContext;

import static org.lwjgl.nuklear.Nuklear.*;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import static org.lwjgl.nanovg.NanoVGGL3.*;
import static org.lwjgl.system.MemoryUtil.*;

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

        //Nuklear
//        NkContext nkContext = NkContext.create();
//        nk_init_fixed(nkContext, memCalloc(1, 1024), null);

        // NanoVG
//        nvgBeginFrame(vg, wWidth, wHeight, 1);
//
//        //Blue Ribbon
//        nvgBeginPath(vg);
//        nvgRect(vg, 0, wHeight - 100, wWidth, 50);
//        nvgFillColor(vg, Color.ToNVGColor(35, 161, 241, 200));
//        nvgFill(vg);
//
//        //Text
//        nvgFontSize(vg, 40.0f);
//        nvgFontFace(vg, fontName);
//        nvgTextAlign(vg, NVG_ALIGN_CENTER | NVG_ALIGN_TOP);
//        nvgFillColor(vg, Color.ToNVGColor(230, 234, 237, 255));
//        nvgText(vg, wWidth / 2, wHeight - 95, "Hello World!");
//
//        nvgEndFrame(vg);
        
        //Restore OpenGL State
        Window.SetGL();
    }
    
    public void Cleanup(){
        nvgDelete(vg);
        memFree(posX);
        memFree(posY);
    }
    
}
