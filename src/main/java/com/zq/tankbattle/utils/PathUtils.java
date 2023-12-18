package com.zq.tankbattle.utils;



/**
 * 作者:ZQ
 * 时间:2023/12/14 14:45
 * 初始化个个静态资源路径
 */
public enum  PathUtils {

    TANKPATH("static/images/tankImage/"),
    SHELLPATH("static/images/shellImage/"),
    VTF("static/images/vtf/"),
    HP("static/images/tankImage/hp/"),
    MUSIC("static/music/");



    private String tankPath;


    PathUtils(String tankPath) {
        this.tankPath = tankPath;
    }


    public String path() {
        return tankPath;
    }

}
