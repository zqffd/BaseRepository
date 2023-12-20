package com.zq.tankbattle.strategy.fire.impl;

import com.zq.tankbattle.Dir;
import com.zq.tankbattle.Shell;
import com.zq.tankbattle.Tank;
import com.zq.tankbattle.TankFrame;
import com.zq.tankbattle.abstractfactory.GreenShell;
import com.zq.tankbattle.strategy.fire.FireStrategy;

/**
 * 作者:ZQ
 * 时间:2023/12/18 16:00
 * 开火策略模式的实现类：爆弹散射
 */
public class BombShotFireStrategy implements FireStrategy {


    private BombShotFireStrategy(){}
    private static final BombShotFireStrategy INSTANCE=new BombShotFireStrategy();


    public static BombShotFireStrategy getInstance(){return BombShotFireStrategy.INSTANCE;}


    //技能CD
    public static int skillCountDown = 5;

    @Override
    public void fire(Tank tank)
    {
        TankFrame tf = tank.getTf();
        int bx = tank.getX() + Tank.WIDTH / 2 - Shell.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Shell.HEIGHT / 2;
        Dir[] dirs=Dir.getShots();
        for (Dir dir : dirs) {
            new GreenShell(bx, by, dir, tank.getGroup(), tf);
        }
    }
}
