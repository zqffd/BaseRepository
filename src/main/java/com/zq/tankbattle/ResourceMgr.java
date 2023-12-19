package com.zq.tankbattle;

import com.zq.tankbattle.utils.PathUtils;
import com.zq.tankbattle.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 作者:ZQ
 * 时间:2023/12/14 14:54
 * 加载所有的文件资源,目前是饿汉式单例
 */
public class ResourceMgr {
    public static BufferedImage tankLeft, tankR, tankUP, tankD, tankUL, tankDL, tankUR, tankDR;
    public static BufferedImage meTankLeft, meTankR, meTankUP, meTankD, meTankUL, meTankDL, meTankUR, meTankDR;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD, bulletUL, bulletDL, bulletUR, bulletDR;
    public static BufferedImage bulletUL1, bulletUL2, bulletUL3, bulletUL4, bulletUR1, bulletUR2, bulletUR3, bulletUR4;
    public static BufferedImage meBulletL, meBulletR, meBulletU, meBulletD, meBulletUL, meBulletDL, meBulletUR, meBulletDR;
    public static BufferedImage[] blowUpList = new BufferedImage[16];
    public static BufferedImage hp100, hp80, hp60, hp40, hp20;

    private static final ResourceMgr RESOURCE_MGR = new ResourceMgr();

    public static ResourceMgr getInstance() {
        return ResourceMgr.RESOURCE_MGR;
    }

    private ResourceMgr() {
    }


    //class加载到内存的时候，直接加载所有文件
    static {
        try {
//            tankD = ImageIO.read(new Utils().getStaticFile(PathUtils.TANKPATH.path() + "坦克D1.png"));
//            tankLeft = ImageIO.read(new Utils().getStaticFile(PathUtils.TANKPATH.path() + "坦克L1.png"));
//            tankR = ImageIO.read(new Utils().getStaticFile(PathUtils.TANKPATH.path() + "坦克R1.png"));
            tankUP = ImageIO.read(new Utils().getStaticFile(PathUtils.TANKPATH.path() + "绿Tank1.png"));
            tankLeft = ImageUtil.rotateImage(tankUP, -90);
            tankR = ImageUtil.rotateImage(tankUP, 90);
            tankD = ImageUtil.rotateImage(tankUP, 180);
            tankUL = ImageUtil.rotateImage(tankUP, -45);
            tankUR = ImageUtil.rotateImage(tankUP, 45);
            tankDR = ImageUtil.rotateImage(tankD, -45);
            tankDL = ImageUtil.rotateImage(tankD, 45);


            meTankUP = ImageIO.read(new Utils().getStaticFile(PathUtils.TANKPATH.path() + "黑Tank1.png"));
            meTankLeft = ImageUtil.rotateImage(meTankUP, -90);
            meTankR = ImageUtil.rotateImage(meTankUP, 90);
            meTankD = ImageUtil.rotateImage(meTankUP, 180);
            meTankUL = ImageUtil.rotateImage(meTankUP, -45);
            meTankUR = ImageUtil.rotateImage(meTankUP, 45);
            meTankDR = ImageUtil.rotateImage(meTankD, -45);
            meTankDL = ImageUtil.rotateImage(meTankD, 45);


            bulletU = ImageIO.read(new Utils().getStaticFile(PathUtils.SHELLPATH.path() + "蓝bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletUL1 = ImageUtil.rotateImage(bulletU, -10);
            bulletUL2 = ImageUtil.rotateImage(bulletU, -20);
            bulletUL3 = ImageUtil.rotateImage(bulletU, -30);
            bulletUL4 = ImageUtil.rotateImage(bulletU, -40);
            bulletUL = ImageUtil.rotateImage(bulletU, -45);

            bulletUR1 = ImageUtil.rotateImage(bulletU, 10);
            bulletUR2 = ImageUtil.rotateImage(bulletU, 20);
            bulletUR3 = ImageUtil.rotateImage(bulletU, 30);
            bulletUR4 = ImageUtil.rotateImage(bulletU, 40);
            bulletUR = ImageUtil.rotateImage(bulletU, 45);


            bulletDR = ImageUtil.rotateImage(bulletD, -45);
            bulletDL = ImageUtil.rotateImage(bulletD, 45);


            meBulletU = ImageIO.read(new Utils().getStaticFile(PathUtils.SHELLPATH.path() + "绿bulletU.gif"));
            meBulletL = ImageUtil.rotateImage(meBulletU, -90);
            meBulletR = ImageUtil.rotateImage(meBulletU, 90);
            meBulletD = ImageUtil.rotateImage(meBulletU, 180);
            meBulletUL = ImageUtil.rotateImage(meBulletU, -45);
            meBulletUR = ImageUtil.rotateImage(meBulletU, 45);
            meBulletDR = ImageUtil.rotateImage(meBulletD, -45);
            meBulletDL = ImageUtil.rotateImage(meBulletD, 45);

            hp100 = ImageIO.read(new Utils().getStaticFile(PathUtils.HP.path() + "100%.png"));
            hp80 = ImageIO.read(new Utils().getStaticFile(PathUtils.HP.path() + "80%.png"));
            hp60 = ImageIO.read(new Utils().getStaticFile(PathUtils.HP.path() + "60%.png"));
            hp40 = ImageIO.read(new Utils().getStaticFile(PathUtils.HP.path() + "40%.png"));
            hp20 = ImageIO.read(new Utils().getStaticFile(PathUtils.HP.path() + "20%.png"));

            for (int i = 0; i < 16; i++) {
                File staticFile = new Utils().getStaticFile(PathUtils.VTF.path() + "e" + (i + 1) + ".gif");
                blowUpList[i] = ImageIO.read(staticFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
