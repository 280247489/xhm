package com.memory.cms.service.impl;

import com.memory.cms.repository.CmsGoodsRepository;
import com.memory.cms.service.CmsGoodsService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * @ClassName CmsGoodsServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/25 18:29
 */
@Service("cmsGoodsService")
public class CmsGoodsServiceImpl implements CmsGoodsService {

    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private CmsGoodsRepository cmsGoodsRepository;

    //添加或修改
    @Transactional
    @Override
    public Goods addOrUpdate(Goods goods){
        daoUtils.save(goods);
        return goods;
    }
    @Transactional
    @Override
    public Goods update(Goods goods){
        cmsGoodsRepository.save(goods);
        return goods;
    }

   //根据产品名、Id验证是否存在
    @Override
    public Goods checkGoodsName(String goodsName, String id){
        StringBuffer sb = new StringBuffer(" from Goods where goodsName=:goodsName ");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsName", goodsName);
        if(!"".equals(id)){
            sb.append(" and id!=:id ");
            map.put("id", id);
        }
        Goods goods = (Goods) daoUtils.findObjectHQL(sb.toString(), map);
        return goods;
    }

    //修改上架下架状态
    @Transactional
    @Override
    public Goods updShelf(Goods goods){
        if (goods.getGoodsIsShelf()==0){
            goods.setGoodsIsShelf(1);
        }else{
            goods.setGoodsIsShelf(0);
        }
        daoUtils.save(goods);
        return goods;
    }


    @Override
    public Map<String,Object> findGoodsList(Integer start, Integer limit,String goodsName,Integer goodsIsShelf,String startTime,String endTime){
        Map<String,Object> returnMap = new HashMap<>();

        StringBuffer sbGoodsList = new StringBuffer(" FROM Goods WHERE 1=1 ");
        StringBuffer sbGoodsCount = new StringBuffer( " select count(*) from Goods where 1=1 ");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        Map<String,Object> map = new HashMap<>();
        if (!"".equals(goodsName)){
            sbGoodsList.append(" AND goodsName LIKE :goodsName ");
            sbGoodsCount.append( " AND goodsName Like :goodsName");
            map.put("goodsName","%"+goodsName+"%");
        }
        if (goodsIsShelf!=null){
            sbGoodsList.append(" AND goodsIsShelf =:goodsIsShelf ");
            sbGoodsCount.append( " AND goodsIsShelf  =:goodsIsShelf");
            map.put("goodsIsShelf",goodsIsShelf);
        }
        if(!"".equals(startTime)){
            try {
                Date sTime = Utils.sf_yMd.parse(startTime);
                map.put("sTime", sTime);
                sbGoodsList.append(" and  >= goodsCreateTime:sTime");
                sbGoodsCount.append(" and goodsCreateTime >= :sTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!"".equals(endTime)){
            try {
                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                c.setTime(Utils.sf_yMd.parse(endTime));
                c.add(Calendar.DATE, 1);
                map.put("eTime", c.getTime());
                sbGoodsList.append(" and goodsCreateTime <= :eTime");
                sbGoodsCount.append(" and goodsCreateTime <= :eTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        sbGoodsList.append(" ORDER BY goodsUpdateTime DESC ");

        List<Goods> goodsList = daoUtils.findByHQL(sbGoodsList.toString(),map,page);
        Integer goodsCount = daoUtils.getTotalByHQL(sbGoodsCount.toString(),map);

        returnMap.put("goodsList",goodsList);
        returnMap.put("goodsCount",goodsCount);

        return  returnMap;
    }

}
