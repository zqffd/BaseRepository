package com.zq.tankbattle.abstractfactory;

import com.zq.tankbattle.Dir;
import com.zq.tankbattle.Group;
import com.zq.tankbattle.Tank;
import com.zq.tankbattle.TankFrame;

/**
 * 作者:ZQ
 * 时间:2023/12/19 17:05
 * 坦克工厂，生产坦克
 */
public class DefaultFactory extends GameFactory{




    @Override
    BaseTank createTankFactory(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new BadTank(x,y,dir,group,tf);
    }


    @Override
    BaseShell createShellFactory(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new GreenShell(x,y,dir,group,tf);
    }

    @Override
    DefaultFactory createExplodeFactory(int x, int y, Dir dir, Group group, TankFrame tf) {
        return null;
    }


}
