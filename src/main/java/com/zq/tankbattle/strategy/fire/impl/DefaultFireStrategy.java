package com.zq.tankbattle.strategy.fire.impl;

import com.zq.tankbattle.Shell;
import com.zq.tankbattle.Tank;
import com.zq.tankbattle.TankFrame;
import com.zq.tankbattle.strategy.fire.FireStrategy;

/**
 * 作者:ZQ
 * 时间:2023/12/18 15:53
 * 开火策略模式的实现类：普通子弹
 */
public class DefaultFireStrategy implements FireStrategy {



    private DefaultFireStrategy(){}

    private static final DefaultFireStrategy INSTANCE = new DefaultFireStrategy();


    public static DefaultFireStrategy getInstance() {
        return DefaultFireStrategy.INSTANCE;
    }


    @Override
    public void fire(Tank tank) {
        TankFrame tf = tank.getTf();
        int bx = tank.getX() + Tank.WIDTH / 2 - Shell.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Shell.HEIGHT / 2;
        new Shell(bx, by, tank.getDir(), tank.getGroup(), tf);
    }




}
