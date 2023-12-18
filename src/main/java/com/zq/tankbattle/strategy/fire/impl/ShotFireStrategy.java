package com.zq.tankbattle.strategy.fire.impl;

import com.zq.tankbattle.Dir;
import com.zq.tankbattle.Shell;
import com.zq.tankbattle.Tank;
import com.zq.tankbattle.TankFrame;
import com.zq.tankbattle.strategy.fire.FireStrategy;

/**
 * 作者:ZQ
 * 时间:2023/12/18 16:00
 * 开火策略模式的实现类：散射
 */
public class ShotFireStrategy implements FireStrategy {


    private ShotFireStrategy(){}
    private static final ShotFireStrategy INSTANCE=new ShotFireStrategy();


    public static ShotFireStrategy getInstance(){return ShotFireStrategy.INSTANCE;}

    @Override
    public void fire(Tank tank) {
        TankFrame tf = tank.getTf();
        int bx = tank.getX() + Tank.WIDTH / 2 - Shell.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Shell.HEIGHT / 2;
        Dir[] dirs=Dir.values();
        for (Dir dir : dirs) {
            new Shell(bx, by, dir, tank.getGroup(), tf);
        }
    }
}
