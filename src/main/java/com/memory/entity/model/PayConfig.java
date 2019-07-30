package com.memory.entity.model;

/**
 * @ClassName PayConfig
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/28 17:02
 */
public class PayConfig {
    // 微信号,
    public static String APP_ID = "wxa6ff759682482521";

    // 商户号
    public static String MCH_ID = "1545807311";

    // 应用对应的凭证
    public static String APP_SECRET = "申请微信支付后可以获得的";

    // 商户密钥
    public static String API_KEY = "61bd639e350b484ab641c763e5eb4e2c";

    // 回调地址
    public static String NOTIFY_URL = "http://127.0.0.1/WeixinDemo02";

    //微信支付 回调地址
    //public static String NOTIFY_URL_H5 = "http://localhostUrl/resourceManager/xxxxxx?userid=";
    public static String NOTIFY_URL_XCX = "https://server.momjia.com/xhm/order/wxpay/pay";

    // ip
    public static String IP = "39.106.227.183";

    // 请求地址
    public static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    // 微信支付V2账单查询接口
    public static String ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";

    // 微信支付场景地址:WAP网站URL地址,可为项目地址
    public static String WAP_URL = "http://127.0.0.1/WeixinDemo02/";

    // 关闭订单
    public static String CLOSEORDER = "https://api.mch.weixin.qq.com/pay/closeorder";

}
