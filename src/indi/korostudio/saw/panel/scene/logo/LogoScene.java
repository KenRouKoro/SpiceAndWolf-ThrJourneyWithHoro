package indi.korostudio.saw.panel.scene.logo;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.scene.Scene;
import indi.korostudio.saw.system.cmd.CMD;
import indi.korostudio.saw.system.image.ImageBase;
import indi.korostudio.saw.tool.Tool;
import indi.korostudio.saw.tool.TweenTool;
import indi.korostudio.saw.tween.TweenActuator;
import indi.korostudio.saw.tween.TweenImplements;
import indi.korostudio.saw.tween.TweenListener;
import indi.korostudio.saw.tween.TweenSystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LogoScene extends Scene {
    protected BufferedImage image;
    protected TweenSystem in,out;
    protected TweenActuator inout;
    protected LogoScene logoScene=this;

    public LogoScene(){
        load();
    }

    @Override
    public void in() {
        in = TweenTool.SimpleTween(this,5f,TweenImplements.ALPHA,1f);

        out = TweenTool.SimpleTween(this,5f,TweenImplements.ALPHA,0f).addTweenListener(new TweenListener() {
            @Override
            public void start() {

            }

            @Override
            public void finish() {
                Data.scenePanel.remove(logoScene);
                next();
            }

            @Override
            public void pause() {

            }

            @Override
            public void stop() {

            }
        });

        inout = TweenTool.SimpleActuator(in,out);
        this.setAlpha(0f);
        this.setVisible(true);

        inout.start();
    }

    @Override
    public void out() {
        doNextScene();
    }

    protected void next(){
        CMD.getCMD().runCMD("show Main");
    }

    public void load(){
        setSize(Data.mainDimension);
        setVisible(false);
        setAlpha(0f);
        this.setLayout(null);
        image= ImageBase.get("logo-0");
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.drawImage(image,(getWidth()-getHeight())/2,0,getHeight(),getHeight(),null);
    }



}
