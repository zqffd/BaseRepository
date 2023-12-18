package com.zq.tankbattle.utils;



/**
 * 作者:ZQ
 * 时间:2023/12/14 14:45
 */
public enum  PathUtils {

    TANKPATH("static/images/tankImage/"),
    SHELLPATH("static/images/shellImage/"),
    VTF("static/images/vtf/"),
    MUSIC("static/music/");



    private String tankPath;


    PathUtils(String tankPath) {
        this.tankPath = tankPath;
    }


    public String path() {
        return tankPath;
    }

}
