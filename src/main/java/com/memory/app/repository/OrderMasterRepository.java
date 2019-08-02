package com.memory.app.repository;

import com.memory.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName OrderMasterRepository
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/27 17:31
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    OrderMaster findByOrderNo(String orderNo);


}
