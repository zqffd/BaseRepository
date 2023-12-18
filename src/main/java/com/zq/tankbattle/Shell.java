package com.zq.tankbattle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

/**
 * 作者:ZQ
 * 时间:2023/12/14 10:03
 * 发射炮弹
 */
public class Shell {

    //移动速度
    private static final int SPEED = 10;

    //大小
    public static  int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();



    int x, y;

    //子弹方向
    private Dir dir;

    //子弹颜色
    private Color color = Color.BLACK;

    //子弹是否活着(子弹飞出画面就死了，子弹打中其它坦克也死了)
    private boolean living = true;

    //区分敌方子弹还是我方子弹
    private Group group = Group.BAD;

    private TankFrame tf;

    public  Rectangle rect =  new Rectangle();




    public Shell(int x, int y, Dir dir,Group group ,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf = tf;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }


    public void paint(Graphics g) {
        if (!living) {
            tf.shellList.remove(this);
        }
        BufferedImage tankD = ResourceMgr.tankD;

        switch (dir){
            case DOWN:
                tankD = ResourceMgr.bulletD;
                break;
            case LEFT:
                tankD = ResourceMgr.bulletL;
                break;
            case RIGHT:
                tankD = ResourceMgr.bulletR;
                break;
            case UP:
                tankD = ResourceMgr.bulletU;
                break;
            case DL:
                tankD = ResourceMgr.bulletDL;
                break;
            case DR:
                tankD = ResourceMgr.bulletDR;
                break;
            case UL:
                tankD = ResourceMgr.bulletUL;
                break;
            case UR:
                tankD = ResourceMgr.bulletUR;
                break;

        }
        //画一张图片
        g.drawImage(tankD,x,y,null);



        move();

        //设置颜色
//        g.setColor(yy1);
//        g.fillRect(x1, y1, 100, 100);


    }


    /**
     * Author: ZQ
     * Date: 2023/12/14
     * Decription: 移动
     */
    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case DR:
                y += SPEED;
                x += SPEED;
                break;
            case DL:
                x -= SPEED;
                y += SPEED;
                break;
            case UR:
                y -= SPEED;
                x += SPEED;
                break;
            case UL:
                y -= SPEED;
                x -= SPEED;
                break;
        }


        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDIH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }


        //子弹的矩形要跟着移动而变化
        rect.x=x;
        rect.y=y;

    }


    //发生碰撞
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;

        ////获取子弹矩形和坦克矩形，判断子弹和坦克的方块是否相交,发生相交，两个都消失
        if (rect.intersects(tank.rect())){
            tank.die();
            this.die();
        }


    }

    /**
      Author: ZQ
      Date: 2023/12/14
      Decription: 子弹是否活着
    */
    private void die() {
        this.living = false;
    }
}
