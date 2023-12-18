package com.zq.tankbattle.strategy.fire.impl;

import com.zq.tankbattle.Shell;
import com.zq.tankbattle.Tank;
import com.zq.tankbattle.TankFrame;
import com.zq.tankbattle.strategy.fire.FireStrategy;

/**
 * 作者:ZQ
 * 时间:2023/12/18 17:29
 * 开火策略模式的实现类：速射
 */
public class FlayFireStrategy implements FireStrategy {

    private FlayFireStrategy (){}

    private static final FlayFireStrategy INSTANCE= new FlayFireStrategy();

    public static  FlayFireStrategy getInstance(){
        return FlayFireStrategy.INSTANCE;
    }


    //TODO 没什么用
    @Override
    public void fire(Tank tank) {
        TankFrame tf = tank.getTf();
        int bx = tank.getX() + Tank.WIDTH / 2 - Shell.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Shell.HEIGHT / 2;
        for (int i = 0; i < 5; i++) {
            new Shell(bx, by, tank.getDir(), tank.getGroup(), tf);
        }
    }
}
