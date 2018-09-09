package com.example.asus.ar_application;

import java.io.Serializable;

public class Box implements Serializable {
    public int face;
    public int Xpos;
    public  int Ypos;

    public Box(int face,int x,int y){
        this.face = face;
        this.Xpos = x;
        this.Ypos = y;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getXpos() {
        return Xpos;
    }

    public void setXpos(int xpos) {
        Xpos = xpos;
    }

    public int getYpos() {
        return Ypos;
    }

    public void setYpos(int ypos) {
        Ypos = ypos;
    }
}
