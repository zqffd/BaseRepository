package com.zq.tankbattle.strategy.fire;

import com.zq.tankbattle.Tank;

/**
 * 作者:ZQ
 * 时间:2023/12/18 15:32
 * 开火的策略模式入口
 */
public interface FireStrategy {

    //开火
    void fire(Tank tank);


}
