package com.memory.app.service.impl;


import com.memory.app.service.GetOpenIdService;
import com.memory.entity.model.PayConfig;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GetOpenIdServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/30 13:26
 */
@Service("getOpenIdService")
public class GetOpenIdServiceImpl implements GetOpenIdService {

    @Override
    public Map<String,Object> getOpenId(String code){
        Map<String,Object> returnMap = new HashMap<>();
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            StringBuilder urlPath = new StringBuilder(PayConfig.GETOPENID);
            urlPath.append(String.format("?appid=%s",PayConfig.APP_ID));
            urlPath.append(String.format("&secret=%s",PayConfig.SECRET));
            urlPath.append(String.format("&js_code=%s",code));
            urlPath.append(String.format("&grant_type=%s", "authorization_code")); // 固定值

            URL url = new URL(urlPath.toString());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String openId = jsonObject.getString("openid");
            System.out.println(openId);
            returnMap.put("openId",openId);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return returnMap;
    }

}
