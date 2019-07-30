package com.memory.common.video;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.memory.common.utils.Utils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;


/**
 * @author INS6+
 * @date 2019/7/29 10:09
 */

public class VideoUtils {
/*

    */
/**
     * 获取指定视频的帧并保存为图片至指定目录
     *
     * @param videofile 源视频文件路径
     * @param framefile 截取帧的图片存放路径
     * @throws Exception
     *//*

    public static void fetchFrame(String videofile, String framefile, String os)
            throws Exception {
        long start = System.currentTimeMillis();
        File targetFile = new File(framefile);
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        ff.start();
        int length = ff.getLengthInFrames();
        int i = 0;
        Frame f = null;
        while (i < length) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            if ((i > 5) && (f.image != null)) {
                break;
            }
            i++;
        }
        IplImage img = f.image;
        int oWidth = img.width();
        int oHeight = img.height();
        // 对截取的帧进行等比例缩放
        int width = 800;
        int height = (int) (((double) width / oWidth) * oHeight);
        System.out.println("height =" + height);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);


        //如果是苹果设备，图片顺时针旋转90°
        if (Utils.isNotNull(os) && os.equals("ios")) {
            //判断是否需要旋转
            if (height < width) {
                bi = RotateImage.Rotate(bi, 90);
            }
        }


        ImageIO.write(bi, "png", targetFile);
        //ff.flush();
        ff.stop();
        System.out.println(System.currentTimeMillis() - start);
    }
*/


    /**
     * 旋转图片为指定角度
     *
     * @param bufferedimage 目标图像
     * @param degree        旋转角度
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
                                            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }



    public static String fetchFrame(String videoFile,String frameFile,String os)  {
        File targetFile = new File(frameFile);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        try {
            //File file2 = new File("/data/photo/tempFile.mp4");
            File file2 = new File(videoFile);

                FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file2);
                ff.start();
                int ftp = ff.getLengthInFrames();
                int flag = 0;
                Frame frame = null;
                while (flag <= ftp) {
                    //获取帧
                    frame = ff.grabImage();
                    //过滤前3帧，避免出现全黑图片
                    if ((flag > 5) && (frame != null)) {
                        break;
                    }
                    flag++;
                }
                ImageIO.write(FrameToBufferedImage(frame,os), "png", targetFile);
                ff.close();
                ff.stop();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    private static RenderedImage FrameToBufferedImage(Frame frame,String os) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);

        //如果是苹果设备，图片顺时针旋转90°
        if (Utils.isNotNull(os) && os.equals("ios")) {

            bufferedImage = RotateImage.Rotate(bufferedImage, 90);

        }

        return bufferedImage;
    }


        public static void main(String[] args) {
        try {
           // VideoUtils.fetchFrame("G:/video/test6_ios.mp4", "G:/video/test6_ios.jpg");
          //  VideoUtils.fetchFrame("G:/video/test5.mp4", "G:/video/test55.jpg","");
            VideoUtils.fetchFrame("G:/video/test6_ios.mp4", "G:/video/test6_ios.png","ios");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }



}
