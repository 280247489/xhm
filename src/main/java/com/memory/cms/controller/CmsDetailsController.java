package com.memory.cms.controller;

import com.memory.cms.repository.CmsUserRepository;
import com.memory.cms.service.CmsDetailsService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Details;
import com.memory.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName CmsDetailsController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/23 19:42
 */
@RestController
@RequestMapping(value = "cmsDetails")
public class CmsDetailsController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(CmsDetailsController.class);

    @Autowired
    private CmsDetailsService cmsDetailsService;

    @Autowired
    private CmsUserRepository cmsUserRepository;

    @Autowired
    private DaoUtils daoUtils;

    /**
     * 添加用户充值积分记录
     * @param userId
     * @param type
     * @param sum
     * @return
     */
    @RequestMapping(value = "addDetails",method = RequestMethod.POST)
    public Message addDetails(@RequestParam String userId, @RequestParam String type, @RequestParam Double sum){
        try {
            msg = Message.success();
            User user = cmsUserRepository.findByIdAndUserNologinAndUserCancel(userId,1,1);

            if (user!=null){
                Date date = new Date();
//                user.setUserIntegral(user.getUserIntegral()+sum);
                Details details = new Details();
                details.setId(Utils.generateUUID());
                details.setType(type);
                details.setTypeId("");
                details.setSum(sum);
                details.setUserId(userId);
                details.setUserIntegral(user.getUserIntegral()+sum);
                details.setIsComplete(0);
                details.setCreateTime(new Date());
                details.setCreateId("1");

                cmsDetailsService.addDetails(details);
                msg.setRecode(0);
            }else{
                msg.setRecode(1);
                msg.setMsg("无此用户");
            }

        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 添加用户积分，修改账单状态
     * @param detailsId
     * @return
     */
    @RequestMapping(value = "addUserIntegral",method = RequestMethod.POST)
    public Message addUserIntegral(@RequestParam String detailsId){
        try {
            msg = Message.success();
            Details details = (Details) daoUtils.getById("Details",detailsId);
            User user = cmsUserRepository.findByIdAndUserNologinAndUserCancel(details.getUserId(),1,1);
            if (details!=null){
                if (user!=null){
                    details.setIsComplete(1);
                    user.setUserIntegral(user.getUserIntegral()+details.getSum());
                    cmsDetailsService.addIntegral(user,details);
                }else{
                    msg.setRecode(1);
                    msg.setMsg("无此用户");
                }
            }else {
                msg.setRecode(1);
                msg.setMsg("无此记录");
            }


        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }
}
