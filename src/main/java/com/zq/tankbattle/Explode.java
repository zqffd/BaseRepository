package com.zq.tankbattle;

import com.zq.tankbattle.utils.PathUtils;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

/**
 * 作者:ZQ
 * 时间:2023/12/14 10:03
 * 坦克死亡后的爆炸特效
 */
@Data
public class Explode {

    //大小
    public static  int WIDTH = ResourceMgr.blowUpList[0].getWidth();
    public static int HEIGHT = ResourceMgr.blowUpList[0].getHeight();

    int x, y;

    //子弹是否活着(子弹飞出画面就死了，子弹打中其它坦克也死了)
    private boolean living = true;

    private TankFrame tf;

    private int step = 1;


    private Audio audio;



    public Explode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
         audio = new Audio(PathUtils.MUSIC.path() + "explode.wav");

    }


    public void paint(Graphics g) {
        //声音
//        CompletableFuture.runAsync(()->audio.play());
        g.drawImage(ResourceMgr.blowUpList[step++], x, y, null);

        if(step >= ResourceMgr.blowUpList.length)
            tf.blowUpList.remove(this);




    }
}
