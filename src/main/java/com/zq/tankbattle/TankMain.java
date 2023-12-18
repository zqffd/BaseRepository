package com.zq.tankbattle;


import com.zq.tankbattle.common.TankCommon;


/**
 * 作者:ZQ
 * 时间:2023/12/13 15:07
 */
public class TankMain {
    public static void main(String[] args) throws InterruptedException {

        //每隔50毫秒刷新窗口
        TankFrame t = new TankFrame();

        //初始化地方坦克
        for (int j = 0; j < TankCommon.getIntValue("badTankNum"); j++) {
            t.tankList.add(new Tank(50 + j * 80, 200, Dir.DOWN, Group.BAD, t));
        }

        //每25毫秒刷新一次，延迟25ms
        while (true) {
            Thread.sleep(30);
            t.repaint();
        }
    }



}
