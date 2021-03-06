package com.memory.app.service.impl;

import com.memory.app.service.GoodsService;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Goods;
import com.memory.entity.model.GoodsOrder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @ClassName GoodsServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/10 18:00
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private DaoUtils daoUtils;

    @Override
    public Map<String,Object> findGoodsList(Integer start, Integer limit, String goodsName){
        Map<String,Object> returnMap = new HashMap<>();

        StringBuffer sbGoodsList = new StringBuffer(" FROM Goods WHERE goodsIsShelf=1 ");
        StringBuffer sbGoodsCount = new StringBuffer( " select count(*) from Goods where goodsIsShelf=1 ");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        Map<String,Object> map = new HashMap<>();
        if (!"".equals(goodsName)){
            sbGoodsList.append(" AND goodsName LIKE :goodsName ");
            sbGoodsCount.append( " AND goodsName Like :goodsName");
            map.put("goodsName","%"+goodsName+"%");
        }
        sbGoodsList.append(" ORDER BY goodsUpdateTime DESC ");

        List<Goods> goodsList = daoUtils.findByHQL(sbGoodsList.toString(),map,page);
        Integer goodsCount = daoUtils.getTotalByHQL(sbGoodsCount.toString(),map);

        returnMap.put("goodsList",goodsList);
        returnMap.put("goodsCount",goodsCount);
        return  returnMap;
    }

    @Override
    public Map<String,Object> getGoodsList(String goodsList){
        Map<String,Object> returnMap = new HashMap<>();
        try {
            Double sum = 0.0;
            JSONArray array = JSONArray.fromObject(goodsList);
            List<GoodsOrder> goodsOrderList = new ArrayList<GoodsOrder>();
         for (int i =0; i< array.size(); i++){
             JSONObject obj = array.getJSONObject(i);
             Goods goods = (Goods) daoUtils.getById("Goods",obj.getString("goodsId"));
             if (goods!=null){
                int count = obj.getInt("goodsNumber");
                double s_sum = goods.getGoodsCurrentPrice()*count;
                String[] imgUrls = goods.getGoodsImg().split(",");
                GoodsOrder goodsOrder = new GoodsOrder(goods.getId(),imgUrls[0],goods.getGoodsName(),count,goods.getGoodsCurrentPrice(),s_sum);
                goodsOrderList.add(goodsOrder);
                sum+=s_sum;
             }
         }
         returnMap.put("goodsOrderList",goodsOrderList);
         returnMap.put("sum",sum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  returnMap;
    }


}
