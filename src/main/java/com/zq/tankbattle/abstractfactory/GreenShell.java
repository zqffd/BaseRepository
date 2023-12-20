package com.zq.tankbattle.abstractfactory;

import com.zq.tankbattle.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 作者:ZQ
 * 时间:2023/12/14 10:03
 * 发射炮弹实体类
 */
public class GreenShell extends BaseShell{

    //移动速度
    public static final int SPEED = 10;

    //大小
    public static int WIDTH = ResourceMgr.bombD.getWidth();
    public static int HEIGHT = ResourceMgr.bombD.getHeight();


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


    public GreenShell(int x, int y, Dir dir, Group group, TankFrame tf) {
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
                tankD = ResourceMgr.bombD;
                break;
            case LEFT:
                tankD = ResourceMgr.bombL;
                break;
            case RIGHT:
                tankD = ResourceMgr.bombR;
                break;
            case UP:
                tankD = ResourceMgr.bombU;
                break;
            case DL:
                tankD = ResourceMgr.bombDL;
                break;
            case DR:
                tankD = ResourceMgr.bombDR;
                break;
            case UL:
                tankD = ResourceMgr.bombUL;
                break;
            case UR:
                tankD = ResourceMgr.bombUR;
                break;
            case UR1:
                tankD = ResourceMgr.bombUR1;
                break;
            case UR2:
                tankD = ResourceMgr.bombUR2;
                break;
            case UR3:
                tankD = ResourceMgr.bombUR3;
                break;
            case UR4:
                tankD = ResourceMgr.bombUR4;
                break;
            case UL1:
                tankD = ResourceMgr.bombUL1;
                break;
            case UL2:
                tankD = ResourceMgr.bombUL2;
                break;
            case UL3:
                tankD = ResourceMgr.bombUL3;
                break;
            case UL4:
                tankD = ResourceMgr.bombUL4;
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
