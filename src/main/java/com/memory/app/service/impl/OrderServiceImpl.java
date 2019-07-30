package com.memory.app.service.impl;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.memory.app.repository.DetailsRepository;
import com.memory.app.repository.OrderMasterRepository;
import com.memory.app.repository.OrderSlaveRepository;
import com.memory.app.repository.UserGroupRepository;
import com.memory.app.service.OrderService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.*;
import com.memory.entity.model.HttpClient;
import com.memory.entity.model.PayConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName OrderServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/27 16:05
 */
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderSlaveRepository orderSlaveRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    /**
     * 生成未支付订单
     * @param userId
     * @param openId
     * @param shrName
     * @param shrPhone
     * @param shrAddr
     * @param goodsList
     * @param remark
     * @return
     */
    @Transactional
    @Override
    public Map<String,Object> addOrder(String userId, String openId, String shrName, String shrPhone, String shrAddr, String goodsList, String remark){
        Map<String,Object> returnMap = new HashMap<>();
        Map<String,String> payMap = new HashMap<>();
        try {
            User user = (User) daoUtils.getById("User",userId);
            if (user!=null){
                JSONArray array = JSONArray.fromObject(goodsList);
                //订单商品list
                List<OrderSlave> orderSlaveList = new ArrayList<>();
                String orderMasterId = Utils.generateUUID();
                Date date = new Date();
                String out_trade_no = Utils.idTimer.format(date)+this.getFourRandom();
                double orderMoney = 0.0;
                for (int i=0;i<array.size();i++){
                    JSONObject obj = array.getJSONObject(i);
                    Goods goods = (Goods) daoUtils.getById("Goods",obj.getString("goodsId"));
                    if (goods!=null){
                        int count = obj.getInt("goodsNumber");
                        double orderSlaveSum = goods.getGoodsCurrentPrice()*count;
                        String[] imgUrls = goods.getGoodsImg().split(",");
                        //生成订单子数据
                        OrderSlave orderSlave = new OrderSlave(Utils.generateUUID(),orderMasterId,count,
                                orderSlaveSum,goods.getId(),goods.getGoodsName(),imgUrls[0],goods.getGoodsCurrentPrice(),date,1);
                        orderSlaveList.add(orderSlave);
                        //计算订单总额
                        orderMoney+=orderSlaveSum;
                    }
                }
                int total_fee = (int) (orderMoney * 100);


                payMap = this.pay(String.valueOf(total_fee),out_trade_no,"测试-测试商品",openId);
                String msg = payMap.get("msg");
                if ("success".equals(msg)){
                    String nonce_str = payMap.get("returnNonceStr");
                    String sign = payMap.get("returnSign");
                    String prepay_id = payMap.get("returnPrepayId");
                    //生成订单主数据
                    OrderMaster orderMaster = new OrderMaster(orderMasterId,userId,out_trade_no,
                            shrName,shrPhone,shrAddr,orderMoney,1,remark,nonce_str,sign,prepay_id,"",date,date,1);
                    //保存数据
                    if (this.saveOrder(orderMaster,orderSlaveList)){
                        returnMap.put("orderMasterId",orderMaster.getId());
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     *  微信统一下单
     * @param total_fee
     * @param out_trade_no
     * @param goodsName
     * @param openid
     * @return
     */
    public Map<String,String> pay(String total_fee, String out_trade_no, String goodsName,String openid){
        // 封装需要的信息
        Map<String, String> payMap = new HashMap<String, String>();
        try {
            // 1. 拼接下单地址参数
            Map<String,String> param = new HashMap<String,String>();
            param.put("appid", PayConfig.APP_ID); // 小程序ID
            param.put("mch_id", PayConfig.MCH_ID); // 商户号ID
            param.put("nonce_str", WXPayUtil.generateNonceStr());  // 随机字符串
            param.put("body", goodsName) ;// 商品描述
            param.put("out_trade_no", out_trade_no); // 商户订单号
            param.put("total_fee", total_fee);//金额（分）
            param.put("spbill_create_ip", PayConfig.IP); // 商户终端ip
            param.put("notify_url", PayConfig.NOTIFY_URL_XCX); // 微信异步通知回调地址
            param.put("trade_type", "JSAPI"); // 小程序支付类型
            param.put("openid",openid);//小程序支付时openId必传

            // 生成签名,官方默认MD5+商户秘钥+参数信息
            String sign = WXPayUtil.generateSignature(param, PayConfig.API_KEY,WXPayConstants.SignType.MD5);
            param.put("sign", sign);

            // 将所有参数转换为xml形式
            String xmlParam = WXPayUtil.mapToXml(param);

            // 2. 发送请求
            //String xmlStr = HttpRequest.sendPost(unifiedorder_url, xml);//发送post请求"统一下单接口"
            HttpClient httpClient = new HttpClient(PayConfig.UFDODER_URL);
            httpClient.setHttps(true);// https协议
            httpClient.setXmlParam(xmlParam);
            httpClient.post();

            // 获取结果
            String xmlResult = httpClient.getContent();
            //以下内容是返回前端页面的json数据
            String returnNonceStr = "";
            String returnSign = "";
            String returnPrepayId = "";
            if (xmlResult.indexOf("SUCCESS") != -1) {  // 只要执行了下发接口,都会包含SUCCESS的
// { nonce_str=FZX1bv0L9lZt7Yw8,appid=wxa6ff759682482521, sign=FD352AED6C147ADCC9D88801F4627F80, trade_type=JSAPI, return_msg=OK, result_code=SUCCESS, mch_id=1545807311, return_code=SUCCESS, prepay_id=wx28210937606104045639dae81973538300 }
                Map<String, String> map = WXPayUtil.xmlToMap(xmlResult);
                returnNonceStr = map.get("nonce_str");
                returnSign = map.get("sign");
                returnPrepayId = map.get("prepay_id");
                payMap.put("returnNonceStr",returnNonceStr);
                payMap.put("returnSign",returnSign);
                payMap.put("returnPrepayId",returnPrepayId);
                payMap.put("msg", "success");
                System.out.println(map);
            } else {
                System.out.println("统一支付接口获取预支付订单出错");
                payMap.put("msg", "支付失败");
                return payMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("统一支付接口获取预支付订单出错");
            payMap.put("msg", "系统支付错误");
            return payMap;
        }

        return payMap;
    }

    //修改订单状态 生成积分记录
    @Override
    public Map<String,Object> updOrder(String orderNo, String transactionId){
        Map<String,Object> returnMap = new HashMap<>();
        try {
            List<Details> detailsList = new ArrayList<>();
            OrderMaster orderMaster = orderMasterRepository.findByOrderNo(orderNo);
            String orderMasterId = orderMaster.getId();
            Date date = new Date();
            if (orderMaster!=null){
                double orderSum = orderMaster.getOrderMoney();
                //修改订单状态
                orderMaster.setOrderStatus(2);
                orderMaster.setWxTransactionId(transactionId);
                //生成积分记录
                UserGroup userGroup = userGroupRepository.findByUserId(orderMaster.getUserId());
                if (userGroup!=null){
                    String groupIdOne = userGroup.getParentIdOne();
                    String gruopIdTwo = userGroup.getParentIdTwo();
                    //存在一级代理
                    if (!"".equals(groupIdOne)){
                        UserGroup userGroup1 = userGroupRepository.findByUserId(groupIdOne);
                        User user1 = (User) daoUtils.getById("User",groupIdOne);
                        if (userGroup1!=null){
                            double sumOne = orderSum*userGroup1.getRoyalty();
                            Details detailsOne = new Details(Utils.generateUUID(),"下单",orderMasterId,sumOne,groupIdOne,user1.getUserIntegral()+sumOne,0,date,"");
                            detailsList.add(detailsOne);
                        }
                    }
                    //存在二级代理
                    if (!"".equals(gruopIdTwo)){
                        UserGroup userGroup2 = userGroupRepository.findByUserId(gruopIdTwo);
                        User user2 = (User) daoUtils.getById("User",gruopIdTwo);
                        if (userGroup2!=null){
                            double sumTwo = orderSum*userGroup2.getRoyalty();
                            Details detailsTwo = new Details(Utils.generateUUID(),"下单",orderMasterId,sumTwo,gruopIdTwo,user2.getUserIntegral()+sumTwo,0,date,"");
                            detailsList.add(detailsTwo);
                        }
                    }
                }
                if (updOrder(orderMaster,detailsList)){
                    returnMap.put("code","SUCCESS");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  returnMap;
    }

    @Override
    public Map<String,Object> getOrderById(String orderMasterId){
        Map<String,Object> returnMap = new HashMap<>();
        try {
            OrderMaster orderMaster = (OrderMaster) daoUtils.getById("OrderMaster",orderMasterId);
            if (orderMaster!=null){
                StringBuffer stringBuffer = new StringBuffer("FROM OrderSlave where orderMasterId = '"+orderMaster.getId()+"' ");
                List<OrderSlave> list = daoUtils.findByHQL(stringBuffer.toString(),null,null);
                //如果订单未支付，再次构建签名数据
                if (orderMaster.getOrderStatus()==1){
                    Map<String,String> payMap = new HashMap<>();
                    long time = System.currentTimeMillis()/1000;
                    payMap.put("appId",PayConfig.APP_ID);//小程序Id
                    payMap.put("timeStamp", String.valueOf(time));//时间戳
                    payMap.put("nonceStr",orderMaster.getWxNonceStr());//调用微信统一支付接口生成的随机字符串【否则签名错误】
                    payMap.put("package","prepay_id="+orderMaster.getWxPrepayId());
                    payMap.put("signType","MD5");
                    String paySign = WXPayUtil.generateSignature(payMap,PayConfig.API_KEY, WXPayConstants.SignType.MD5);
                    returnMap.put("paySign",paySign);
                    returnMap.put("timeStamp",String.valueOf(time));


                    System.out.println(paySign);
                    System.out.println(time);
                }
                returnMap.put("orderMaster",orderMaster);
                returnMap.put("orderSlaveList",list);
            }

        }catch (Exception e){

        }

        return returnMap;
    }

    @Transactional
    public  boolean saveOrder(OrderMaster orderMaster,List<OrderSlave> orderSlaves){
        boolean flag = false;
        try {
            orderMasterRepository.save(orderMaster);
            orderSlaveRepository.saveAll(orderSlaves);
            flag = true;
        }catch (Exception e){
            flag = false;
            throw e;
        }
        return flag;
    }

    @Transactional
    public  boolean updOrder(OrderMaster orderMaster,List<Details> detailsList){
        boolean flag = false;
        try {
            orderMasterRepository.save(orderMaster);
            detailsRepository.saveAll(detailsList);
            System.out.println("======");
            flag = true;
        }catch (Exception e){
            flag = false;
            throw e;
        }
        return flag;
    }

    public String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom ;
        }
        return fourRandom;
    }



    public static void main(String[] args) {



    }





}
