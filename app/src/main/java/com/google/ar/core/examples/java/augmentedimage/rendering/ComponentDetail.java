package com.google.ar.core.examples.java.augmentedimage.rendering;

public class ComponentDetail {
    public String comNanme;
    public String comDetail;
    public float posx,posy,posz;
    public int numberOfPlace;

    public ComponentDetail(String comNanme, String comDetail, float posx, float posy, float posz, int numberOfPlace) {
        this.comNanme = comNanme;
        this.comDetail = comDetail;
        this.posx = posx;
        this.posy = posy;
        this.posz = posz;
        this.numberOfPlace = numberOfPlace;
    }
    public ComponentDetail( float posx, float posy, float posz) {
        this.comNanme = comNanme;
        this.comDetail = comDetail;
        this.posx = posx;
        this.posy = posy;
        this.posz = posz;
        this.numberOfPlace = numberOfPlace;
    }
}
