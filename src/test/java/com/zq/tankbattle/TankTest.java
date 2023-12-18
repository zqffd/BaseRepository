package com.zq.tankbattle;

import com.zq.tankbattle.common.TankCommon;
import com.zq.tankbattle.utils.PathUtils;
import com.zq.tankbattle.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 作者:ZQ
 * 时间:2023/12/14 11:30
 */
@SpringBootTest
public class TankTest {

    @Autowired
    private TankCommon common;




    @Test
    public void imageTest() throws Exception {
        Utils utils = new Utils();

        File file = utils.getStaticFile(PathUtils.TANKPATH.path()+"坦克2.gif");
        BufferedImage tank2 = ImageIO.read(file);


        System.out.println("结果："+file.exists());





    }

    public static void main(String[] args) {
        Object badTankSize = TankCommon.mainConfig("badTankNum");
        System.out.println("结果："+badTankSize);

    }

    @Test
    public void imageTest2(){
        System.out.println("数量："+ TankCommon.getBadTankNum());





    }
}
