package com.zq.tankbattle;

import com.zq.tankbattle.strategy.fire.FireStrategy;
import com.zq.tankbattle.strategy.fire.impl.DefaultFireStrategy;
import com.zq.tankbattle.strategy.fire.impl.ShotFireStrategy;
import lombok.Data;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 作者:ZQ
 * 时间:2023/12/14 9:20
 */
@Data
public class Tank {
    public boolean ytrue;

    public boolean xtrue;

    //位置
    int x, y = 200;
    //方向
    private Dir dir = Dir.DOWN;
    //敌方坦克移动速度
    final int badSpeed = 1;
    //我方坦克移动速度
    public static int goodSpeed = 3;

    int incepetion = 0;

    //颜色
    Color color = Color.RED;

    //是否活着
    private boolean living = true;

    private TankFrame tf = null;

    //当前坦克的宽高
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private Random random = new Random();

    //区分是我方坦克还是地方坦克，默认敌方
    private Group group = Group.BAD;

    public  Rectangle rect =  new Rectangle();



    //是否移动
    private boolean moving = true;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.color = color;
        this.group = group;
        this.tf = tf;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }


    public void paint(Graphics g) {
        BufferedImage tankD;
        if (!living) tf.tankList.remove(this);

        if (group == Group.BAD) {
            tankD = ResourceMgr.tankR;
            //随机敌方方向
            randomBADDir();
            switch (dir) {
                case DOWN:
                    tankD = ResourceMgr.tankD;
                    break;
                case LEFT:
                    tankD = ResourceMgr.tankLeft;
                    break;
                case RIGHT:
                    tankD = ResourceMgr.tankR;
                    break;
                case UP:
                    tankD = ResourceMgr.tankUP;
                    break;
            }


        } else {
            tankD = ResourceMgr.meTankD;
            switch (dir) {
                case DOWN:
                    tankD = ResourceMgr.meTankD;
                    break;
                case LEFT:
                    tankD = ResourceMgr.meTankLeft;
                    break;
                case RIGHT:
                    tankD = ResourceMgr.meTankR;
                    break;
                case UP:
                    tankD = ResourceMgr.meTankUP;
                    break;
                case DL:
                    tankD = ResourceMgr.meTankDL;
                    break;
                case DR:
                    tankD = ResourceMgr.meTankDR;
                    break;
                case UL:
                    tankD = ResourceMgr.meTankUL;
                    break;
                case UR:
                    tankD = ResourceMgr.meTankUR;
                    break;
            }
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
     * Decription: 八方向移动
     */
    private void move() {
        if (!moving) return;

        if (group == Group.BAD) {
            modetail(badSpeed);
        } else if (group == Group.GOOD && this.dir != null) {
            modetail(goodSpeed);
        } else {
            return;
        }


        if (group == Group.BAD && random.nextInt(50) > 48) {
            this.fire(DefaultFireStrategy.getInstance());
        } else if (group == Group.GOOD && null != tf.event && tf.event.getKeyCode() == KeyEvent.VK_SPACE) {
            this.fire(ShotFireStrategy.getInstance());
        }

        //边界检测
        boundsCheck();

        //坦克矩形的位置要跟着移动变化
        rect.x = x;
        rect.y = y;

    }

    /**
     * Author: ZQ
     * Date: 2023/12/15
     * Decription: 边界检测
     */
    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDIH - Tank.WIDTH - 2) x = TankFrame.GAME_HEIGHT - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;


    }

    /**
     * Author: ZQ
     * Date: 2023/12/14
     * Decription: 开火
     */
    public void fire(FireStrategy f) {
        f.fire(this);
    }

    /**
     * Author: ZQ
     * Date: 2023/12/14
     * Decription: 坦克死亡
     */
    public void die() {
        this.living = false;
        int eX = this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        tf.blowUpList.add(new Explode(eX, eY, tf));
    }


    public void modetail(int speed) {
        switch (this.dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case DR:
                y += speed;
                x += speed;
                break;
            case DL:
                x -= speed;
                y += speed;
                break;
            case UR:
                y -= speed;
                x += speed;
                break;
            case UL:
                y -= speed;
                x -= speed;
                break;
        }
    }


    /**
     * Author: ZQ
     * Date: 2023/12/15
     * Decription: 敌方坦克随机方向
     */
    public void randomBADDir() {
        int i = random.nextInt(4);
        int j = random.nextInt(100);
        if (j <= i) {
            this.dir = Dir.values()[j];
//
//
//            switch (i) {
//                case 1:
//                    this.dir = Dir.DOWN;
//                    break;
//                case 2:
//                    this.dir = Dir.LEFT;
//                    break;
//                case 3:
//                    this.dir = Dir.RIGHT;
//                    break;
//                case 4:
//                    this.dir = Dir.UP;
//                    break;
//            }
        }
    }

    /**
     * Author: ZQ
     * Date: 2023/12/15
     * Decription: 返回坦克矩形
     */
    public Rectangle rect() {
        return rect;
    }
}
