package com.memory.app.controller;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.memory.app.repository.OrderMasterRepository;
import com.memory.app.service.OrderService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.entity.OrderMaster;
import com.memory.entity.model.PayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/28 20:02
 */
@RestController
@RequestMapping(value = "order")
public class OrderController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 添加订单信息
     * @param userId
     * @param openId
     * @param shrName
     * @param shrPhone
     * @param shrAddr
     * @param goodsList
     * @param remark
     * @return
     */
    @RequestMapping(value = "addOrder",method = RequestMethod.POST)
    public Message addOrder(@RequestParam String userId, @RequestParam String openId,@RequestParam String shrName, @RequestParam String shrPhone, @RequestParam String shrAddr,
                            @RequestParam String goodsList, @RequestParam String remark){
        try {
            msg = Message.success();
            Map<String,Object> returnMap = orderService.addOrder(userId,openId, shrName, shrPhone, shrAddr, goodsList, remark);
            msg.setRecode(0);
            msg.setResult(returnMap);
        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }


    @RequestMapping(value = "wxpay/pay")
    public Message pay(HttpServletRequest request, HttpServletResponse response){
        try {
            msg = Message.success();
//            String xmlStr  = getXmlString(request);
            System.out.println("request========"+request.toString());
            Map<String,String> map = WXPayUtil.xmlToMap(request.toString());
            //验证返回结果是否成功
            if (map.get("return_code").equals("SUCCESS")){
                String result_code = map.get("result_code");//业务结果
                String out_trade_no = map.get("out_trade_no");//订单号
                String transaction_id = map.get("transaction_id");//微信支付订单号
                System.out.println("====回调成功====");
                System.out.println("====result_code===="+result_code);
                if ("SUCCESS".equals(result_code)){
                    //查询订单支付状态
                    OrderMaster orderMaster = orderMasterRepository.findByOrderNo(out_trade_no);
                    if (orderMaster!=null){
                        if (orderMaster.getOrderStatus()!=1){
                            System.out.println("====if====");
                            String str = returnXML(map.get("return_code").toString());
                            response.getWriter().write(str);
                            response.getWriter().flush();
                        }else{
                            System.out.println("====else====");
                           Map<String,Object> returnMap =  orderService.updOrder(out_trade_no,transaction_id);
                           //判断订单状态
                            if (returnMap.get("code").equals("SUCCESS")){
                                String str = returnXML(map.get("return_code").toString());
                                response.getWriter().write(str);
                                response.getWriter().flush();
                                msg.setRecode(0);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 根据Id查询订单详情
     * @param orderMasterId
     * @return
     */
    @RequestMapping(value = "getOrderById",method = RequestMethod.POST)
    public Message getOrderById(@RequestParam String orderMasterId){
        try {
            msg = Message.success();
            Map<String,Object> returnMap = orderService.getOrderById(orderMasterId);
            returnMap.put("fileUrl",this.getFileUrl());
            msg.setRecode(0);
            msg.setResult(returnMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * IO解析获取微信的数据
     * @param request
     * @return
     */
    public static String getXmlString(HttpServletRequest request) {
        BufferedReader reader = null;
        String line = "";
        String xmlString = null;
        try {
            reader = request.getReader();
            StringBuffer inputString = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            xmlString = inputString.toString();
        } catch (Exception e) {

        }

        return xmlString;
    }

    /**
     * 返回给微信服务端的xml
     * @param return_code
     * @return
     */
    public static String returnXML(String return_code) {
        return "<xml><return_code><![CDATA["+ return_code+ "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }


}

