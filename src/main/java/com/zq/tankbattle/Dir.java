package com.zq.tankbattle;

/**
 * 作者:ZQ
 * 时间:2023/12/13 17:08
 * 定义方向的枚举类
 */
public enum Dir {
    LEFT, UP, RIGHT, DOWN ,STOP,
    DR, DL, UR, UL ,UL1 , UL2, UL3 , UL4, UR1, UR2, UR3, UR4;


    /**
      Author: ZQ
      Date: 2023/12/19
      Decription: 上右上左密集方向
    */
    public  static Dir[] getShots(){
        Dir[] dirs = {Dir.UL1,Dir.UL2,Dir.UL3,Dir.UL4,Dir.UR1,Dir.UR2,Dir.UR3,Dir.UR4};
        return dirs;
    }

    /**
      Author: ZQ
      Date: 2023/12/19
      Decription: 移动的八方向
    */
    public  static Dir[] getAlls(){
        Dir[] dirs = {Dir.LEFT,Dir.UP,Dir.RIGHT,Dir.DOWN,Dir.STOP,Dir.DR,Dir.DL,Dir.UR,Dir.UL};
        return dirs;
    }

}
