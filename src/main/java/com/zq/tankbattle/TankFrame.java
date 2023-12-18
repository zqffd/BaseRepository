package com.zq.tankbattle;

import com.zq.tankbattle.strategy.fire.impl.DefaultFireStrategy;
import com.zq.tankbattle.strategy.fire.impl.FlayFireStrategy;
import com.zq.tankbattle.strategy.fire.impl.ShotFireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者:ZQ
 * 时间:2023/12/13 15:28
 * 游戏运行基本实体类
 */
//继承Frame后，自己就是一个窗口对象
public class TankFrame extends Frame {

    Tank tank = new Tank(200, 400, Dir.STOP, Group.GOOD, this);
    //窗口的宽度
    static final int GAME_WIDIH = 1024;
    //窗口的高度
    static final int GAME_HEIGHT = 800;

    KeyEvent event = null;

    //子弹容器
    List<Shell> shellList = new ArrayList<>();

    //敌方坦克
    List<Tank> tankList = new ArrayList<>();

    //炸弹特效集合
    List<Explode> blowUpList = new ArrayList<>();

    //杀敌数
    List<Explode> killsList = new ArrayList<>();

    int x1 = 200;
    int y1 = 200;
    int a = 0;

    //无参在被执行new后会执行初始化
    public TankFrame() throws HeadlessException {
        //设置窗口大小
        setSize(GAME_WIDIH, GAME_HEIGHT);
        //设置窗口标题
        setTitle("这是一个新窗口");
        //将窗口设置为可见
        setVisible(true);


        //添加监听器
        addWindowListener(new WindowAdapter() {
            @Override
            //监听窗口上的X
            public void windowClosing(WindowEvent e) {
                //点击后触发系统关闭，也就是窗口关闭
                System.exit(0);
            }
        });

        //监听键盘
        addKeyListener(new MyKeyListener());

    }

    /**
     * Author: ZQ
     * Date: 2023/12/14
     * Decription: 问题：用双缓冲解决窗口闪烁问题(可以理解为当刷新过快，每次刷新完，画的东西才画完一点点，刷新窗口太快了，但是计算跟不上，就导致看着是有延迟，这时就发生闪烁
     * 解决：在内存中定义一个跟这个窗口一样大小的空间，画的时候先画到内存中定义的这个窗口上，等画完了，一次性拿到外面使用的这个窗口上，闪烁就消除了)
     * 正常情况，这段会封装在游戏引擎里面，已经封装好了；
     * 内存的内容复制到显存，显存的内容才是展示在屏幕上的
     */
    //定义一张图片，定义在内存里面，这个图片就是内存中的窗口，还没有画出来，只是定义出来
    Image offScreenImage = null;

    //窗口刷新的时候会先调用update，再调用paint，所以直接截获update来处理
    @Override
    public void update(Graphics g) {

        if (offScreenImage == null) {
            //定义这个图片的宽高
            offScreenImage = this.createImage(GAME_WIDIH, GAME_HEIGHT);
        }

        //拿到图片上的画笔
        Graphics graphics = offScreenImage.getGraphics();
        //设置背景
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDIH, GAME_HEIGHT);
        graphics.setColor(color);
        //调用屏幕上的paint方法，把内存里面画的窗口直接画到屏幕上
        paint(graphics);
        //把要画的东西一次性画到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);
    }


    /**
     * Author: ZQ
     * Date: 2023/12/13
     * Decription: paint这个方法作用就是窗口发生任何重新绘画的时候就会自动执行（窗口变大变小、窗口焦点消失又回来等等）
     * g：这个对象就是一个画笔，可以在窗口内随意画
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.GREEN);
        g.drawString("子弹数量：" + shellList.size(), 10, 60);
        g.drawString("坦克数量：" + tankList.size(), 10, 80);
        g.drawString("爆炸数量" + blowUpList.size(), 10, 100);
        g.setColor(color);


        tank.paint(g);
        //这里用原始循环，不能用fore，用fore会加锁，锁住list的数量，数量如果发生改变会报错
        for (int i = 0; i < shellList.size(); i++) {
            shellList.get(i).paint(g);
        }


        //敌方坦克持续生成
        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }


        //爆炸特效
        for (int i = 0; i < blowUpList.size(); i++) {
            blowUpList.get(i).paint(g);
        }

        //子弹打中坦克
        for (int i = 0; i < shellList.size(); i++) {
            for (int j = 0; j < tankList.size(); j++) {

                shellList.get(i).collideWith(tankList.get(j));
            }
        }

        //可以让方块自动绕圈执行
//          circle();
    }

    //监听键盘的内部类
    class MyKeyListener extends KeyAdapter {

        boolean W = false;
        boolean A = false;
        boolean S = false;
        boolean D = false;

        @Override
        public void keyTyped(KeyEvent e) {
            super.keyTyped(e);
        }


        //当一个键被按下的时候调用,下面函数是可以用WASD操作方块前进
        @Override
        public void keyPressed(KeyEvent e) {
            //获取按下的指定键
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    W = true;
                    break;
                case KeyEvent.VK_A:
                    A = true;
                    break;
                case KeyEvent.VK_S:
                    S = true;
                    break;
                case KeyEvent.VK_D:
                    D = true;
                    break;
                case KeyEvent.VK_SHIFT:
                    tank.goodSpeed=6;
                    break;
//                //空格
//                case KeyEvent.VK_SPACE:
//                    tank.fire();
//                    break;
                default:
                    break;
            }
//            event=e;

            setMainTankDir();
            //等于调用了paint方法
//            repaint();
        }


        //当一个键被抬起来的时候调用
        @Override
        public void keyReleased(KeyEvent e) {
            //获取按下的指定键
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    W = false;
                    break;
                case KeyEvent.VK_A:
                    A = false;
                    break;
                case KeyEvent.VK_S:
                    S = false;
                    break;
                case KeyEvent.VK_D:
                    D = false;
                    break;
                //空格单发
                case KeyEvent.VK_SPACE:
                    tank.fire(DefaultFireStrategy.getInstance());
                    break;
                //R 散射
                case KeyEvent.VK_R:
                    tank.fire(ShotFireStrategy.getInstance());
                    break;
                case KeyEvent.VK_Q:
                    tank.fire(FlayFireStrategy.getInstance());
                    break;
                //shift 加速
                case KeyEvent.VK_SHIFT:
                    tank.goodSpeed=3;
                    break;
                default:
                    break;
            }
            setMainTankDir();

            //等于调用了paint方法
//            repaint();
        }

        //可以处理持续按下持续触发的情况
        private void setMainTankDir() {
            tank.setMoving(true);

            //当所有按键都没按下的时候，停止移动
            if (!A && !S && !W && !D) {
                tank.setMoving(false);
                //目前支持八个方向
            } else {
                if (A && !S && !W && !D) tank.setDir(Dir.LEFT);
                if (W && !S && !A && !D) tank.setDir(Dir.UP);
                if (S && !A && !W && !D) tank.setDir(Dir.DOWN);
                if (D && !A && !W && !S) tank.setDir(Dir.RIGHT);
                if (D && S && !W && !A) tank.setDir(Dir.DR);
                if (A && S && !W && !D) tank.setDir(Dir.DL);
                if (W && A && !S && !D) tank.setDir(Dir.UL);
                if (W && D && !S && !A) tank.setDir(Dir.UR);
            }
        }
    }


    public void circle() {

        if (a == 0 && x1 < 400) {
            x1 += 10;
        }

        if (x1 == 400) {
            a = 1;
            y1 += 10;
        }

        if (y1 == 400) {
            x1 -= 10;
        }

        if (x1 == 200) {
            y1 -= 10;
        }

        if (x1 == 200 && y1 == 200) {
            a = 0;
        }
    }


}
