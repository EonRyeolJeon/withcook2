package com.example.opencval;

import android.app.Application;

public class rgbset extends Application {

    private static Integer beforered = 0;
    private static Integer beforegreen = 0 ;
    private static Integer beforeblue = 0;
    private static Integer nextred=0;
    private static Integer nextgreen=0;
    private static Integer nextblue=0;

    public static Integer getBeforered(){
        return beforered;
    }

    public void setBeforered(Integer globalint){
        this.beforered = globalint;
    }


    public static Integer getBeforegreen(){
        return beforegreen;
    }

    public void setBeforegreen(Integer globalint){
        this.beforegreen = globalint;
    }

    public static Integer getBeforeblue(){
        return beforeblue;
    }

    public void setBeforeblue(Integer globalint){
        this.beforeblue = globalint;
    }

    public static Integer getNextred(){
        return nextred;
    }

    public void setNextred(Integer globalint){
        this.nextred = globalint;
    }


    public static Integer getNextblue(){
        return nextblue;
    }

    public void setNextblue(Integer globalint){
        this.nextblue = globalint;
    }


    public static Integer getNextgreen(){
        return nextgreen;
    }

    public void setNextgreen(Integer globalint){
        this.nextgreen = globalint;
    }

}
