package com.zq.tankbattle.abstractfactory;

import com.zq.tankbattle.Tank;

import java.awt.*;

/**
 * 作者:ZQ
 * 时间:2023/12/19 17:33
 * 父类工厂
 */
public abstract class BaseShell {
    public abstract void paint(Graphics g);

    public abstract void collideWith(Tank tank);
//    public abstract void collideWith(Tank tank);


}
