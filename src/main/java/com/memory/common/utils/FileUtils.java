package com.memory.common.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * file相关 的util方法
 * @author INS6+
 * @date 2019/5/10 11:12
 */
@Component
public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private static FileUtils fileUtils;



    @PostConstruct
    public void init(){
        fileUtils = this;
    }


    public static String upload(MultipartFile file, String fileUploadedPath,String fileName) {
        try {

            log.info("上传的文件名为：" + fileName);
            File dest = new File(fileUploadedPath + "/" + fileName);
            System.out.println("上传路径==" +fileUploadedPath);
            System.out.println("上傳路徑檢測= " +dest.getParentFile() );
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String upload(MultipartFile file, String fileUploadedPath,String customPath,String fileName) {
        String returnFileName = "";
        try {
            File dest = new File(fileUploadedPath + "/" + customPath + "/" + fileName);
            returnFileName =  customPath + "/" + fileName;
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }



 /*   public static String upload(MultipartFile file, String fileUploadedPath,String fileName,String UUID) {
        String returnFileName = "";
        try {

            log.info("上传的文件名为：" + fileName);
            fileUploadedPath = fileUploadedPath +"/" +UUID;
            File dest = new File(fileUploadedPath + "/" + fileName);
            System.out.println("上传路径==" +fileUploadedPath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            returnFileName = UUID + "/" + fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }
*/




    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static boolean  downLoadFromUrl(String urlStr,String fileName,String savePath) {
        boolean flag =false;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);

            System.out.println("文件保存路径====" + savePath);
            System.out.println("文件名称=====" + fileName);

            File file = new File(savePath+"/"+fileName);
            System.out.println("文件判斷路徑 == " + file.getParentFile());

            if(!file.getParentFile().exists()){
                //注意.mkdirs() 和 .mkdir() 的区别,后者只会创建一个层级的文件夹
                file.getParentFile().mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);

            if(fos!=null){
                fos.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            flag = true;
            log.info("Url:【"+urlStr+"】下载成功.存储文件【"+ savePath+"/"+fileName+"】");
            System.out.println("Url:【"+urlStr+"】下载成功.存储文件【"+ savePath+"/"+fileName+"】");
        }catch (Exception e){
            e.printStackTrace();
            log.error("Url:【"+urlStr+"】下载失败",e.getMessage());
        }
        return flag;
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    /**
     * 获取音频文件名称
     * @param prefix
     * @param audioFile
     * @return
     */
    public static String getAudioFileName(String prefix, MultipartFile audioFile){
        String fileNameReal =  audioFile.getOriginalFilename();
        String suffix = fileNameReal.substring(fileNameReal.lastIndexOf("."));
        String dayStr = DateUtils.getDate("yyyyMMdd");
        String hoursStr = DateUtils.getDate("HHmmss");
        return   prefix + "_" + dayStr + "_" + hoursStr + suffix;
    }


    /**
     * 获取音频文件名称
     * @param prefix
     * @param realFileName
     * @return
     */
    public static String getAudioFileName(String prefix, String realFileName){
        String suffix = realFileName.substring(realFileName.lastIndexOf("."));
        String dayStr = DateUtils.getDate("yyyyMMdd");
        String hoursStr = DateUtils.getDate("HHmmss");
        return   prefix + "_" + dayStr + "_" + hoursStr + suffix;
    }


    /**
     * 获取图片文件名称
     * @param prefix
     * @return
     */
    public static String getImgFileName(String prefix){
        //String fileNameReal =  imgFile.getOriginalFilename();
        //String suffix = fileNameReal.substring(fileNameReal.lastIndexOf("."));
        String suffix = ".png";
        String dayStr = DateUtils.getDate("yyyyMMdd");
        String hoursStr = DateUtils.getDate("HHmmss");
        return   prefix + "_" + dayStr + "_" + hoursStr + suffix;
    }







    /**
     * 扫描指定路径下的文件
     * @param path
     * @return
     */
    public static ArrayList<File> getFiles(String path){
        ArrayList<File> fileArrayList = new ArrayList<File>();
        try{
            File file = new File(path);
            if(file.isDirectory()){
                File [] files = file.listFiles();
                for (File fileIndex : files) {
                    //如果这个文件是个目录则递归遍历
                    if(fileIndex.isDirectory()){
                        getFiles(fileIndex.getPath());
                    }else {
                        fileArrayList.add(fileIndex);
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileArrayList;
    }


}
