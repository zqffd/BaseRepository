package com.zq.tankbattle.abstractfactory;

import com.zq.tankbattle.Dir;
import com.zq.tankbattle.Group;
import com.zq.tankbattle.TankFrame;

/**
 * 作者:ZQ
 * 时间:2023/12/19 17:04
 * 游戏工厂，管理产品一族
 */
public abstract class GameFactory {


    //坦克工厂
    abstract BaseTank createTankFactory(int x, int y, Dir dir, Group group, TankFrame tf);

    //子弹工厂
    abstract BaseShell createShellFactory(int x, int y, Dir dir, Group group, TankFrame tf);

    //爆炸特效工厂
    abstract DefaultFactory createExplodeFactory(int x, int y, Dir dir, Group group, TankFrame tf);

}
