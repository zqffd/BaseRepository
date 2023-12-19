package com.zq.tankbattle;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 作者:ZQ
 * 时间:2023/12/14 10:03
 * 发射炮弹实体类
 */
public class Shell {

    //移动速度
    public static final int SPEED = 10;

    //大小
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();


    int x, y;

    //子弹方向
    public Dir dir;

    //子弹颜色
    public Color color = Color.BLACK;

    //子弹是否活着(子弹飞出画面就死了，子弹打中其它坦克也死了)
    public boolean living = true;

    //区分敌方子弹还是我方子弹
    public Group group = Group.BAD;


    public TankFrame tf;

    public Rectangle rect = new Rectangle();


    public Shell(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.shellList.add(this);
    }


    public void paint(Graphics g) {
        if (!living) {
            tf.shellList.remove(this);
        }
        BufferedImage tankD = null;

        switch (dir) {
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
            case UR1:
                tankD = ResourceMgr.bulletUR1;
                break;
            case UR2:
                tankD = ResourceMgr.bulletUR2;
                break;
            case UR3:
                tankD = ResourceMgr.bulletUR3;
                break;
            case UR4:
                tankD = ResourceMgr.bulletUR4;
                break;
            case UL1:
                tankD = ResourceMgr.bulletUL1;
                break;
            case UL2:
                tankD = ResourceMgr.bulletUL2;
                break;
            case UL3:
                tankD = ResourceMgr.bulletUL3;
                break;
            case UL4:
                tankD = ResourceMgr.bulletUL4;
                break;
            default:
                break;
        }


        //画一张图片
        g.drawImage(tankD, x, y, null);


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

        switch (dir){
            case UL1:
                y -= SPEED;
                x -= SPEED-8;
                break;
            case UL2:
                y -= SPEED;
                x -= SPEED-6;
                break;
            case UL3:
                y -= SPEED;
                x -= SPEED-4;
                break;
            case UL4:
                y -= SPEED;
                x -= SPEED-2;
                break;
            case UR1:
                y -= SPEED;
                x += SPEED-8;
                break;
            case UR2:
                y -= SPEED;
                x += SPEED-6;
                break;
            case UR3:
                y -= SPEED;
                x += SPEED-4;
            case UR4:
                y -= SPEED;
                x += SPEED-2;
                break;
            default:
                break;
        }


        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDIH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }


        //子弹的矩形要跟着移动而变化
        rect.x = x;
        rect.y = y;

    }


    //发生碰撞
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;

        ////获取子弹矩形和坦克矩形，判断子弹和坦克的方块是否相交,发生相交，两个都消失
        if (rect.intersects(tank.rect())) {
            tank.die();
            this.die();
        }


    }

    /**
     * Author: ZQ
     * Date: 2023/12/14
     * Decription: 子弹是否活着
     */
    private void die() {
        this.living = false;
    }
}
